# [네트워크] sockaddr, sockaddr_in, sockaddr_un 구조체

 >### [1. 관련 헤더 ](#1-관련-헤더) <br>
 >### [2. sockaddr 구조체 ](#2-sockaddr-구조체) <br>
 >### [3. sockaddr_in 구조체 ](#3-sockaddr_in-구조체) <br>
 >### [4. sockaddr_in6 구조체 ](#4-sockaddr_in6-구조체) <br>
 >### [5. sockaddr_un 구조체 ](#5-sockaddr_un-구조체) <br>

## 1. 관련 헤더
```c
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h> // superset of previous
```

## 2. sockaddr 구조체
- 소켓의 주소를 담는 구조체 역할
```c
struct sockaddr{
  u_short sa_family;    // address family. 2bytes
  char sa_data[14];     // IP address + Port number. 14bytes
};
```
- 2가지 멤버변수
  - sa_family : 주소체계를 구분하기 위한 변수 cf) u_short --> unsigned short
  - sa_data : 실제 주소를 저장하기 위한 변수 14bytes

## 3. sockaddr_in 구조체
sockaddr 구조체에서 sa_family가 AF_INET인 경우 사용하는 구조체
sockaddr을 그대로 사용하면 sa_data에 ip와 port번호가 조합되어 있어 읽고 쓰기가 불편
그래서 sockaddr_in을 사용 (IPv4 주소체계 사용)
```c
struct sockaddr_in{
  short sin_family;           // 주소 체계  AF_INET
  u_short sin_port;           // 16비트 포트 번호 network byte order
  struct in_addr sin_addr;    // 32 비트 IP 주소
  char sin_zero[8];           // 전체 크기를 16비트로 맞추기 위한 dummy
};

struct in_addr{
  u_long s_addr;              // 32비트 IP 주소를 저장 할 구초제 network byte order
};
```
- sin_family : 항상 AF_INET을 설정한다.
- sin_port : 포트번호(2byte) 0~65535
- sin_addr : 호스트 IP 주소
- in_zero : 8 byes dummy data, struct sockaddr구조체와 크기를 일치시키려는 목적

## 4. sockaddr_in6 구조체
IPv6 주소체계의 소켓주소를 사용하는 구조체
IPv4 사용되는 struct_sockaddr_in과 달리 struct_sockaddr_in6는 bzero() 또는 memset() 함수를 통해 0으로 초기화

```c
#include <netinet/in.h>

struct sockaddr_in6{
  sa_family_t    sin6_family;
  in_port_t      sin6_port;
  uint32_t       sin6_flowinfo;
  struct in6_addr sin6_addr;
  uint32_t       sin6_scope_id;
};

struct in6_addr{
  unsigned char s6_addr[16];
};
```
- sin6_family : 항상 AF_INET6
- sin6_port : IPv6 포트를 저장하는 변수
  - ntohs()와 htons()로 조작
- sin6_flowinfo : IPv6 헤더와 연관된 트래픽 클래스와 플로우 레이블을 포함
- sin6_addr : 16bBytes(128bits)의 주소 IPv6주소를 저장하는 변수
- sin6_scope_id : sin6_addr의 주소범위에 따라 달라지는 식별자를 포함할 수 있다.

## 5. sockaddr_un 구조체
AF_UNIX 또는 AF_LOCAL인 경우
- 하나의 시스템에서 서로다른 프로세스 사이의 통신에 사용되는 소켓의 주소를 지정하는데 사용하는 구조체
- 'Unix Domain Socket'라고도 한다.
```c
#define <sys/un.h>

#define UNIX_PATH_MAX 108

struct sockaddr_un {
  sa_family_t     sum_family;
  char            sum_path[UNIX_PATH_MAX];
};
```
- sun_family : 항상 AF_UNIX 값
- sun_path : 파일 시스템 경로를 지정한다. NULL로 끝나는 문자열(C string)이어야 한다. 최대 길이는 NULL terminator를 포함해서 108bytes이다.

