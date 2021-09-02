# pthread
프로세스 내에서 실행되는 여러 흐름의 단위
여러개의 프로세스를 만드는 것이 아닌, 하나의 프로세스 안에 스레드를 생성하여 여러개의 스레드가 돌아가며 동작하게 한다.
## 스레드의 장점
- 문맥교환(context switching) 시간이 짧다.
- 메모리공유로 인하여 시스템 자원 소모가 줄어든다.
- 응답시간이 단축된다.

## pthread
POSIX Thread의 약자로 유닉스 계열 POSIX시스템에서 병렬적으로 작동하는 소프트웨어를 작성하기 위하여 제공하는 API

# pthread를 이용한 Bingo Game

## server.c
<details>
  <summary>코드 접기/펄치기</summary>
  <div markdown="1">
    
```C
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <pthread.h>
#define BOARD_SIZE 5
#define NAME_SIZE 10
#define BUF_SIZE 100
#define BACKLOG 3
#define MAX_CLNT 256

void error_handling(char* mse);
void* handle_clnt(void* arg);
void* handle_game(void* arg);
void* status_board(void* arg);
void send_msg(char* msg, int len,int index);


int clnt_cnt = 0;
int clnt_socks[MAX_CLNT];
int Game_on=0;//게임이 시작되면 1이 되고, 추가 접속을 막는다. 게임이 끝나면 2가                                                                                         되고, exit한다(쓰레드정리+동적할당정리+main끝)
int win_check =0;//RCV쓰레드와 GM쓰레드가 함 사용하므로 전역변수
                //가장 어려운 세마포를 제어하는 전역변수이다. 클라이언트가 N(숫                                                                                        자)를 입력할때 마다 win_check이 1씩 증가한다.
                //하지만 서버쪽에서 실제로는 win_check이 clnt_cnt만큼 증가한다.                                                                                         클라이언트가 1번 입력하면 AUTO_SendALL을 통해 모든 클라이언트가 그값에 반응을 하                                                                                        기때문이다.
                //그러므로 win_check이 clnt_cnt과 같다면 단일 클라이언트가 1번입                                                                                        력한것에 대해 모든 클라이언트가 반응했다는 뜻이다.
                //즉 내가 빙고를 완성했을때, 다른 클라이언트도 반응했는지 체크하                                                                                        고, 통신이 원활하게 이루어졌는지 검증할수 있게된다.

struct Clnt{
        char IP[16];
        int PORT;
        char NAME[10];//동적할당으로 수정전에 NAME_SIZE를 10으로 임의
        int R;//0은 준비중 1은 준비완료 2는 게임중 3은 게임중+
        int Bingo;//
};
struct Clnt C[MAX_CLNT]; //what a massive
char msgQ[5][NAME_SIZE+BUF_SIZE+1]; //SND쓰레드와 RCV쓰레드가 함께 사용하므로 전                                                                                        역변수
pthread_mutex_t mutx;
pthread_t t_id;
pthread_t t_id2;
pthread_t t_id3;

int main(int argc, char* argv[])
{
        int serv_sock, clnt_sock;
        struct sockaddr_in serv_adr, clnt_adr;
        int clnt_adr_sz;
        char name[NAME_SIZE]="[DEFAULT]";
        pthread_t t_id;
        if (argc != 2) {
                printf("insert port.");
        }
        pthread_mutex_init(&mutx, NULL);
        serv_sock = socket(PF_INET, SOCK_STREAM, 0);

        memset(&serv_adr, 0, sizeof(serv_adr));
        serv_adr.sin_family = AF_INET;
        serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);
        serv_adr.sin_port = htons(atoi(argv[1]));

        if (bind(serv_sock, (struct sockaddr*)&serv_adr, sizeof(serv_adr)) == 1)                                                                                         {
                error_handling("bind() error");
        }
        if (listen(serv_sock, 5) == -1) {
                error_handling("listen() error");
        }
        printf("var reset, ip port allocate, listen, setting all cleared. start                                                                                         in 5sec \n");
        sleep(5);

        pthread_create(&t_id2, NULL, status_board, (void*)&clnt_sock);
        pthread_detach(t_id2);

        pthread_create(&t_id3, NULL, handle_game, (void*)&clnt_sock);
        pthread_detach(t_id3);

        while (Game_on!=1) {
                int str_len;
                char msg[1+NAME_SIZE+BUF_SIZE];
                clnt_adr_sz = sizeof(clnt_adr);
                clnt_sock = accept(serv_sock, (struct sockaddr*)&clnt_adr, &clnt                                                                                        _adr_sz);

                pthread_mutex_lock(&mutx);
                clnt_socks[clnt_cnt] = clnt_sock;
                pthread_mutex_unlock(&mutx);

                pthread_create(&t_id, NULL, handle_clnt, (void*)&clnt_sock);
                pthread_detach(t_id);
                strcpy(C[clnt_cnt].IP,inet_ntoa(clnt_adr.sin_addr));//클라이언트                                                                                         주소저장
                C[clnt_cnt].PORT=ntohs(clnt_adr.sin_port);//클라이언트의 포트저                                                                                        장
                C[clnt_cnt].R=0;//클라이언트가 레디하지않음으로 설정
                clnt_cnt++;
        }
        if(Game_on!=2){
        close(serv_sock);
        return 0;
        }
}

void error_handling(char* msg){
        fputs(msg, stderr);
        fputc('\n', stderr);
        exit(1);
}

void* handle_clnt(void* arg) {//클라이언트를 1대1로 담당하는 쓰레드이자, 수신을                                                                                         담당하는 쓰레드. 이쓰레드에서 사용되는 변수는 전역변수로 설정할지 검증되어야 한                                                                                        다.
        int clnt_sock = *((int*)arg);
        int str_len = 0, i, j;
        //int win_check=0; //전역변수로  되었음
        char msg[1+NAME_SIZE+BUF_SIZE];
        //handle_clnt의 메세지 수신부분
        send_msg("",1,0);//서로 연결이 확정되면 의미없는 문장을 보내서, 클라이언                                                                                        트의 RCV와 game_print를 활성화시킨다
        while ((str_len = read(clnt_sock, msg, sizeof(msg))) != 0)
        {
                //입력에 대한 기본처리
                printf("[Debug]red is it correct? :%s\n",msg);
                char tmpName[10]; //
                        for(i=0,j=0;i<10;i++){
                        if(msg[i+1]!=32) {tmpName[j++]=msg[i+1];}
                        }
                char tmpMsg[100]; //
                        int k;
                        for(k=0;k<100;k++){
                        tmpMsg[k]=msg[k+11];
                        }
                        tmpMsg[k]='\0';


                 //순서1.W로 시작하는 숫자내역이왔을때 처리. 맨위에 있어야 무승                                                                                        부 처리 순서에 올바르다.
                if(msg[0]==87)
                {
                        for(i=0; i<clnt_cnt;i++){
                                if(tmpMsg[0]==49&&(strcmp(C[i].NAME,tmpName)==0)                                                                                        )
                                {
                                        printf("[Debug]BINGO!!");
                                        C[i].Bingo=1;
                                }
                        }
                        pthread_mutex_lock(&mutx);
                        win_check++;//반드시 값처리가 끝난뒤 win_check을 올려줘                                                                                        야한다.
                        printf("[Debug]win_check++\n");
                        pthread_mutex_unlock(&mutx);
                }
                //순서2.C로 시작하는 채팅내역이오면
                if(msg[0]==67)
                {
                        char tmpNameMsg[111];
                        //tmpNameMsg[sizeof(tmpName)+sizeof(tmpMsg)] = '\0';
                        sprintf(tmpNameMsg,"%s%s",tmpName,tmpMsg);


                        strcpy(msgQ[4],msgQ[3]);
                        strcpy(msgQ[3],msgQ[2]);
                        strcpy(msgQ[2],msgQ[1]);
                        strcpy(msgQ[1],msgQ[0]);
                        strcpy(msgQ[0],tmpNameMsg);

                        char sendMsg[BUF_SIZE+NAME_SIZE+1+1];
                        sprintf(sendMsg,"%s%10s%s","C",tmpName,tmpMsg);

                        //sprintf(tmpNameMsg,"%s",tmpMsg);
                        //send_msg(msgQ[0], 1+NAME_SIZE+BUF_SIZE,1);

                        send_msg(sendMsg, 1+NAME_SIZE+BUF_SIZE,11);
                }

                        //S로 시작하는 네임세팅이 오면
                        if(msg[0]==83)
                        {
                                strcpy(C[clnt_cnt-1].NAME,tmpName);
                        }

                        //R로 시작하는 레디내역이오면
                        if(msg[0]==82)
                        {
                                for(i=0; i<clnt_cnt;i++){
                                        if(strcmp(C[i].NAME,tmpName)==0){C[i].R+                                                                                        +;}
                                        //printf("C[i].NAME:%s tmp2:%s tmp:%s\n"                                                                                        ,C[i].NAME,tmp2,tmp);
                                }
                                send_msg("",1,2);//의미없는문자열을 보내서 클라                                                                                        이언트쪽 화면을 제어해준다
                        }

                        //N로 시작하는 숫자내역이오면
                        if(msg[0]==78)
                        {
                                for(i=0; i<clnt_cnt;i++)
                                {
                                        if(strcmp(C[i].NAME,tmpName)==0)
                                        {
                                                C[i].R=2;
                                                char tmp[1+NAME_SIZE+BUF_SIZE];
                                                if(i==clnt_cnt-1){
                                                        C[0].R=3;
                                                        sprintf(tmp,"%1s%10s","T                                                                                        ",C[0].NAME);
                                                        send_msg(tmp,1+NAME_SIZE                                                                                        +BUF_SIZE,5);
                                                }
                                                else{
                                                        C[i+1].R=3;
                                                        sprintf(tmp,"%1s%10s","T                                                                                        ",C[i+1].NAME);
                                                        send_msg(tmp,1+NAME_SIZE                                                                                        +BUF_SIZE,5);
                                                }
                                                char tmp2[1+NAME_SIZE+BUF_SIZE];
                                                sprintf(tmp2,"%1s%10s%2s","N","S                                                                                        ERV",tmpMsg);
                                                send_msg(tmp2,1+NAME_SIZE+BUF_SI                                                                                        ZE,5);
                                        }
                                }
                        }
                ///*
                for(i=0; i<1+NAME_SIZE+BUF_SIZE;i++){
                        msg[i]='\0';
                }
                //*/

        }
        //본인이 담당하면 클라이언트가 끊어졌다면, 서버에서 클라이언트의 정보를                                                                                         지우고 재설정한다.
        pthread_mutex_lock(&mutx);
        for (i = 0; i < clnt_cnt; i++) //eliminated disconnections
        {
                if (clnt_sock == clnt_socks[i])
                {
                        while (i++ < clnt_cnt - 1)
                                clnt_socks[i] = clnt_socks[i + 1];
                                memcpy(&C[i],&C[i+1],sizeof(struct Clnt));
                        break;
                }
        }
        clnt_cnt--;
        pthread_mutex_unlock(&mutx);
        close(clnt_sock);
        return NULL;
}
void* handle_game(void* arg){
        int i;
        while(1)
        {
                char tmp[1+BUF_SIZE+NAME_SIZE];
                //sum으로 R준비여부를 수집하여 연산
                if(clnt_cnt>1&&C[0].R==1&&Game_on==0)
                {

                        int sum=0;
                        for(i=0; i<clnt_cnt;i++)
                        {
                                sum+=C[i].R;
                        }
                        if(sum==clnt_cnt)
                        {
                                //게임을 시작하는 동안에는 다른 연산을 멈추고 게                                                                                        임에 맞도록 변수를 설정한다.
                                pthread_mutex_init(&mutx, NULL);
                                send_msg("GAMEON",1+BUF_SIZE+NAME_SIZE,3);
                                sleep(1);
                                send_msg("GAMEON",1+BUF_SIZE+NAME_SIZE,3);//왜                                                                                         인지 모르겠지만 가장 전송누락이 잦은 부분. 주의
                                for(i=0;i<clnt_cnt;i++)
                                {
                                        C[i].R=2;
                                }
                                C[0].R=3;
                                sprintf(tmp,"%1s%10s","T",C[0].NAME);
                                send_msg(tmp,1+BUF_SIZE+NAME_SIZE,4);
                                pthread_mutex_unlock(&mutx);
                        }
                }
                //w승리 배열에 승리여부를 총집합한뒤, 여러명이 승리했으면 무승부                                                                                        , 1명이 승리했으면 승리.(나머지인원에게는 패배)
                //핸들클라이언트 쓰레드들은 지금 if(win_check==3)에 의해 입력이                                                                                         제한된 상태이다.
                //혹시라도 win_check이나 c[].bingo를 가져오는 동안 값이 변조될수                                                                                        있다.(CASE:게임핸들 쓰레드가 연산을 하는중 클라이언트가 뒤늦게 발송)
                pthread_mutex_init(&mutx, NULL);
                if((clnt_cnt!=0)&&(win_check==clnt_cnt))
                {
                        int cnt=0;
                        for(i=0; i<clnt_cnt;i++)
                        {
                                if(C[i].Bingo==1)
                                {
                                        cnt++;
                                }
                        }
                        //Wflag: 0진행 1패배 2무승부 3승리
                        if(cnt==1)//1명의 단독승리
                        {
                                for(i=0; i<clnt_cnt; i++){
                                        if(C[i].Bingo==1){
                                                sprintf(tmp,"%1s%10s%s","W",C[i]                                                                                        .NAME,"3");
                                                send_msg(tmp,1+BUF_SIZE+NAME_SIZ                                                                                        E,7);
                                        }
                                        else{
                                                sprintf(tmp,"%1s%10s%s","W",C[i]                                                                                        .NAME,"1");
                                                send_msg(tmp,1+BUF_SIZE+NAME_SIZ                                                                                        E,8);
                                        }
                                }
                        }
                        else if(cnt>1)//무승부
                        {
                                for(i=0; i<clnt_cnt; i++){
                                        if(C[i].Bingo==1){
                                                sprintf(tmp,"%1s%10s%s","W",C[i]                                                                                        .NAME,"2");
                                                send_msg(tmp,1+BUF_SIZE+NAME_SIZ                                                                                        E,9);
                                        }
                                        else{
                                                sprintf(tmp,"%1s%10s%s","W",C[i]                                                                                        .NAME,"1");
                                                send_msg(tmp,1+BUF_SIZE+NAME_SIZ                                                                                        E,10);
                                        }
                                }
                        }
                        //printf("[Debug]입력검증끝");
                        win_check=0;
                }
                pthread_mutex_unlock(&mutx);
        }
}
void* status_board(void* arg){
        int i;
        while(1){
        //접속클라이언트 현황
                printf("CLNT\t|IP\t\t|PORT\t|NAME\t|Ready\t\t|BINGO\t\t|\n");
                for(i=0; i<clnt_cnt;i++){
                printf("%d\t|%s\t|%d\t|%s\t|",i,C[i].IP,C[i].PORT,C[i].NAME);
                if(C[i].R==0)printf("WAIT\t\t|%d\t\t|\n",C[i].Bingo);
                if(C[i].R==1)printf("READY\t\t|%d\t\t|\n",C[i].Bingo);
                if(C[i].R==2)printf("INGAME\t\t|%d\t\t|\n",C[i].Bingo);
                if(C[i].R==3)printf("TRUN\t\t|%d\t\t|\n",C[i].Bingo);
                }
        //채팅현황
                printf("\n================================\n");
                printf(" recent msgs\n");
                for(i=0; i<5;i++){
                printf("%d:%s\n",i,msgQ[i]);
                }
        //게임 현황
                printf("\n================================\n");
                printf("입력검증 %d / %d",win_check,clnt_cnt*clnt_cnt);
        //디버그 출력
                printf("\n================================\n");
        //딜레이
                sleep(8);
                system("clear");
        }
}
void send_msg(char* msg, int len, int index) {//index는 디버그용, 아무값이나 넣                                                                                        어도됌
        int i;
        pthread_mutex_lock(&mutx);
        for (i = 0; i < clnt_cnt; i++)
                write(clnt_socks[i], msg, len);
        pthread_mutex_unlock(&mutx);
        printf("[Debug] %d sendALL\n",index);
}

```
    
