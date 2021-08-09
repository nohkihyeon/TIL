# 문자열 형식의 도메인 -> 4바이트 네트워크 바이트 순서로된 이진 형식의 주소

### 함수
```c
#include <netdb.h>

struct hostent * gethostbyname(const char *name);
```
>### 반환 값
>- 성공 시 : hostent 구조체 주소
>- 실패 시 : NULL
>### 인자
>- name : 도메인 이름
인자  name이 가르키는 도메인 이름에 해당하는 서버의 정보를 네트워크상에서 검색해서 구조체 hostent에 넣어 준다.

### 함수
```c
#include <netdb.h>

struct hostent * gethostnbyaddr(const char *addr, int len, int type);
```
>### 반환 값
>- 성공 시 : hostent 구조체 주소
>- 실패 시 : NULL
>### 인자
>- addr : 32비트 이진 값으 IP 주소
>len : 주소 길이
>type : 주소 유형


### 구조체
```c
struct hostent {
  char *h_name;         // 도메인 이름
  char **h_aliases;     // 별명들
  int h_addrtype;       // 주소 유형
  int h_length;         // 주소 길이
  char **h_addr_list;   // 네트워크 바이트 순서로 된 이진 형식의 IP 주소
}
```


```c
#include<stdio.h>
#include <netdb.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <netinet/in.h>

int main() {
        struct in_addr addr;
        struct hostent *host;
        const char *hostName = "www.eluon.com";
        int i;

        if((host = gethostbyname(hostName)) == NULL ) {
                printf("gethostbyname() error - check network\n");
                exit(-1);
        }

        printf("official name = %s \n", host->h_name);

        for(i=0; host->h_aliases[i]; i++)
                printf("aliases %d : %s \n", i+1, host->h_aliases[i]);

        printf("address type = %d\n", host->h_addrtype);
        printf("address length = %d\n", host->h_length);

        i =0;
        while(host->h_addr_list[i] != NULL) {
                memcpy(&addr.s_addr, host->h_addr_list[i], 4);
                printf("address = %s(0x%x)\n", inet_ntoa(addr), ntohl(*(long*)host->h_addr_list[i]));
                i++;
        }
}

```

#### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128675893-d1f9c3c7-0800-49a0-a6dd-d1c34cba2eac.png)

