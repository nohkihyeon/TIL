
# HTTP/2 check 해주는 C로 구현

## HTTP Request Header
![image](https://user-images.githubusercontent.com/65120581/133039666-2d1bd6e6-ed50-46d6-af43-f622989be55b.png)

## HTTP Status Code
![image](https://user-images.githubusercontent.com/65120581/133039591-01204c47-ebe6-4f96-84fa-0b2643cfd70d.png)
#### 1xx
- 100 Continue
진행 중임을 의미하는 응답코드입니다. 현재까지의 진행상태에 문제가 없으며, 클라이언트가 계속해서 요청을 하거나 이미 요청을 완료한 경우에는 무시해도 되는 것을 알려줍니다.
- 101 Switching Protocol
101은 클라이언트에 의해 보낸 업그레이드 요청 헤더에 대한 응답으로 보내집니다. 이 응답 코드는 클라이언트가 보낸 Upgrade 요청 헤더에 대한 응답에 들어가며, 서버에서 프로토콜을 변경할 것임을 알려줍니다. 해당 코드는 Websocket 프로토콜 전환 시에 사용됩니다.
- 102 Processing(WebDAV)
이 응답 코드는 서버가 요청을 수신하였으며 이를 처리하고 있지만, 아직 제대로 된 응답을 알려줄 수 없음을 알려줍니다.

#### 2xx
- 200 OK
요청이 성공적으로 되었습니다. 정보는 요청에 따른 응답으로 반환됩니다.
- 201 Created
요청이 성공적이었으며 그 결과로 새로운 리소스가 생성되었습니다. 이 응답은 일반적으로 POST 요청 또는 일부 PUT 요청 이후에 따라옵니다.
- 202 Accepted
요청을 수신하였지만, 그에 응하여 행동할 수 없습니다. 이 응답은 요청 처리에 대한 결과를 이후에 HTTP로 비동기 응답을 보내는 것에 대해서 명확하게 명시하지 않습니다. 이것은 다른 프로세스에서 처리 또는 서버가 요청을 다루고 있거나 배치 프로세스를 하고 있는 경우를 위해 만들어졌습니다.

#### 3xx
- 300 Multiple Choices
최대 다섯 개의 링크를 골라서 이동할 수 있는 상태
- 301 Moved Permanently
이 응답 코드는 요청한 리소스의 URI가 변경되었음을 의미합니다. 새로운 URI가 응답에서 아마도 주어질 수 있습니다.
- 302 Found
이 응답 코드는 요청한 리소스의 URI가 일시적으로 변경되었음을 의미합니다. 새롭게 변경된 URI는 나중에 만들어질 수 있습니다. 그러므로, 클라이언트는 향후의 요청도 반드시 동일한 URI로 해야합니다.
- 303 See Other
클라이언트가 요청한 리소스를 다른 URI에서 GET 요청을 통해 얻어야 할 때, 서버가 클라이언트로 직접 보내는 응답입니다.
- 304 Not Modified
이것은 캐시를 목적으로 사용됩니다. 이것은 클라이언트에게 응답이 수정되지 않았음을 알려주며, 그러므로 클라이언트는 계속해서 응답의 캐시된 버전을 사용할 수 있습니다.
- 305 Use Proxy
이전 버전의 HTTP 기술 사양에서 정의되었으며, 요청한 응답은 반드시 프록시를 통해서 접속해야 하는 것을 알려줍니다. 이것은 프록시의 in-band설정에 대한 보안상의 걱정으로 인하여 사라져가고 있습니다.

#### 4xx
- 400 Bad Request
 응답은 잘못된 문법으로 인하여 서버가 요청하여 이해할 수 없음을 의미합니다.
- 401 Unauthorized
비록 HTTP 표준에서는 '미승인(unauthorized)'를 명확히 하고 있지만, 의미상 이 응답은 '비인증(unauthenticated)'를 의미합니다. 클라이언트는 요청한 응답을 받기 위해서는 반드시 스스로를 인증해야 합니다.
- 402
이 응답 코드는 나중에 사용될 것을 대비해 예약되었습니다. 첫 목표로는 디지털 결제 시스템에 사용하기 위하여 만들어졌지만 지금 사용되고 있지는 않습니다.
- 403 Forbidden
클라이언트는 콘텐츠에 접근할 권리를 가지고 있지 않습니다. 예를 들어, 그들은 미승인이어서 서버는 거절을 위한 적절한 응답을 보냅니다. 401과 다른 점은 서버가 클라이언트가 누구인지 알고 있습니다.
- 404 Not Found
서버는 요청받은 리소스를 찾을 수 없습니다. 브라우저에서는 알려지지 않은 URL을 의미합니다. 이것은 API에서 종점은 적절하지만 리소스 자체는 존재하지 않음을 의미할 수 있습니다. 서버들은 인증받지 않은 클라이언트로부터 리소스를 숨기기 위하여 이 응답을 403 대신에 전송할 수도 있습니다. 이 응답 코드는 웹에서 반복적으로 발생하기 때문에 가장 유명할지도 모릅니다.

#### 5xx
- 500 Internal Server Error
웹 사이트 서버에 문제가 있음을 의미하지만 서버는 정확한 문제에 대해 더 구체적으로 설명할 수 없습니다.

- 501 Not Implemented
서버가 요청을 이행하는 데 필요한 기능을 지원하지 않음을 나타냅니다.

- 502 Bad Gateway
서버가 게이트웨이로부터 잘못된 응답을 수신했음을 의미합니다. 인터넷상의 서버가 다른 서버로부터 유효하지 않은 응답을 받은 경우 발생합니다.
### 처음 시도
```c
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>

char buffer[BUFSIZ];

int main(int argc, char *argv[]){
        int status;
        struct addrinfo hints;
        struct addrinfo *servinfo, *tmp;                // 결과를 저장할 변수
        char host[256];
        int c_socket;
        struct sockaddr_in c_addr;
        int len;

        char rcvBuffer[BUFSIZ];
        int n;

        c_socket = socket(PF_INET, SOCK_STREAM, 0);


        memset(&hints, 0, sizeof(hints));               // hints 구조체의 모든 값을 0으로 초기화
        hints.ai_family = AF_UNSPEC;                    // IPv4 IPv6 상관없이 결과 모두 반환
        hints.ai_socktype = SOCK_STREAM;                // TCP stream sockets

        if(argc ==1){
                printf("Enter Domain\n ");
                return -1;
        }
        status = getaddrinfo(argv[1], "80", &hints, &servinfo);

        tmp = servinfo;
        getnameinfo(tmp->ai_addr, tmp->ai_addrlen, host, sizeof(host), NULL, 0, NI_NUMERICHOST);

        sprintf(buffer, "GET / HTTP/1.1\r\nHost: %s\r\nConnection: %s\r\nUpgrade: %s\r\nHTTP2-Settings: %s\r\n\r\n", argv[1], "Upgrade, HTTP2-Settings", "h2c", "<base64url encoding of HTTP/2 SETTINGS payload>");

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(host);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(80);

        printf("hostname : %s\n", host);

        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                printf("Can not connect\n");
                close(c_socket);
                return -1;
        }

        write(c_socket, buffer, strlen(buffer)+1);
        memset(rcvBuffer, 0, sizeof(rcvBuffer));

        while((n=read(c_socket, rcvBuffer, BUFSIZ)) > 0){
                        printf("%s", rcvBuffer);
                        memset(rcvBuffer, 0, sizeof(rcvBuffer));
        }

        close(c_socket);
        freeaddrinfo(servinfo);

}

```
- 대부분의 사이트는 HTTPS를 우선적으로 확인하기 때문에 막상 패킷을 던졌을 경우 원하는 결과를 Response 해주지 않는다.
![image](https://user-images.githubusercontent.com/65120581/133040131-334b9dcf-9ab4-4068-9265-925def058130.png)
- 301 에러 Moved Permanently (REDIRECTION) 에러가 나타난다.

### HTTP로 Request 시 응답을 해주는 사이트 : ghttp2.org
```c
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>

char buffer[BUFSIZ];

int main(int argc, char *argv[]){
        int status;
        struct addrinfo hints;
        struct addrinfo *servinfo, *tmp;                // 결과를 저장할 변수
        char host[256];
        int c_socket;
        struct sockaddr_in c_addr;
        int len;
        int i=0;
        char *ptr;

        char rcvBuffer[BUFSIZ];
        int n;

        c_socket = socket(PF_INET, SOCK_STREAM, 0);


        memset(&hints, 0, sizeof(hints));               // hints 구조체의 모든 값을 0으로 초기화
        hints.ai_family = AF_UNSPEC;                    // IPv4 IPv6 상관없이 결과 모두 반환
        hints.ai_socktype = SOCK_STREAM;                // TCP stream sockets

        if(argc ==1){
                printf("Enter Domain\n ");
                return -1;
        }
        status = getaddrinfo(argv[1], "80", &hints, &servinfo);

        tmp = servinfo;
        getnameinfo(tmp->ai_addr, tmp->ai_addrlen, host, sizeof(host), NULL, 0, NI_NUMERICHOST);

        sprintf(buffer, "GET / HTTP/1.1\r\nHost: %s\r\nConnection: %s\r\nUpgrade: %s\r\nHTTP2-Settings: %s\r\n\r\n", argv[1], "Upgrade, HTTP2-Settings", "h2c", "<base64url encoding of HTTP/2 SETTINGS payload>");

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(host);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(80);


        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                printf("Can not connect\n");
                close(c_socket);
                return -1;
        }

        write(c_socket, buffer, strlen(buffer)+1);
        memset(rcvBuffer, 0, sizeof(rcvBuffer));

        read(c_socket, rcvBuffer, 20);
        ptr = strstr(rcvBuffer, "101");
        if(ptr != NULL){
                printf("%s : HTTP/2 is enabled.\n", argv[1]);
        }
        else
                printf("%s : HTTP/2 Not Found.\n", argv[1]);

        close(c_socket);
        freeaddrinfo(servinfo);

}
```

## 결과 화면
![image](https://user-images.githubusercontent.com/65120581/133038649-2f1f1068-2d54-47d9-ba4d-1537ddb2e0b7.png)
