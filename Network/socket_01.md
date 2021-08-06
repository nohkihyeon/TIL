# hello, world를 출력하는 소켓 프로그램
- 소켓 프로그램 구성 
  - 서비스를 요청하는 클라이언트 측
  - 클라이언트로부터의 요청을 받아 서비스하는 서버 측
- 클라이언트 프로그램이 네트워크사엥서 통신 채널을 통해 서버측에 연결되면 서버 프로그램은 즉시 문자열 hello, world를 클라이언트 측에 전송
- 클라이언트 프로그램은 전송받은 문자열을 화면에 출력한다.
![image](https://user-images.githubusercontent.com/65120581/128443278-0c61843c-267b-4115-8254-57b7d3721811.png)
1. 연결요청 : 클라이언트 프로그램은 소켓 API 함수를 호출하여 서버 프로그램에 연결을 요청한다.
2. 문자열 전송 : 연결 요청을 받은 서버 프로그램은 클라이언트 프로그램과 연결되자마자 문자열 hello, world를 클라이언트측에 전송한다.
3. 화면 출력 : 클라이언트 프로그램은 전송받은 문자열 (helo, world)를 자신의 화면에 출력한다.
#### hello_server.c
```C
#include<stdio.h>
#include<string.h>
#include<netinet/in.h>
#include<sys/socket.h>
#define PORT 9001

char buffer[BUFSIZ] = "hello, wolrd";

int main()
{
        int c_socket, s_socket;
        struct sockaddr_in s_addr, c_addr;
        int len;
        int n;

        s_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&s_addr, 0, sizeof(s_addr));
        s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        s_addr.sin_family = AF_INET;
        s_addr.sin_port = htons(PORT);

        if(bind(s_socket, (struct sockaddr *) &s_addr, sizeof(s_addr)) == -1){
                printf("Can not Bind\n");
                return -1;
        }

        if(listen(s_socket, 5) == -1){
                printf("listen Fail\n");
                return -1;
        }
        while(1){
                len = sizeof(c_addr);
                c_socket = accept(s_socket, (struct sockaddr *) &c_addr, &len);

                n = strlen(buffer);
                write(c_socket, buffer, n);

                close(c_socket);
        }
        close(s_socket);
}


```



#### hello_client.c
- 서버 프로그램에 연결하기 위해 네트워크 연결 장치인 소켓을 생성해야 하고, 사전에 서버의 IP주소와 서버의 응용 프로그램이 결된 포트 번호를 알아야한다.
- 클라이언트 프로그램을 구현한 코드 (socket, connect, read 등의 소켓 관련 API 함수를 사용)
```C
#include<stdio.h>
#include <netinet/in.h>
#include <string.h>
#include <sys/socket.h>

#define PORT 9001
#define IPADDR "127.0.0.1"

main(){
        int c_socket;
        struct sockaddr_in c_addr;
        int len;
        int n;

        char rcvBuffer[BUFSIZ];

        c_socket = socket(PF_INET, SOCK_STREAM, 0);                               // TCP용 소켓을 생성

        memset(&c_addr, 0, sizeof(c_addr));                                       // 연결할 서버의 주소를 구조체 변수 c_addr에 설정
        c_addr.sin_addr.s_addr = inet_addr(IPADDR);                               // 연결할 서버의 주소는 127.0.0.1(자신)으로
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(PORT);                                            // 포트 번호는 9000번으로 설정 

        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){ // 소켓을 서버에 연결
                printf("Can not connect\n");
                close(c_socket);
                return -1;
        }
        
        /* 서비스 요청과 처리
         * 서버와 연결된 통신 채널을 통해 자료를 송수신하면서 서비스를 처리한다. 
        */
        if((n = read(c_socket, rcvBuffer, sizeof(rcvBuffer))) < 0){
                printf("here");
                return (-1);
        }

        rcvBuffer[n] = '\0';
        printf("reveived Data : %s\n", rcvBuffer);

        close(c_socket);
}
```



### 소켓을 서버에 연결

![image](https://user-images.githubusercontent.com/65120581/128441634-4e6e5091-23bd-4b70-bf5d-44d8c1cc33f9.png)
- 서버와의 연결은 커널 내부에서 [3방향 핸드셰이킹](https://github.com/nohkihyeon/TIL/tree/main/Network#tcp-3-way-handshake)을 거쳐 이루어진다. 앞서 서버에서는 서버 프로그램에서 클라이언트의 연결 요청을 받을 서버 내 포트 번호 (9001번 포트)를 결정
- 클라이언트에서는 클라이언트의 커널에서 서버 연결에 사용할 포트(그림에서는 2345번 포트)를 결정하기 때문에 클라이언트 프로그램에서는 포트번호를 특별히 지정하지 않는다.




#### 실행 화면

![image](https://user-images.githubusercontent.com/65120581/128442317-39d9f514-7454-4552-9a26-f3d89bc5acb3.png)
