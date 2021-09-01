# Signal과 Zombie Process
좀비프로세스가 만들어지는 이유는 커널에서 자식 프로세스가 종료되는 시점에서의 반환 값을 부모 프로세스가 읽어 갈 때까지 자식 프로세스를 완전히 소멸시키지 않기 때문이다.
통상 자식 프로세스는 부모 프로세스가 자식 프로세스의 반환 값을 읽어 갈 수 있도록 종료하면서 시그널 SIGCHLD 발생시킨다.
커널은 부모 프로세스가 SIGCHLD 시그널을 받아 처리할때까지는 자식 프로세스를 종료시키지 않고, 자식 프로세스에 할당된 메모리는 회수하지만 프로세스 테이블의 항목은 그대로인 좀비 프로세스로 둔다.
이런 좀비프로세스는 프로세스가 종료된 후에도 남아서 각종 자원을 점유해 성능에 악영향을 준다.

## 좀비프로세스 해결법
- 자식 프로세스가 종료될 때 발생하는 SIGCHLD 시그널을 부모 프로세스가 받아서 처리한다
```C
(void) signal(SIGCLD, SIG_IGN);
```
- 이러면 자식 프로세스가 좀비 프로세스로 되는 것을 방지 할 수 있다.


```C
#include<stdio.h>
#include <signal.h>

struct sigaction act_new;
struct sigaction act_old;

void sig_handler(int signo){
        printf("First Ctrl-C pressed !!\n");
        sigaction(SIGINT, &act_old, NULL);
}

int main(void){
        act_new.sa_handler = sig_handler;
        sigemptyset(&act_new.sa_mask);
        act_new.sa_flags = 0;

        sigaction(SIGINT, &act_new, &act_old);
        while(1){
                printf("sigaction test \n");
                sleep(1);
        }
}

```

![image](https://user-images.githubusercontent.com/65120581/131623545-15113593-73a4-459b-94f3-44cebae42294.png)
