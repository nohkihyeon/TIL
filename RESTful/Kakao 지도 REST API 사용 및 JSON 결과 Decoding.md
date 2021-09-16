#  Kakao 지도 REST API 사용 및 JSON 결과 Decoding

## kakao developers 
- [카카오디벨로퍼스](https://developers.kakao.com/)는 카카오 개발자 웹사이트이다.
- 카카오 API는 개발자 웹사이트에 등록된 각각의 애플리케이션 정보 기반으로 동작하므로, 카카오 API를 사용하려면 개발자 웹사이트에 앱을 등록해야 한다.
  >- 카카오 플랫폼
  > 카카오 플랫폼 구조와 지원하는 오픈 API 종류, 카카오계정, 연결 등 플랫폼 이용에 필요한 참고 지식은 [카카오 플랫폼 이해하기 가이드](https://developers.kakao.com/docs/latest/ko/getting-started/platform)에서 안내

- 앱 만들기 
![image](https://user-images.githubusercontent.com/65120581/133536658-9cb2b7f7-755f-4981-93fb-7afac0be148a.png)
- 앱 키를 이용해서 API 통신
![image](https://user-images.githubusercontent.com/65120581/133536832-a51000f1-9f70-4b44-922a-53c3716105e8.png)

## 키워드로 장소 검색

- 질의어에 매칭된 장소 검색 결과를 지정된 정렬 기준에 따라 제공
- 현재 위치 좌표, 반경 제한, 정렬 옵션, 페이징 등의 기능을 통해 원하는 결과를 요청 할 수 있음
- 앱 REST API 키를 헤더에 담아 GET으로 요청
- 원하는 검색어와 함게 결과 형식 파라미터의 값을 선택적으로 추가 할 수 있음
- 응답은 JSON과 XML 형식을 지원

> 참조[문서>로컬>개발가이드>](https://developers.kakao.com/docs/latest/ko/local/dev-guide)


## Request
- URL
```restapi
GET /v2/local/search/keyword.{format} HTTP/1.1
Host: dapi.kakao.com
Authorization: KakaoAK {REST_API_KEY}
```

## Response




## 소스코드
```C
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

void error_handling(char* msg);
char buffer[BUFSIZ];

int main(int argc, char *argv[]){
        int status;
        struct addrinfo hints;
        struct addrinfo *servinfo, *tmp;                // 결과를 저장할 변수
        char host[256];
        int c_socket;
        struct sockaddr_in c_addr;
        char message_fmt[] = "GET %s HTTP/1.1\r\nHost: %s\r\nAuthorization: KakaoAK %s\r\n\r\n";

        int left_len, sent_len, recv_len, bytes;
        char rcvBuffer[BUFSIZ];

        c_socket = socket(PF_INET, SOCK_STREAM, 0);


        memset(&hints, 0, sizeof(hints));               // hints 구조체의 모든 값을 0으로 초기화
        hints.ai_family = AF_UNSPEC;                    // IPv4 IPv6 상관없이 결과 모두 반환
        hints.ai_socktype = SOCK_STREAM;                // TCP stream sockets

        if(argc !=4){
                fprintf(stderr, "Usage : <host> <resource> <API_KEYS>\nex) ./search_keyword dapi.kakao.com  /v2/local/search/keyword.json?query=<query> <API_KEYS>\n");
                return -1;
        }

        status = getaddrinfo(argv[1], "80", &hints, &servinfo);

        tmp = servinfo;
        getnameinfo(tmp->ai_addr, tmp->ai_addrlen, host, sizeof(host), NULL, 0, NI_NUMERICHOST);

        sprintf(buffer, message_fmt, argv[2], argv[1], argv[3]);

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(host);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(80);


        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                error_handling("Can not connect");
                close(c_socket);
                return -1;
        }
        left_len = strlen(buffer);
        sent_len = 0;
        do{
                bytes = write(c_socket, buffer + sent_len, left_len - sent_len);
                if(bytes  <0)
                        error_handling("write()Error\n");
                if(bytes ==0)
                        break;
                sent_len +=bytes;
        }while(sent_len < left_len);

        left_len = strlen(rcvBuffer);
        sent_len = 0;
        do{
                memset(rcvBuffer, 0, sizeof(rcvBuffer));
                bytes = read(c_socket, rcvBuffer, 1024);
                fprintf(stdout, "%s ", rcvBuffer);

                if(bytes < 0)
                        error_handling("read() error\n");
                if(bytes ==0)
                        break;
                recv_len +=bytes;
        }while(1);

        if(recv_len == left_len)
                error("Error\n");


        close(c_socket);
        freeaddrinfo(servinfo);

}

void error_handling(char* msg){
        fputs(msg, stderr);
        fputc('\n', stderr);
        exit(1);
}


```
## 결과화면
![image](https://user-images.githubusercontent.com/65120581/133582525-422e8054-f789-45c1-abf2-0222abd94994.png)
<br>
![image](https://user-images.githubusercontent.com/65120581/133582451-fe252654-124a-4edf-8f83-d3987b835195.png)

