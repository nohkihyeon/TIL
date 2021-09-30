# #ifndef ~ #endif
- 전처리기에서 문제가 되지 않는다. 컴파일러는 구조체의 정의가 두 번 등장했을 때 에러를 인식한다.
- 중복정의를 피하기 위한 매크로



## 헤더파일에 매크로 #ifndef ~ #endif의 선언을 포함하는 것이 좋다.
- 헤더파일 중복으로 발생할 수 있는 문제를 막는 가장 좋은 방법이다.
- 중복삽입으로 인한 문제가 발생하지 않는다.

#### 1. stdiv.h
```c
  #ifndef __STDIV_H__
  #define __STDIV_H__

  typedef struct div
  {
      int quotient;   // 몫
      int remainder;  // 나머지
  } Div;
  #endif
```
- __STDIV_H__라는 매크로를 정의한 적이 없다면 #ifndef  ~ #endif 사이에 있는 모든 내용을 main.c에 포함시킨다.
- 구조체의 중복 선언을 방지할 수 있다.
#### 2. intdiv.h
```c

  #ifndef __INTDIV_H__
  #define __INTDIV_H__

  #include "stdiv.h"
  Div IntDiv(int num1, int num2);
  #endif
```
- 해당 파일의 #ifndef ~ #endif 매크로를 이용해서 중복 삽입의 문제를 미연에 방지한다.
#### 3.  intdiv.c
```c
  #include "stdiv.h"

  DIV IntDiv(int num1, int num2)
  {
      Div dval;
      dval.quotient=num1/num2;
      dval.remainder=num1%num2;
      return dval;
  }
```
- DIV 구조체를 선언하기 위해 헤더파일 추가
#### 4. main.c
```c
  #include <stdio.h>
  #include "stdiv.h"
  #include "intdiv.h"

  int main(void)
  {
      Div val = IntDiv(5, 2);
      printf("몫: %d \n", val.quotient);
      printf("나머지: %d \n", val.remainder);
      return 0;
  }
```
- #include "stdiv.h"  stdiv.h 삽입
- #include "intdiv.h" stdiv.h 삽입
- 2번째 삽입은  #ifndef ~ #endif 통해 중복삽입으로 인한 문제가 발생하지 않는다.
