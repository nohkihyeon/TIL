# Signal 함수

## 시그널
- 특정 상황이 되었을 때 운영체제가 프로세스에게 해당 상황이 발생했음을 알리는 일종의 메시지
  - SIGALRM : alarm 함수호출을 통해서 등록된 시간이 된 상황
  - SIGINT  : CTRL+C가 입력된 상황
  - SIGCHLD : 자식 프로세스가 종료된 상황

## 시그널 등록
- 특정 상황에서 운영체제로부터 프로세스가 시그널을 받기 위해서 해당 상황에 대해서 등록의 과정을 거쳐야 한다.

## signal과 signal함수
- 프로세스와 운영체제가 하는 일
  - 자식 프로세스가 종료되면 프로세스가 zombie_handler라는 이름의 함수를 호출
    - 프로스세 : zombie_handler라는 함수 생성
    - 운영체제 : 자식 프로세스가 종료되면 대신 zombie_handler 함수 호출  
- 시그널 등록 함수
  - 매개변수 선언 : int signo, void(*func)(int)
    - signo : 특정 상황에 대한 정보
    - void(*(func)(int) : 특정 상황에서 호출될 함수의 주소 값(포인터)를 전달
    - 첫 번째 인자를 통해 명시된 상황 발생 시, 두 번재 인자로 전달된 주소 값의 함수가 호출
```c
#include <signal.h>

void (*signal(int signo, void (*func)(int)))(int);    // 시그널 발생시 호출되도록 이전에 등록된 함수의 포인터 변환
```

- signal함수를 통해서 등록 가능한 특정 상황과 그 상황에 할당된 변수
- `signal(SIGCHLD, mychild);` : 자식 프로세스 정료 시 mychild 함수 호출
- `signal(SIGALRM, timeout);` : alarm 함수 호출을 통해서 등록된 시간이 지나면 timeout 함수를 호출
- `signal(SIGINT, keycontrol);` : CTRL+C가 입력되면 keycontrol 함수 호출
- 시그널이 등록되면, 함께 등록된 함수의 호출을 통해서 운영체제는 시그널의 발생을 알린다.

### 시그널 핸들링 예제1 - 시그널을 발생시켜 sleep 함수의 호출 -> 블로킹 상태 프로세스 깨운다.
```c
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

void timeout(int sig) {
	if (sig == SIGALRM) {
		puts("Tiem out!");
	}
	alarm(2);
}
void keycontrol(int sig) {
	if (sig == SIGINT)
		puts("CTRL+C pressed");
}
int main(int argc, char *argv[])
{
	int i;
	signal(SIGALRM, timeout);
	signal(SIGINT, keycontrol);
	alarm(2);  // 2초 뒤에 SIGALRM 시그널 발생
	for (i = 0; i < 3; i++) {
		puts("wait....");
		sleep(100);
	}
	return 0;
}
```
![image](https://user-images.githubusercontent.com/65120581/128983486-92fd4916-2621-4544-91f4-ed4474d90356.png)
- CTRL+C 누르면 -> sleep 깨짐


- sigaction 함수
  - signal 함수 대체 가능, 안정적
  - signal 함수는 유닉스 계열의 운영체제 별로 동작 방식 차이를 보임
  - sigaction 함수는 차이가 없다.
 ```c
 #include <signal.h>
 
 int sigaction(int signo, const struct sigaction *act, struct sigaction *oldact);
 // 성공 시 0
 // 실패시 -1 반환
 ```
 - signo : signal 함수와 마찬가지로 시그널의 정보를 인자로 전달
 - act : 첫 번째 인자로 전달된 상수에 해당하는 시그널 발생 시 호출될 함수(시그널 핸들러)의 정보 전달
 - oldact : 이전에 등록되었던 시그널 핸들러의 함수 포인터를 얻는데 사용되는 인자

```c
struct sigaciton{
  void (*sa_handler)(int);
  sigset_t sa_mask;
  int sa_flags;
}
```
- sigaction 구조체 변수를 선언해서, 시그널 등록 시 호출될 함수의 정보를 채워서 위의 함수 호출 시 인자로 전달
- sa_mask의 모든 비트는 0, sa_flags는 0으
### 시그널 핸들링 예제2 - 시그널 핸들링을 통한 좀비 프로세스의 소멸 
```c
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <signal.h>
#include <sys/wait.h>

void read_childproc(int sig) {
	int status;
	pid_t id = waitpid(-1, &status, WNOHANG);
	if (WIFEXITED(status)) {
		printf("Removed proc id: %d \n", id);
		printf("Child send: %d \n", WEXITSTATUS(status));
	}
}

int main(int argc, char *argv[])
{
	pid_t pid;
	struct sigaction act;
	act.sa_handler = read_childproc;  // 자식 프로세스가 끝나면 read_childproc 함수 호출하라
	sigemptyset(&act.sa_mask);
	act.sa_flags = 0;
	sigaction(SIGCHLD, &act, 0);

	pid = fork();
	if (pid == 0) {
		puts("Hi! I'm child process");
		sleep(10);
		return 12;
	}
	else {
		printf("Child proc id: %d \n", pid);
		pid = fork();
		if (pid == 0) {
			puts("Hi! I'm child process");
			sleep(10);
			exit(24);
		}
		else {
			int i;
			printf("Child proc id: %d \n", pid);
			for (i = 0; i < 5; i++) {
				puts("wait....");
				sleep(5);   // 시그널이 발생하면 깨어남
			}
		}
	}
	return 0;
}
```

![image](https://user-images.githubusercontent.com/65120581/128998502-7ae3602d-29ff-4e55-8ebe-16ad6428c41c.png)
