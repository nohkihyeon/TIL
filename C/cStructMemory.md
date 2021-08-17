# c언어 구조체의 메모리 사이즈
1. sizeof 연산자
 - 해당 변수 및 데이터타입의 크기를 정수형태로 되돌려 준다.
```C
int a;
double b;
char c;

sizeof(a)   // 4
sizeof(b)   // 8
sizeof(c)   // 1
```
2. 구조체의 크기
```C
#include<stdio.h>

typedef struct stu{
    char a;
    int b;
}S;

void main() {
    struct stu a;
    printf("S의 메모리 공간 크기 = %d\n", sizeof(S));
    printf("S의 메모리 공간 크기 = %d\n", sizeof(a));
}
``` 
- a는 1바이트 문자형, b는 4바이트 정소형 그렇다면 5바이트???
![image](https://user-images.githubusercontent.com/65120581/126278996-060c4b89-ecf3-47f3-b1d7-4bcdfae7c9e3.png)
    ```
    $ gcc c.c -o c // c.c파일을 gcc로 c source를 컴파일
    ```
- 결과는 8바이트가 나온다.
- 이유는 구조체가 메모리 공간을 잡는 원리에 있다.
  - A. 각각의 멤버를 저장하기 위해서는 기본 4바이트 단위로 구성된다.
  - B. 구조체 각 멤버중에 가장 큰 멤버의 크기에 영향을 받는다.

-cf)
### 예시1 
- a는 1바이트 b는 4바이트
  ```C
  typedef struct student
  {
  char a;
  int b;
  }S;
  ```
 ![image](https://user-images.githubusercontent.com/65120581/126281107-0998a97b-78c2-43e1-8e04-b0d66538c57c.png)
- a는 1바이트 b는 4바이트이지만, 실제로는 A때문에 3바이트의 여유공간을 두게된다.
### 예시2
  ```C
  typedef struct student
  {
  char a;
  char b;
  int c;
  }S;
  ```
![image](https://user-images.githubusercontent.com/65120581/126281301-a8048be8-62a0-4967-b548-fb4a9daa1c06.png)
- a는 1바이트 b는 1바이트, C는 4바이트의 공간을 두게된다.
### 예시3
  ```C
  typedef struct student
  {
  char a;
  int c;
  char b;
  }S;
  ```
![image](https://user-images.githubusercontent.com/65120581/126281830-2647a049-efdf-4524-8852-2b57af611473.png)
- a는 1바이트 b는 1바이트, C는 4바이트의 여유공간을 두게된다. 선언할 때 같은 자료타입이지만 배열하는 순서에 의해 할당되는 메모리공간이 달라짐을 이해할 수 있다.
- 따라서 같은 데이터끼리, 작은 자료형을 앞에다가 선언하는 것이 유리하다.
### 예시4
  ```C
  typedef struct student
  {
  char a;
  double b;
  }S;
  ```
![image](https://user-images.githubusercontent.com/65120581/126282439-eac54a4f-b356-4190-ba55-1b849c7b3db6.png)
- a는 1바이트 b는 8바이트이므로 기본적인 공간을 8바이트로 잡게된다. B의 규칙을 따른다.
