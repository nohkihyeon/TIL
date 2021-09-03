# pthread
리눅스에서 Thread를 생성하고 관리하는 함수
프로세스 내에서 실행되는 여러 흐름의 단위  
여러개의 프로세스를 만드는 것이 아닌, 하나의 프로세스 안에 스레드를 생성하여 여러개의 스레드가 돌아가며 동작하게 한다.

## 1. 스레드의 장점

-   문맥교환(context switching) 시간이 짧다.
-   메모리공유로 인하여 시스템 자원 소모가 줄어든다.
-   응답시간이 단축된다.

## 2. pthread

POSIX Thread의 약자로 유닉스 계열 POSIX시스템에서 병렬적으로 작동하는 소프트웨어를 작성하기 위하여 제공하는 API
헤더파일 <pthread.h>



# pthread 사용시 주의할 점
pthread를 사용하는 멀티스레드 프로그램에서 메모리 누수가 발생할 수 있다.
pthread_create를 통해 새로운 thread가 작업을 처리하고 thread가 끝날 때 pthread_create 후 메모리를 회수 하지 않으면 문제가 발생할 수 있다.
그러므로 pthread_create 후에는 join 또는 detach를 수행하는 것이 바람직하다.

## 1. pthread_create(pthread_t *thread, const pthread_attr_t *attr, void *(*start_routine)(void *), void *arg)
- thread 생성된 스레드 식별자
- attr 스레드 특정 설정 (기본 NULL)
- start_routine 스레드 함수 (스레드로 분기해서 실행할 함수)
- arg 스레드 함수 인자


## 2. int pthread_join(pthread_t thread, void **thread_return);
리눅스에서 본래 프로세스는 메인스레드라고 지칭한다.
새로운 스레드를 추가로 생성하는 함수
원래 추가된 스레드의 코드가 실행이 되고 종료되면 스레드의 다음 코드를 읽어나가야하지만 추가된 스레드 중 아직 실행중인 스레드가 있다면 비정상적으로 동작 할 수 있다.
실행중인 스레드가 끝날때까지 기다려야 할 때 사용한다.
join을 사용하는 쓰레드는 종료되어야 할 시점을 명시해야 하는 작업에 사용한다.
- thread 기다릴 스레드 식별자
- thread_return 스레드의 리턴 값을 가져올 수 있는 포인터

## 3. pthread_detach(pthread_t th)
생성된 쓰레드가 종료될 때 알아서 자원을 시스템에게 반환하는 옵션
- thread : detach할 스레드 식별자
```c
#inlcude<pthread.h>

int pthread_detach(pthread_t thread);

..
if( pthread_create(&thread,&attr, &test_thread, NULL ) != 0 ) 
    return 1; 
pthread_detach(thread);

```



### 참조
[Pthread - create, exit, join, detach, mutex](https://probe29.tistory.com/48)