</div>
</details>
    
## client.c  
    
<details>
  <summary>코드 접기/펼치기</summary>
  <div>
    
```c
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <sys/select.h>
#include <netinet/in.h>
#include <pthread.h>

#define BOARD_SIZE 5
#define NAME_SIZE 10
#define BUF_SIZE 100

void* send_msg(void* arg);
void* recv_msg(void* arg);
void* game_set(void* arg);

void error_handling(char* mse);
void game_print(int any);
int bingo_check(int board[][BOARD_SIZE]);

//게임관련 구조체로 묶을 변수
struct Game{
int Game_on;
int game_turn;
int my_turn;
int my_bingo;
int winFlag; //Wflag: 0진행 1패배 2무승부 3승리
int board[BOARD_SIZE][BOARD_SIZE];
int bingo[BOARD_SIZE][BOARD_SIZE];

};
struct Game MyGame ={0,};


//채팅관련 구조체로 묶을변수

char msgQ[5][NAME_SIZE+BUF_SIZE];

//main의 매개변수용 char
char name[NAME_SIZE]="[DEFAULT]";

int main(int argc, char* argv[])
{
        int sock;
        struct sockaddr_in serv_addr;
        pthread_t snd_thread, rcv_thread, gam_thread;
        void* thread_return;
        int i,j;

        srand(time(NULL));
        int check_number[BOARD_SIZE * BOARD_SIZE] = { 0 };
        for (i = 0; i < BOARD_SIZE; i++)
        {
                for (j = 0; j < BOARD_SIZE; j++)
                {
                        while (1)
                        {
                                int temp = rand() % 25;

                                if (check_number[temp] == 0)
                                {
                                        check_number[temp] = 1;
                                        MyGame.board[i][j] = temp + 1;
                                        break;
                                }
                        }
                }
        }



        if (argc != 4) {
                printf("ip, port, name");
                exit(1);
        }
        sprintf(name,"[%s]",argv[3]);

        sock = socket(PF_INET, SOCK_STREAM, 0);

        memset(&serv_addr, 0, sizeof(serv_addr));
        serv_addr.sin_family=AF_INET;
        serv_addr.sin_addr.s_addr = inet_addr(argv[1]);
        serv_addr.sin_port = htons(atoi(argv[2]));

        if (connect(sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1)
                error_handling("connect err");
        pthread_create(&snd_thread, NULL, send_msg, (void*)&sock);
        pthread_create(&rcv_thread, NULL, recv_msg, (void*)&sock);
        pthread_create(&gam_thread, NULL, game_set, (void*)&sock);
        //pthread_detach(snd_thread);
        //pthread_detach(rcv_thread);
        //pthread_detach(gam_thread);
        pthread_join(snd_thread, &thread_return);
        pthread_join(rcv_thread, &thread_return);
        pthread_join(gam_thread, &thread_return);
        if(1){
        close(sock);
        return 0;}
}

void* send_msg(void* arg) {
        int sock = *((int*)arg);
        char set[111];
        sprintf(set,"%1s%10s","S",name);//이름을 최초 1회 보내는걸로 검증한다.
        write(sock, set, strlen(set));
        int i;
        while (1)
        {
                char msg[BUF_SIZE];
                char chat[NAME_SIZE+BUF_SIZE+2];
                //배열버퍼, stdin버퍼 초기화

                fgets(msg, BUF_SIZE, stdin);
                if (!strcmp(msg, "q\n")||!strcmp(msg,"Q\n"))//Q를 입력하면 종료로 인식한다.
                {
                        close(sock);
                        exit(0);
                }
                if(!strcmp(msg, "c\n")||!strcmp(msg,"C\n")) //C를 입력하면 채팅입력창을 출력한다.
                {
                        printf("type msg: ");
                        /*
                        for(int i=0; i<BUF_SIZE;i++)
                        {
                                msg[i]='\0';
                        }
                        for(int i=0; i<NAME_SIZE+BUF_SIZE+1;i++)
                        {
                                chat[i]='\0';
                        }
                        */
                        fgets(msg, BUF_SIZE, stdin);
                        msg[strlen(msg)-1]='\0';

                        //입력받은 msg로 chat내용을 세그먼트화 한다. (채팅-10자리이름(공백으로 줄맞춤)-메세지내용)
                        sprintf(chat,"%1s%10s%s","C",name,msg);
                        write(sock, chat, strlen(chat));
                        printf("[Debug]writed\n");


                }
                if(MyGame.my_turn==1 && !strcmp(msg,"N\n")) //내턴일때 N을 입력하면 숫자를 입력받는다.
                {
                        while(1) {
                                printf("NUM:");
                                fgets(msg, BUF_SIZE, stdin);

                                if(atoi(msg)==0) {
                                        continue;
                                } else {
                                        msg[strlen(msg)-1]=10;//개행문자
                                        sprintf(chat,"%1s%10s%2s","N",name,msg);
                                        write(sock, chat, strlen(chat));
                                        MyGame.my_turn--;
                                }
                                break;
                        }
                        printf("[Debug]writed\n");
                }
                else if(!strcmp(msg, "r\n")||!strcmp(msg,"R\n")) //R를 입력하면 레디내역을 서버에보낸다.
                {
                                for(i=0; i<BUF_SIZE;i++)
                                {
                                msg[i]='\0';
                                }
                        sprintf(chat,"%1s%10s","R",name);
                        write(sock, chat, strlen(chat));
                        printf("[Debug]writed\n");
                }

        }
        return NULL;
}
void* recv_msg(void* arg) {
        int sock = *((int*)arg);
        char chat[BUF_SIZE];
        char FLAG[1+NAME_SIZE+BUF_SIZE];
        ssize_t str_len;
        char msg[BUF_SIZE];
        int i,j;
        while (1)
        {
                if(str_len=read(sock, msg, 1+BUF_SIZE+NAME_SIZE)!=0){
                        char tmpName[10]; //
                        for(i=0,j=0;i<10;i++){
                                if(msg[i+1]!=32) {tmpName[j++]=msg[i+1];}
                        }
                        char tmpMsg[100]; //
                        for(i=0;i<111;i++){
                        tmpMsg[i]=msg[i+11];
                        }
                        system("clear");

                        if(strcmp(msg,"GAMEON")==0) MyGame.Game_on=1;
                        if(msg[0]==87)//W로 시작하는 제어문이 오면 Wflag: 0진행 1패배 2무승부 3승리
                        {
                                if(strcmp(tmpName, name)==0){
                                        printf("\n승리플래그 메세지 검증%d\n",tmpMsg[0]);
                                        switch (tmpMsg[0]) {
                                                case 48 : MyGame.winFlag=0; break;
                                                case 49 : MyGame.winFlag=1; break;
                                                case 50 : MyGame.winFlag=2; break;
                                                case 51 : MyGame.winFlag=3; break;
                                                default : MyGame.winFlag=-1; break;
                                        }
                                }
                        }
                        if(msg[0]==67) //C로 시작하는 채팅내역이오면
                        {
                                strcpy(msgQ[4],msgQ[3]);
                                strcpy(msgQ[3],msgQ[2]);
                                strcpy(msgQ[2],msgQ[1]);
                                strcpy(msgQ[1],msgQ[0]);
                                sprintf(msgQ[0],"%s%s",tmpName,tmpMsg);
                        }
                        if(msg[0]==84)//T로 시작하는 제어문이 오면
                        {
                                if(strcmp(tmpName, name)==0) MyGame.my_turn++;
                        }
                        if(msg[0]==78)//N로 시작하는 제어문이 오면
                        {
                                        //2자리문자열로 온 숫자를 아스키코드표에 따라 숫자로 변환
                                        //printf("숫자[%d][%d]",tmpMsg[0],tmpMsg[1]);
                                int NUM=0;
                                printf("서버입력받아 변환할 값 %d %d",tmpMsg[0],tmpMsg[1]);
                                if(tmpMsg[1]==10){NUM=tmpMsg[0]-48;}
                                else{NUM=(10*(tmpMsg[0]-48))+tmpMsg[1]-48;}
                                //printf("받아서 변환된숫자: %d\n",NUM);
                                for(i=0; i<BOARD_SIZE;i++){
                                        for(j=0; j<BOARD_SIZE;j++){
                                                if(MyGame.board[i][j]==NUM){
                                                        MyGame.bingo[i][j]=1;
                                                        MyGame.game_turn++;
                                                        //printf("smp checker");
                                                }
                                        }
                                  }
                                for(i=0; i<BUF_SIZE;i++)
                                {
                                FLAG[i]='\0';
                                }
                                MyGame.my_bingo=bingo_check(MyGame.bingo);
                                //리시브가 모두 끝나고 난 뒤에, 승리플래그를 보낼지 검증해야한다.
                                if(MyGame.my_bingo==3)
                                {
                                        sprintf(FLAG,"%1s%10s%s","W",name,"1");
                                        int k= write(sock, FLAG, strlen(FLAG));
                                        if(k!=-1) {printf("[Debug][bingo3]writed\n");}
                                }
                                else{
                                        sprintf(FLAG,"%1s%10s%s","W",name,"0");
                                        write(sock, FLAG, strlen(FLAG));
                                        printf("[Debug]writed\n");
                                }
                        }
                        for(i=0; i<BUF_SIZE;i++){
                                msg[i]='\0';
                        }
                        //UI표시부
                        game_print(0);


                }
        }
        return NULL;
}
void* game_set(void* arg){
        int sock = *((int*)arg);
}

void error_handling(char* msg)
{
        fputs(msg, stderr);
        fputc('\n', stderr);
        exit(1);
}

void game_print(int any)
{
        if(MyGame.Game_on==1){
        int i, j, x;
        printf("%c[1;33m", 27);

        printf("@----- client bingo -----@\n");
        printf("turn: %3d bingo: %3d\n", MyGame.game_turn, MyGame.my_bingo);
        printf("+----+----+----+----+----+\n");
        for (i = 0; i < BOARD_SIZE; i++)
        {
                for (j = 0; j < BOARD_SIZE; j++)
                {
                        /*
                        if (MyGame.board[i][j] == 0)
                        {
                                printf("| ");
                                printf("%c[1;31m", 27);
                                printf("%2c ", 88);
                                printf("%c[1;33m", 27);
                        }
                        else
                                printf("| %2d ", MyGame.board[i][j]);
                        */
                        if(MyGame.bingo[i][j]==1){
                                printf("|\033[1;31m %2d \033[1;33m", MyGame.board[i][j]);
                        }
                        else
                                printf("| %2d ", MyGame.board[i][j]);
                }
                printf("|\n");
                printf("+----+----+----+----+----+\n");
        }

        printf("%c[0m", 27);
        /*
        if (turn_count != 0)
        {
                printf("number: %d\n", 1);
                printf("bingo count: %d\n", 1);
        }*/
        }
        else{
                printf("\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        printf("=====================================\n");
        if(MyGame.winFlag==3){printf("YOU WIN!!\n");}
        else if(MyGame.winFlag==2){printf("DRAW!!\n");}
        else if(MyGame.winFlag==1){printf("LOSE\n");}
        else if(MyGame.winFlag==-1){printf("ERR\n");}
        else if(MyGame.my_turn==1){printf("its your turn\n");}
        else {printf("\n");}
        printf("=====================================\n");
        printf("5:%s \n4:%s \n3:%s \n2:%s \n1:%s \n",msgQ[4],msgQ[3],msgQ[2],msgQ[1],msgQ[0]);
        printf("=====================================\n");
        printf("C to chat,R to Ready,N to Number Q to quit\n");
}
int bingo_check(int board[][BOARD_SIZE])
{
        int i;
        int count=0;

        for(i=0; i < BOARD_SIZE; i++) //가로
        {
                if(board[i][0]==1&&board[i][1]==1&&board[i][2]==1&&board[i][3]==1&&board[i][4]==1) //가로
                {
                        count++;
                }
                if(board[0][i]==1&&board[1][i]==1&&board[2][i]==1&&board[3][i]==1&&board[4][i]==1) //세로
                        count++;
        }
        if(board[0][0]==1&&board[1][1]==1&&board[2][2]==1&&board[3][3]==1&&board[4][4]==1)
                count++;
        if(board[0][4]==1&&board[1][3]==1&&board[2][2]==1&&board[3][1]==1&&board[4][0]==1)
                count++;
        return count;
}
    
```
</div>
</details>
    
    
    
 ## 결과 화면
    
![image](https://user-images.githubusercontent.com/65120581/131804153-e1cebf93-3514-47fe-a02c-59b645ca82a6.png)
![image](https://user-images.githubusercontent.com/65120581/131804249-e71aae85-5fe0-44a1-b3b2-a26dae10d9f1.png)
![image](https://user-images.githubusercontent.com/65120581/131804281-bff4e527-e01b-475e-9f76-f9e76969aff5.png)
![image](https://user-images.githubusercontent.com/65120581/131804337-eea57418-36ef-4621-807f-325730d66c66.png)
