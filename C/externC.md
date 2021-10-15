# C언어 extern 외부 함수

파일을 분할을 해서 작성을 하기 위한 목적으로 사용한다.

- externTest.c
```c
#include <stdio.h>

extern int add(int x, int y);   // 다른파일에 선언된 함수
extern int Number;              // 다른파일에 선언된 변수

int main(){
  int a = 10;
  int b = 20;
  
  Number = add(a, b);
  printf("%d\n", Number);
  
  return 1;
}
```
- addFunc.c
```c
#include <stdio.h>

int add(int x, int y);
int Number =0;

int add(int x, int y){
  Number = x + y;
  
  return Number;
}
```
## 결과화면
![image](https://user-images.githubusercontent.com/65120581/137439179-ede5e177-fd05-4e4e-9da0-1c4ab782cf2e.png)


# 조건부 컴파일
- 조건을 평가하며 코드를 컴파일 대상에 포함시키거나 제외시키는 역할을 한다.
- 주로 매크로 상수의 존재 여부나 값에 대한 평가식이 사용된다.
```c
#if 조건1
코드 1
#elif 조건2
코드2
#else
코드3
#endif
```
## 예시화면
![image](https://user-images.githubusercontent.com/65120581/137440109-f8d82c9d-68fa-44d9-87da-b0120a264fe6.png)


![image](https://user-images.githubusercontent.com/65120581/137440197-7f65d4eb-4d4d-467e-b596-b67b9ada0c4f.png)

