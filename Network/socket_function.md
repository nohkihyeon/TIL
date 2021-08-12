# C reference

## 1. inet_pton 함수
- inet_pton() 함수는 사람이 알아보기 쉬운 텍스트 형태의 IPv4와 IPv6 주소를 binary 형태로 변환 기능
- Header files
  - `#include <arpa/inet.h>`
### API Prototype
```c
int inet_pton(int af, const char *src, void *dst);
```
- af[input] : address family를 지정. src의 문자열이 IPv4/IPv6 중 하나를 함수에 알린다.
- src[input] : 문자열 형태의 IP주소를 넣는다
- dst[output] : src를 binary형태로 변환 후 복사된 메모리의 포인터
### Description
- 이 함수는 af의 address family가 가리키는 네트워크 주소 구조체에 src의 문자열 값을 반환 한 후, 이 네트워크 구조체를 dst에 복사
- af는 반드시 AF_INET 혹인 AF_INET6 둘 중 하나여야 한다
- dst는 network byte order로 작성

> #### AF_INET
> - src 가리키는 문자열은 IPv4 네트워크 주소이고, 점으로 자리를 구분하는 10진수 주소 형태이다.
> - 172.100.100.100와 같은 형태 이며 0~255 범위의 10진수가 온다.
> - in_addr 구조체로 변환 된 후, 4byte크기를 가지는 dst로 복사된다.
> #### AF_INET6
> - src 가리키는 문자열은 IPv6 네트워크 주소이다.
> - in_addr 구조체로 변환된 후 16byte크기를 가지는 dst로 복사된다.
> - src에 넣을 수 있는 IPv6 주소 표현 방식은 IPv4 주소 표현 방식에 비해 다양하다.
 
### Return Value
- inet_pton() 성공시 1반환
- 0이 반환되는 경우 src의 문자열이 나타내는 네트워크 주소가 af에 명시된 address family의 유효한 값이 아닌 경우
- -1이 반환되는 경우 af가 적절한 address family값이 아닌 경우 이때 errno이 EAFNOSUPPORT값으로 설정
### 알아둘 내용
inet_aton( ) 및 inet_addr( )와는 달리, inet_pton( )은 IPv6주소를 지원한다. 
반면에, inet_pton( )은 점 3개와 10진수 표현법의 (dotted-decimal notation) IPv4주소만 받아들인다. 
inet_aton( )및 inet_addr( )은 좀더 일반적인 표현법인 'numbers-and-dots notation' 에 대해서도 받아들인다.
'numbers-and-dots notation'는 이와 같다.
hexadecimal and octal number formats, and formats that don't require all four bytes to be explicitly written.  
### 같이 보면 좋은 함수
getnameinfo( ) <br>
[getaddrinfo( )](#3-addrinfo-구조체) <br>
[inet_ntop( )](#2-inet_ntop-함수) <br>

## 2. inet_ntop 함수
- inet_ntop IPv4와 IPv6 주소를 binary 형태에서 사람이 알아보기 쉬운 텍스트 형태로 전환해준다.
- Header files
`#include <arpa/inet.h>`
### API Prototype
```c
const char *inet_ntop(int af, const void *src, char * dst, socklen_t size);
```
### Description
- 네트워크 주소 구조체인 src를 char 문자열 dst로 변환해주는 함수
- 네트워크 주소는 IPv4 혹은 IPv6가 될 수 있다.
- af를 참고하여 src를 해석
- dst는 반드시 NULL 포인터가 아니여야한다.

> #### AF_INET
> - src는 struct in_addr (network byte order임) 구조체를 가리킨다.
> - 버퍼 dst는 반드시 메모리가 할당되어야 하며, 크기는 최소한 INET_ADDRSTRLEN 크기만큼의 바이트 수
> #### AF_INET6
> - 버퍼 dst는 반드시 메모리가 할당되어야 하며, 크기는 최소한 INET6_ADDRSTRLEN 크기만큼의 바이트 수

### Return Value
- 성공하면 inet_ntop() 는 dst를 가리키는 포인터를 반환
- 에러 발생시 NULL, errono에 에러와 관련된 값이 설정

### 같이 보면 좋은 함수
getnameinfo( ) <br>
getaddrinfo( ) <br>
[inet_pton( )](#1-inet_pton-함수) <br>


## 3. addrinfo 구조체
### SUMMARY
- addrinfo 구조체는 네트워크 주소정보(인터넷 주소)와 호스트 이름을 표현하는데 사용
- 정보는 bind(), connect() 호출 시 입력 파라미터에 사용될 수 있다.
- getaddrinfo() 함수 호출 시, hint 정보를 알리는 입력 파라미터로 사용할 수 있으며, getaddrinfo() 함수의 결과값을 전닿하는 출력 파라미터로도 사용
### Header files
`#include <netdb.h>`
### 구조체 원형
```c
struct addrinfo {
  int ai_flags;              /* 추가적인 옵션을 정의 할 때 사용함. 여러 flag를 bitwise OR-ing 하여 넣는다 */
  int ai_family;             /* address family를 나타냄. AF_INET, AF_INET6, AF_UNSPEC */
  int ai_socktype;           /* socket type을 나타냄. SOCK_SREAM, SOCK_DGRAM */
  int ai_protocol;           /* IPv4와 IPv6에 대한 IPPROTO_xxx와 같은 값을 가짐. */
  socklen_t ai_addrlen;      /* socket 주소인 ai_addr의 길이를 나타냄 */
  char *ai_canonname;        /* 호스트의 canonical name을 나타냄 */
  struct sockaddr *ai_addr;  /* socket 주소를 나타내는 구조체 포인터 */
  struct addrinfo *ai_next;  /* 주소정보 구조체 addrinfo는 linked list이다. 다음 데이터의 포인터 */
}
```
### 같이 보면 좋은 함수
getaddrinfo()

