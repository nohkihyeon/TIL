# 포트(Port)
인터넷에 연결된 컴퓨터 간에 이루어지는 자료의 교환은 클라이언트/서버 구조를 기반으로 한다.
클라이언트의 서비스 요청과 서버의 응대 처리는 각각의 소켓이 연결된 포트를 통해 이루어진다.
컴퓨터 시스템은 0~65535까지의 포트를 두고 있다.
- 웹 서버, 이메일, FTP, 텔넷(Telnet) 등 자주 사용하는 서비스에서 해당 서비스를 어느 시스템에서 제공하던 간에 대부분 동일한 포트로 이루어진다.
- 웹 서버는 웹 브라우저가 80번 포트에 연결하면 웹 서버에 연결된다. 'http://www.naver.com/index.html'처럼 특정 포트를 명시하지 않아도 80번 포트에 연결되는 이유는 기본(default) 설정이기 때문
- 만일 호스트 A에는 웹서버가 80번 포트에 연결되어 실행 중이고, 호스트 B에는 웹서버가 90번 포트에 연결되어 실행  중이면 웹 브라우저에서 호스트 A와 호스트 B의 웹 서버에 연결 할 때마다 해당 웹 서버의 포트를 다시 명확하게 해야 한다.
- http://www.naver.com:8090/index.html'처럼 특정 포트를 명시하는 경우가 종종 있다.
- 인터넷 할당 번호 관리 기관 IANA(Internet Assigned Numbers Authority)S는 서비스와 포트 번호에 대한 규약을 정하고 관리한다.
- IANA 3개 영역
  - Well-known Port, 0~1023
  - Registered Port, 1024~49151
  - Dynamic and/or Private Port, 19512~65535
 
## 포트별 서비스 내역을 출력하는 프로그램

/etc/services 파일에서 정보를 읽어오는 프로그램을 구현
### 함수
```c
#include <netdb.h>

struct servent *getservent(void);
```
>### 반환값
>- 성공 시 : servent 구조체에 대한 포인터
>- 실패 시 : NULL

### 구조체
```c
struct servent{
  char *s_name;       // 서비스 공식 명칭
  char **s_aliases;   // 별명 명칭
  int s_port;         // 포트 번호
  char *s_proto;      // 사용하는 프로토콜
```


```c
#include <stdio.h>
#include <netdb.h>

int main() {
        struct servent *p;
        int n;

        while(1){
                if (!(p = getservent())) break;

                printf("%s\t %d/%s \t", p->s_name, ntohs(p->s_port), p->s_proto);
                for(n=0; p->s_aliases[n] != NULL; n++)
                        printf("%s ", p->s_aliases[n]);
                printf("\n");
        }
}

```

## IP 주소 변환
시스템에서 주소를 표현하는 3가지 방식
|표현 방식|예|설명|
|------|---|---|
|도메인 이름|www.eluon.com|문자열 형식|
| IP 주소 문자열 (Dotted Decimal)|203.249.39.3|문자열 형식, 10진수와 점으로 구성|
|IP 주소(Binary)|0xcbf92730|이진형식|


>### 함수
```C
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int inet_aton(const char *cp, struct in_addr *inp);

```
>### 반환 값
>- 성공 시 : 0이 아닌 값
>- 실패 시 : 0
>### 인자
>- cp: 10진수와 점으로 구성된 문자열 형식의 IP 주소
>- inp : 네트워크 바이트 순서로 된 이진 값을 저장하는 32비트 IP 



### 구조체

```c
struct in_addr {
  u_longs_addr;       //IP 주소(네트워크 바이트 순서의 이진 값)
}
```


### 함수
```c
#include <arpa/inet.h>

char * inet_ntoa(struct in_addr in);

```
>### 반환 값
>- 성공 시 : 10진수와 점으로 구성된 문자열 형식의 IP 주소
>- 실패 시 : -1
>### 인자
>- in : 32 비트 IP 주소를 저장할 구조체 변수

```c
#include <stdio.h>
#include <arpa/inet.h>

int main() {
        struct                  in_addr inp;
        const char *    ipAddr = "203.249.39.3";
        char                    *addr;

        inet_aton(ipAddr, &inp);
        printf("ip (dotted decimal_[%s] -> ip(binary)[0x%x]\n", ipAddr, ntohl(inp.s_addr));

        addr = inet_ntoa(inp);
        printf("ip(binary[0x%x] -> ip(dotted decimal)[%s]\n", ntohl(inp.s_addr), addr);
}

```

#### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128664969-76b75328-9c85-4476-a4b5-7a25a310e26b.png)





