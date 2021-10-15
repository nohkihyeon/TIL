# C언어 extern 외부 함수

파일을 분할을 해서 작성을 하기 위한 목적으로 사용한다.

- main.c
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
