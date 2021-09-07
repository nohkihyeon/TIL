# HTTP
웹 브라우저와 웹 서버 사이에서 HTML 문서 등을 전송할 때 사용하는 프로토콜
웹 브라우저가 웹 서버에 연결해서 HTTP 프로토콜의 규약에 따라 HTML 문서를 요청하면 웹서버는 이 요청을 분석한 후 웹 브라우저로 해당하는 HTML 문서를 반환하여 응답한다.
웹 브라아저는 웹 서버로부터 받은 HTML 문서를 해석해서 GUI를 통해 사용자에게 보내준다.
![image](https://user-images.githubusercontent.com/65120581/132170147-4c10dc87-c538-49d1-9fc8-694984210518.png) <br>
[출처 웹의 구동원리](http://tcpschool.com/webbasic/works)

- 웹 브라우저는 URL(Uniform Resource Locator)에 명시된 서버의 주소로 연결한다. 연결할 서버의 포트 번호가 명시되어 있으면 해당 포트로 연결을 요청하고, 그렇지 않으면 80번 포트로 연결을 요청한다.
- 웹 브라우저와 웹 서버 사이에 통신 채널이 연결되면, 웹 브라우저는 내부적으로 HTTP 규약을 따르는 요청 메시지(Request Message)를 생성해서 웹 서버로 전송한다. 요청 메시지는 요청라인, 요청 헤더, 바디 등으로 구성
- 웹 서버는 웹 브라우저로부터의 요청 메시지를 분석하고 해당하는 문서를 찾는다. HTTP 규약에 부합하는 응답 메시지를 구성해서 웹 브라우저에게 전송한다. 응답 메시지는 상태라인, 응답 헤더, 바디 등으로 구성된다. 여기서 응답 헤더는 성공/실패 여부, 전송할 데이터 유형 등으로 구성되며, 실제 데이터는 응답 헤더에 이어서 전송한다.
- 웹 서버는 응답 메시지를 전송한 직후, 강제로 연결을 종료한다.
- 웹 브라우저는 웹 서버가 전송한 응답 메시지를 분석해서 웹 브라우저를 통해 사용자에게 보여준다.
- 만일 웹 서버가 전송한 응답 메시지에 다음고 같이 이미지 파일과 같은 참조가 있으면 웹 브라우저는 서버에 다시 요청해서 이 파일을 받아 온다. 이때 웹 브라우저는 웹 서버로 다시 연결하게 되고, 받은 다음에야 비로서 웹 브라우저로 출력한다.
- ex) `<img src ="image.jpg">`
  - HTML 문서에 10개의 <img> 태그가 있다면, HTML 문서에 대한 요청 1회, 이미지 파일에 대한 요청 10회를 포함, 총 11회 웹 서버와 연결해서 파일을 받아야 한다. 그래야만 해당 문서를 출력할 수 있다.
  - 파일을 받기 위해서 이루어지는 서버와의 연결과 자료 송수신을 트랜잭션이라 하는데, 예에서 총 11회의 트랜잭션이 이루어지게 된다. 
 ![image](https://user-images.githubusercontent.com/65120581/132171008-6da79e47-61f1-4beb-9ec2-90077d91764e.png)
 
 # 웹 서버의 구현
 - 서버는 GET 방식의 요청만을 처리
 - 다음과 같은 형식의 파일에 대해 서비스를 제공
  - 정적 웹 페이지(html, htm), 이미지 파일(gif, png, jpg, jpeg), 압축 이진 파일이나 아카이브(zip, gz, tar)

## 프로그램 구현 과정
![image](https://user-images.githubusercontent.com/65120581/132172476-5bc1609c-bb6b-4cb8-aba5-5956d1e84120.png)
- 1 웹 브라우저로 아파치 웹 서버에 연결한다. HTTP 규약에 따라 웹 서버로 index.html 파일을 요청하고, 이를 받아 웹 브라우저로 출력하는 과정
  - url : http//127.0.0.1/index.html
- 2 웹 브라우저 대신에 쉘상에서 telnet 명령으로 아파치 웹 서버에 연결하고, http 규약에 따라 웹 서버로 index.html 파일을 요청하고, 서버의 응답을 관찰한다.
  - `$ telnet 127.0.9.1 80`
  - `GET / index.html HTTP/1.0`
- 3 HTTP 규약으로 웹 서버에 웹 문서를 요청하는 웹 프로그램을 직접 구현하고, 아파치 웹 서버에 연결해서 웹 문서를 요청하고 받는 과정을 관찰
- 4 HTTP 규약에 따른 요청을 처리하는 웹 서버 프로그램을 직접 구현하여 9000번 포트에 연결해서 실행 후, 웹 서버의 기본 동작이 제대로 구동되는지 쉘상에서 telnet 명령으로 검증한다.
- 5 우리가 구현한 웹 클라이언트와 웹 서버 프로그램으로 HTTP 규약에 따라 웹 문서를 요청하고 응답을 관찰
- 6 웹 브라우저에서 우리가 구현한 웹 서버로 HTTP 규약에 따라 요청을 보내고, 처리하는 과정을 관찰

### `$ yum install httpd` 로 설치

### 홈 디렉토리
- 웹 서버의 홈 디렉토리는 환경 변수 ServerRoot에 설정되어 있다. grep 명령어를 이요하여 ServerRoot가 설정되어 있는 경로를 알 수 있다.
![image](https://user-images.githubusercontent.com/65120581/132180297-9a91be78-9555-4709-97c3-86a0d2765900.png)
- 웹 서버의 Listen 포트도 알 수 있다.

### 문서 파일의 홈 디렉토리
![image](https://user-images.githubusercontent.com/65120581/132181719-292b66c4-34df-4626-a19f-da14b5afbb66.png)



## 텔넷을 이용하여 아파치 웹 서버에 웹 문서 요청
- 텔넷으로 127.0.0.1의 80번 포트로 연결하고, HTTP 규약에 따라 `GET /inde.html HTTP/1.0\r\n\r\n`과 같은 문서 요청 메시지를 웹서버로 전송하면 웹서버는 환경 변수 DocumentRoot에 설정된 문서 파일 홈 디렉토리에서 해당 파일 문서(/usr/local/apache/htdocs/index.html)를 읽어서 클라이언트에게 보내준다.
- telnet 명령으로 아파치 웹 서버에 HTML 문서를 요청한 결과
![image](https://user-images.githubusercontent.com/65120581/132308041-7e594e09-c7ed-4a29-a055-1c3bea208fdd.png)
![image](https://user-images.githubusercontent.com/65120581/132308257-8a4bf484-fccd-4ecf-82a1-c3ec67a6707e.png)
<br>

<details>
<summary>webServer.c</summary>
<div markdown="1">
  
```c
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/resource.h>
#include <netinet/in.h>
#include <signal.h>
#include <sys/stat.h>

#define LOG 100
#define ERROR 200

#define CODE200 200
#define CODE404 404

#define PHRASE200 "OK"
#define PHRASE404 "FILE NOT FOUND"

char documentRoot[ ] = "/etc/httpd/htdocs";

void do_web(int);
void web_log(int, char[ ], char[ ], int);

int log_fd;

int
main(int argc, char *argv[ ]) {
        struct sockaddr_in s_addr, c_addr;
        int     s_sock, c_sock;
        int     len, len_out;
        unsigned short port;
        int     status;
        struct rlimit resourceLimit;
        int     i;

        int     pid;

        if(argc != 2){
                printf("usage: webServer port_number");
                return -1;
        }

        if(fork( ) != 0)
                return 0;                               // parent return to shell

        (void)signal(SIGCLD, SIG_IGN);          // ignore child death
        (void)signal(SIGHUP, SIG_IGN);          // ignore terminal hangup

        resourceLimit.rlim_max = 0;
        status = getrlimit(RLIMIT_NOFILE, &resourceLimit);
        for(i = 0; i < resourceLimit.rlim_max; i++) {
                close(i);
        }

        web_log(LOG, "STATUS", "web server start", getpid( ));

        if((s_sock=socket(PF_INET, SOCK_STREAM, 0))<0){
                web_log(ERROR, "SYSCALL", "web server listen sockek open error", s_sock);
        }

        port=atoi(argv[1]);
        if(port > 60000)
                web_log(ERROR, "ERROR", "invalid port number", port);

        memset(&s_addr, 0, sizeof(s_addr));
        s_addr.sin_family = AF_INET;
        s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        s_addr.sin_port = htons(port);

        if(bind(s_sock, (struct sockaddr *) &s_addr, sizeof(s_addr)) <0)
                web_log(ERROR, "ERROR", "server cannot bind", 0);

        listen(s_sock, 10);

        while(1){
                len = sizeof(c_addr);
                if((c_sock = accept(s_sock, (struct sockaddr *) &c_addr, &len)) < 0)
                        web_log(ERROR, "ERROR", "server accept error", 0);

                if((pid = fork( )) < 0) {
                        web_log(ERROR, "ERROR", "server fork error", 0);
                } else if(pid == 0) {
                        close(s_sock);
                        do_web(c_sock);
                } else {
                        close(c_sock);
                }
        }
}

void
do_web(int c_sock)
{
        char    sndBuf[BUFSIZ+1], rcvBuf[BUFSIZ+1];
        char    uri[100], c_type[20];;
        int     len;

        int     len_out;
        int     n, i;
        char    *p;
        char    method[10], f_name[20];
        char    phrase[20] = "OK";

        int     code = 200;
        int     fd;                     // file discriptor

        char    file_name[20];
        char    ext[20];

        struct stat sbuf;

        struct {
                char *ext;
                char *filetype;
        } extensions [ ] = {
                {"gif", "image/gif" },
                {"jpg", "image/jpeg"},
                {"jpeg","image/jpeg"},
                {"png", "image/png" },
                {"zip", "image/zip" },
                {"gz",  "image/gz"  },
                {"tar", "image/tar" },
                {"htm", "text/html" },
                {"html","text/html" },
                {0,0} };

        memset(rcvBuf, 0, sizeof(rcvBuf));
                if((n = read(c_sock, rcvBuf, BUFSIZ)) <= 0)
                        web_log(ERROR, "ERROR", "can not receive data from web browser", n);

                web_log(LOG, "REQUEST", rcvBuf, n);

                p = strtok(rcvBuf, " ");
                if(strcmp(p, "GET") && strcmp(p, "get"))
                        web_log(ERROR, "ERROR", "Only get method can support", 0);

                p = strtok(NULL, " ");
                if(!strcmp(p, "/"))
                sprintf(uri, "%s/index.html", documentRoot);
                else
                        sprintf(uri, "%s%s", documentRoot, p);

                strcpy(c_type,  "text/plain");
                for(i=0; extensions[i].ext != 0; i++) {
                        len = strlen(extensions[i].ext);
                        if( !strncmp(uri+strlen(uri)-len, extensions[i].ext, len) ) {
                                strcpy(c_type, extensions[i].filetype);
                                break;
                        }
                }

                if((fd = open(uri, O_RDONLY)) < 0) {
                        code = CODE404;
                        strcpy(phrase, PHRASE404);
                }

                p = strtok(NULL, "\r\n ");              // version

                                                        // send Header
                sprintf(sndBuf, "HTTP/1.0 %d %s\r\n", code, phrase);
                n = write(c_sock, sndBuf, strlen(sndBuf));
                web_log(LOG, "RESPONSE", sndBuf, getpid( ));

                sprintf(sndBuf, "content-type: %s\r\n\r\n", c_type);
                n = write(c_sock, sndBuf, strlen(sndBuf));
                web_log(LOG, "RESPONSE", sndBuf, getpid( ));

                if(fd >=0 ) {
                        while((n = read(fd, rcvBuf, BUFSIZ)) > 0) {
                                write(c_sock, rcvBuf, n);
                        }
                }

                close(c_sock);
                exit(-1);
        }

        void
        web_log(int type, char s1[ ], char s2[ ], int n)
        {
                int     log_fd;
                char    buf[BUFSIZ];

                if(type == LOG) {
                        sprintf(buf, "STATUS %s %s %d\n", s1, s2, n);
                } else if(type == ERROR) {
                        sprintf(buf, "ERROR %s %s %d\n", s1, s2, n);
                }

                if((log_fd = open("web.log", O_CREAT|O_WRONLY|O_APPEND, 0644)) >= 0) {
                        write(log_fd, buf, strlen(buf));
                        close(log_fd);
                }

                if(type == ERROR) exit(-1);

        }
```

</div>
</details>

  
  
<details>
<summary>webClient.c</summary>
<div markdown="1">

```c
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

#define BUF_LEN 128


int main(int argc, char *argv[ ])
{
        int s, n, len_in, len_out;
        struct sockaddr_in server_addr;
        char *haddr;
        char buf[BUF_LEN+1];
        int port;

        if(argc==3) {
                port=80;
        } else if(argc==4) {
                port=atoi(argv[3]);
        } else {
                printf("usage : webClient server_addr URL [port_number]");
                return -1;
        }

        haddr=argv[1];
        if((s=socket(PF_INET, SOCK_STREAM, 0)) < 0) {
                printf("can not create socket\n");
                return -1;
        }

        memset(&server_addr, 0, sizeof(server_addr));
        server_addr.sin_family=AF_INET;
        server_addr.sin_addr.s_addr=inet_addr(haddr);
        server_addr.sin_port=htons(port);

        if(connect(s, (struct sockaddr *) &server_addr, sizeof(server_addr)) < 0) {
                printf("can not connect");
                return -1;
        }

        sprintf(buf, "GET %s HTTP/1.0\r\n\r\n",  argv[2]);
        write(s, buf, strlen(buf));

        memset(buf, 0, sizeof(buf));
        while((n = read(s, buf, BUF_LEN)) > 0) {
                printf("%s", buf);
                memset(buf, 0, sizeof(buf));
        }

        close(s);
}

```

</div>
</details>
