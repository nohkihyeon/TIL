# hello, world를 출력하는 소켓 프로그램의 기능 확장
- 이전에 작성한 [hello, world를 출력하는 소켓 프로그램](https://github.com/nohkihyeon/TIL/blob/main/Network/socket_01.md#hello-world%EB%A5%BC-%EC%B6%9C%EB%A0%A5%ED%95%98%EB%8A%94-%EC%86%8C%EC%BC%93-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%A8)에 클라이언트 프로그램이 우선 문자열 print라는 명령과 같은 요청을 보내고 서버 프로그램이 이 요청을 받아야만 문자열 전송이 되도록 기능을 추가
![image](https://user-images.githubusercontent.com/65120581/128444430-783d16d2-1961-41f6-83c7-a7d85e9c1887.png)

1. 연결 요청 : 클라이언트 프로그램은 서버 프로그램에 연결 요청을 하고 연결한다.
2. print 요청 : 클라이언트 프로그램은 사용자로부터 문자열 print를 입력받아 서버 프로그램으로 전송한다.
3. 문자열 전송 : print 요청을 받은 서버 프로그램은 사전 약속대로 문자열 hello, wolrd를 클라이언트 프로그램으로 전송한다.
4. 화면 출력 : 클라이언트 프로그램은 전송받은 문자열(hello, world)을 화면에 출력한다.

#### hello_ext_server.c
```c
#include<stdio.h>
#include <netinet/in.h>
#include <sys/socket.h>
#define PORT 9005
#include<string.h>

char buffer[BUFSIZ] = "HELLO WORLD";
char rBuffer[BUFSIZ];

void printAll(){
        printf("All");
}

int main(){
        int c_socket, s_socket;
        struct sockaddr_in s_addr, c_addr;
        int len;
        int n;

        char *temp;
        int length;

        s_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&s_addr, 0, sizeof(s_addr));
        s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        s_addr.sin_family = AF_INET;
        s_addr.sin_port = htons(PORT);


        if(bind(s_socket, (struct sockaddr *) & s_addr, sizeof(s_addr)) == -1){
                printf("Can not Bind\n");
                return -1;
        }
        
        /* 소켓으로 통신이 이루어지도록
         * 운영체제(커널)에 개통을 요청한다.
         */
        if(listen(s_socket, 5) == -1){
                printf("listen Fail\n");
                return -1;
        }
        
        /* 서버가 클라이언트로부터의 연결 요청을 받아 처리
         * 요청을 무한히 반복을 통해 확인
         */
        while(1){
                len = sizeof(c_addr);
                c_socket = accept(s_socket, (struct sockaddr *) & c_addr, &len);

                length = 0;
                temp = rBuffer;
                while((n = read(c_socket, temp, 1)) > 0 ){
                        if(*temp == '\r') continue;
                        if(*temp == '\n') break;
                        if(*temp == '\0') break;            // 문자열의 끝날 때까지 읽어 들인다.

                        if(length == BUFSIZ) break;
                        temp++; length++;

                }
                rBuffer[length] = '\0';

                if(!strcmp(rBuffer, "print")) {
                        n = strlen(buffer);
                        write(c_socket, buffer, n);
                }
                close(c_socket);
        }
        close(s_socket);
}

```

#### hello_ext_client.c
```c
#include<stdio.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<strings.h>
#include<string.h>

#define PORT 9005
#define IPADDR "127.0.0.1"

char buffer[BUFSIZ];

int main(){
        int c_socket;
        struct sockaddr_in c_addr;
        int len;

        char rcvBuffer[BUFSIZ];

        int n;

        c_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&c_addr,0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(IPADDR);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(PORT);

        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                printf("Can not connect\n");
                close(c_socket);
                return -1;
        }

        scanf("%s", buffer);
        buffer[strlen(buffer)] = '\0';
        write(c_socket, buffer, strlen(buffer)+1);

        if((n = read(c_socket, rcvBuffer, sizeof(rcvBuffer))) < 0){
                return (-1);
        }


        rcvBuffer[n] = '\0';
        printf("received Data : %s\n", rcvBuffer);

        close(c_socket);
}

```

#### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128445434-e9fbad4a-8ca4-40a0-8bb9-2d9b96a20e28.png)
