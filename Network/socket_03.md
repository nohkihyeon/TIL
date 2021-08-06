# 파일 목록을 출력하는 소켓 프로그램 구현

![image](https://user-images.githubusercontent.com/65120581/128458497-3d7687ab-84c2-4372-b9e0-3f285a22ec9a.png)
1. 연결 요청 : 서버 프로그램에 연결 요청을 시도해서 연결한다.
2. ls 요청 : 서버 프로그램에 문자열 ls를 전송해서 파일 목록을 요청한다.
3. 문자열(파일목록) 전송 : ls 요청을 받은 서버 프로그램은 파일 목록을 문자열로 만등러서 클라이언트 프로그램으로 전송한다.
4. 화면 출력 : 클라이언트 프로그램은 전송받은 문자열(파일 목록)을 자신의 화면에 출력한다.

### 파일 목록 읽어 내기
- 파일 목록을 읽어 내기 위해서는 해당 운영체제가 채택한 파일 시스템에 대해서 알고 있어야 한다.
- 리눅스나 유닉스 계열의 경우 디렉토리는 특수 파일로, I-노드 번호와 파일명의 쌍을 갖는 구조체의 열로 이루어져 있다.
- 디렉토리의 구조체에서 I-노드 번호는 I-노드를 찾는 Index로 사용된다.
- I-노드 정보
  - 파일 소유주의 사용자  ID와 그룹 ID
  - 접근 권한 정보(Protection)
  - 파일 내용이 저장된 물리적인 디스크의 번지
  - 파일 크기
  - 최근의 I-노드 변경 시각, 최근의 이용 시각, 최근의 갱신 시각
  - 해당 파일이 몇 개으 ㅣ디렉토리로부터 참조되고 있는지 참조 수
  - 파일 유형(디렉토리, 일반 파일, 특수 파일)

#### 구조체
```c
Struct dirent {
  long             d_ino;              // I-노드 번호
  off_t            d_off;              // offset
  unsigned  short  d_reclen;     // 파일 이름 길이
  char             d_name[NAME_MAX+1]; // 파일 이름
```


### 함수
```c
#include <dirent.h>

DIR *opendir(const char *dirname);
```
>#### 반환 값
>- 성공 : 디렉토리 스트림의 포인터
>- 실패 : NULL


```c
#include <dirent.h>

struct dirent *readdir(DIR *dirp);
```
>#### 반환 값
>- 성공 : 파일 정보가 담긴 dirent 구조체
>- 실패 : NULL

```c
#include <dirent.h>

int closedir(DIR* dp);
```
>#### 반환 값
>- 성공 : 0
>- 실패 : -1

### ls_server.c
```C
#include<stdio.h>
#include<string.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<dirent.h>

#define PORT 9222

char err_1[] = "Directory Error";
char rBuffer[BUFSIZ];

int main(void) {
        int c_socket, s_socket;
        struct sockaddr_in s_addr, c_addr;
        int len, length;
        int n, i;
        char *temp;

        DIR *dp;
        struct dirent *dir;
        
        // 1. 소켓을 생성
        s_socket = socket(PF_INET, SOCK_STREAM, 0);            
        
        /* 2. 연결 요청을 받을 주소와 포트를 결정
         * 포트 번호 9222으로 들어오는 클라이언트의 연결 요청을 받아 들일 수 있도록
         * 구조체 변수 s_addr에 주소와 포트를 결정
         */
        memset(&s_addr, 0, sizeof(s_addr));
        s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        s_addr.sin_family = AF_INET;
        s_addr.sin_port = htons(PORT);
         
        /* 3. 소켓을 주소와 포트에 연결
         * s_socket을 변수 s_addr에 따라 연결
         */
        if(bind(s_socket, (struct sockaddr *) &s_addr, siz
                printf("can not Bind\n");
                return -1;
        }
        
        /* 4. 커널에 개통을 요청
         * 소켓으로 통신을 할 수 있도록 운영체제에 개통을 요청
         */
        if(listen(s_socket, 5) == -1){
                printf("listen Fail\n");
                return -1;
        }

        // 5. 무한반복
        while(1){
                // 6. 연결 요청을 수신
                len = sizeof(c_addr);
                c_socket = accept(s_socket, (struct sockad

                // 7. 파일 목록을 출력
                length =0;
                temp = rBuffer;
                while((n = read(c_socket, temp, 1)) > 0 ){
                        if(*temp == '\r') continue;
                        if(*temp == '\n') break;
                        if(*temp == '\0') break;

                        if(length == BUFSIZ) break;
                        temp++; length++;
                }

                rBuffer[length] = '\0';

                if(!strcmp(rBuffer, "ls")) {
                        if((dp = opendir(".")) == NULL) {
                                write(c_socket, err_1, str
                        } else {
                                while((dir = readdir(dp))
                                        if(dir->d_ino == 0) continue;

                                        write(c_socket, dir->d_name, strlen(dir->d_name));
                                        write(c_socket, " ", 1);
                                }
                                closedir(dp);
                        }
                }
                // 8. 클라이언트와의 연결을 종료
                close(c_socket);
        }
        // 9. 서버를 종료
        close(s_socket);
}

```

### ls_client.c
```C
#include<stdlib.h>
#include<string.h>
#include<stdio.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<strings.h>

#define PORT 9222
#define IPADDR "127.0.0.1"

char buffer[BUFSIZ];

int main(){
        int c_socket;
        struct sockaddr_in c_addr;
        int len;

        char rcvBuffer[BUFSIZ];

        char *temp;
        int length =0;

        int n;

        c_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(IPADDR);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(PORT);

        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                printf("can't connect\n");
                close(c_socket);
                return -1;
        }

        scanf("%s", buffer);
        buffer[strlen(buffer)] = '\0';
        if((n = write(c_socket, buffer, strlen(buffer)+1)) < 0 ){
                printf("write error\n");
                exit(-1);
        }

        temp = rcvBuffer;
        while((n = read(c_socket, temp, 1)) > 0 ) {
                if(length == BUFSIZ) break;
                temp++; length++;
        }

        rcvBuffer[length] = '\0';

        printf("received Data : %s\n", rcvBuffer);

        close(c_socket);
}

```
- `$ ll` 현재 디렉토리의 파일 출력 <br>
![image](https://user-images.githubusercontent.com/65120581/128461235-30bb3622-3ad8-4338-921d-dffb83d17ed3.png)

### 실행 화면
![image](https://user-images.githubusercontent.com/65120581/128461174-d396caa7-e635-4880-b20a-321a997e29cc.png)
