# socketpair 함수를 이용한 소켓 프로그램의 구현 - 프로세스 하나

서로 통신할 때면 두 개의 소켓을 생성해서 자료를 송수신한다. 이때 두 개의 소켓을 생성하는 가장 간단한 방법이 socketpair 함수를 사용하는 것이다.
개발자가 두 소켓 간에 별도의 연결 과정을 처리하지 않아도 두 개의 소켓을 생성하고 자동으로 연결까지 해준다.
개발자는 read와 write 함수를 호출해서 소켓을 통해 자료를 송수신하면 된다.

>### 함수
```c
#include <sys/types.h>
#include <sys/socket.h>

int socketpair(int domain, int type, int protocol, int sd[2]);
```
>### 반환값
>- 성공 시 : 0
>- 실패 시 : -1
>
>### 인자
>- domain : 사용할 프로토콜 패밀리
>- type : 해당 프로토콜에서 사용할 소켓 유형
>- protocol : IPPROTO_TCP, IPPROTO_UDP, 0 등의 프로토콜
>- sd[2] : sd[0], sd[1]

```C
#include<stdio.h>
#include<string.h>
#include <sys/socket.h>

int main(){
        int sd[2], result;                              // 2개의 소켓 생성 sd[0] sd[1]
        int n1, n2;
        char buf[BUFSIZ];
        char data[] = "This is from sd[0]";

        result = socketpair(PF_LOCAL, SOCK_STREAM, 0, sd);

        n1 = write(sd[0], data, strlen(data));
        printf("[send] %s\n", data);

        n2 = read(sd[1], buf, BUFSIZ);
        buf[n2] = '\0';
        printf("[received] %s\n", buf);

        close(sd[0]);
        close(sd[1]);
}

```
#### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128655240-2ce3c11a-2a1e-4b28-9b5b-65331ce5689c.png) <br>

![image](https://user-images.githubusercontent.com/65120581/128653650-db84e052-8e7e-426e-a6b3-9b7360a37e3c.png) <br>
socketpair 함수로 소켓 sd[0]과 sd[1]을 생성한 후, 각각의 소켓을 가리키는 소켓 기술자를 sd[0] sd[1]에 저장해서 반환한다.
wirte 함수를 호출해서 소켓sd[0]에 문자열 Thisis from sd[0]을 쓰면 소켓 sd[1]이 문자열 This is from sd[0]을 전송받음을 알 수 있다.



# socketpair 함수를 이용한 소켓 프로그램의 구현 - 두 프로세스 간에
프로세스 간의 통신 개념을 명확히 하기 우해서 fork 함수로 프로세스를 하나 더 생성
현재 실행중인 프로그램(프로세스)에서 fork 함수를 호출하면 자신과 동일한 프로세스가 하나 더 생성된다.
이 때 원래의 프로세스를 부모 프로세스라 하고, 새로 생성된 프로세스를 자식 프로세스라고 한다.
- 자식프로세스는 프로그램의 코드 부분, 지역 변수, 전역 변수를 비롯하여 레지스터와 기타 공유 자원 등 프로그램 실행에 필요한 모든 환경을 복제한다.
- 부모 프로세스와 자식 프로세스 사이에 소켓을 이용해서 자료를 송수신하는 프로그램
  - 1. socketpair 함수를 이용해서 한 쌍의 소켓을 생성
  - 2. fork 함수를 이용해서 자식 프로세스를 생성
  - 3. 부모 프로세스는 한쪽 소켓을 이용해서 문자열 From Parent를 전송하고, 자식 프로세스로부터 자료를 전송받아 출력
  - 4. 자식 프로세스는 연결된 한쪽 소켓을 이용해서 부모 프로세스로부터 전송받은 문자열 출력하고, 문자열 From Child를 부모 프로세스로 전송 <br>
![image](https://user-images.githubusercontent.com/65120581/128654114-98c06ac0-8639-4f38-84c2-1cb27b94e130.png) <br>

```c
#include<stdio.h>
#include<string.h>
#include<sys/socket.h>

char data1[] = "From Parent";
char data2[] = "From Child";

int main(void){
        int pid;
        int sd[2], result;
        char buf[BUFSIZ];

        result = socketpair(PF_LOCAL, SOCK_STREAM, 0, sd);

        pid = fork();
        if(pid >0){
                close(sd[1]);

                read(sd[0], buf, BUFSIZ);
                printf("[parent:%d] %s\n", getpid(), buf);

                write(sd[0], data1, sizeof(data1));

                close(sd[0]);
        } else if(pid == 0){
                close(sd[0]);
                write(sd[1], data2, sizeof(data2));

                read(sd[1], buf, BUFSIZ);
                printf("[child:%d] %s\n", getpid(), buf);

                close(sd[1]);
        } else if(pid == -1){
                perror("fork()");
        }
}

```
#### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128655267-ae009ef8-88fc-4ae1-97e6-f2ece85a48a2.png)
<br>
## fork 함수로 자식 프로세스를 생성
프로세스가 fork 함수를 만나면 현재 실행 중인 프로세스의 이미지를 그대로 복사해서 새로운 프로세스를 만든다.
프로그램을 실행하면 주기억 장치에 로딩되어 실행 중인 상태가 되는데, 실행 중인 프로그램(프로세스)의 구성
![image](https://user-images.githubusercontent.com/65120581/128655330-60410a03-48c5-4cff-9ffb-f29f68cd3e02.png)
- 코드(Code) : 프로그램 코드가 저장되는 공간
- 스택(Stack) : 지역 변수가 저장되는 공간
- 자료(Data) : 전역 변수가 저장되는 공간
- 힙(Heap) : 프로그램 실행 중에 동적으로 할당되는 공간
- 레지스터(Register) : 프로그램 실행 시 자료 처리 등에 사용되는 공간
- 자원(Resource) : 파일 기술자 등 자원에 할당되는 
