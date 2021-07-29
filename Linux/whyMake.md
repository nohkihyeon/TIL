# make와 Makefile

## make
- SHELL에서 컴파일 할 때 종종 보이는 make 명령어
- Makefile이 있는 디렉토리에서 make만 치면 컴파일이 실행
- 파일 관리 유틸리티
- 파일 간 종속관계를 파악하여 Makefile에 적힌 대로 컴파일러에 명령하여 SHELL 명령이 순차적으로 실행되게 한다.

## make 장점
- 각 파일에 대한 반복적 명령의 자동화로 인한 시간 절약
- 프로그램의 종속 구조를 빠르게 파악 할 수 있으며 관리가 용이
- 단순 반복 작업 및 재작성을 최소화

## 예제 diary_exe <br>
![image](https://user-images.githubusercontent.com/65120581/127274596-5223ef5f-3286-4601-8cef-c2e5f4c7168e.png)
### 1. diary.h 
- 3개의 c 파일이 include 할 헤더파일 생성
```C
#include <stdio.h>
void memo();
void calendar();
```
### 2. 재료로 사용 될 C파일
```linux
vi memo.c
vi calendar.c
vi main.c
```
- memo.c
```C
#include "diary.h"
void memo(){
  printf("I'm function Memo \n");
  }
```
- calendar.c
```C
#include "diary.h"
void calendar(){
  printf("I'm function Calendar() \n");
  }
```
- main.c
```C
#include "diary.h"

int main(void){
  memo();
  calendar();
  return 0;
  }
```
### 3. 생성된 파일 확인하기
![image](https://user-images.githubusercontent.com/65120581/127275752-bda409ab-ec48-498f-a268-52f988109560.png)


## 기본적인 컴파일 과정
- gcc를 이용한 컴파일

### 1. c파일에서 object 파일 생성
```linux
gcc -c -o memo.o memo.c
gcc -c -o calendar.o calendar.c
gcc -c -o main.o main.c
```
![image](https://user-images.githubusercontent.com/65120581/127276116-29fb7952-dba6-4d6b-8e61-49a152922f12.png)

### 2. 각 object 파일을 묶어 컴파일을 통해 diary_exe 실행파일 생성
```linux
gcc -o diary_exe main.o memo.o calendar.o
```
![image](https://user-images.githubusercontent.com/65120581/127276358-dcdcbdef-1493-4cbd-9af0-66cbe7062290.png)

### 3. 결과 확인하기
![image](https://user-images.githubusercontent.com/65120581/127290511-ca189e90-9b71-481f-b373-c941f5d33493.png)
- 기존의 컴파일 과정이 지금까지는 3개의 파일만 하기 때문에 어렵지 않다.
- 3번만 명령해 주면 간단하다.
- 하지만 1,000,000개 c 파일이 있다면 100만 개가 필요하다.

## Makefile의 구성

### Makefile의 구조
- 목적파일(Target) : 명령어가 수행되어 나온 결과를 저장할 파일
- 의존성(Dependency) : 목적파일을 만들기 위해 필요한 재료
- 명령어(Command) : 실행 되어야 할 명령어들
- 매크로(Macro) : 코드를 단순화 시키기 위한 방법

```
CC = gcc

target 1 : dependency1 dependency2
        command1
        command2
target 2 : dependency3 dependency4
        command3
        command4
```
### Makefile 작성규칙
- 목표파일 : 목표파일을 만드는데 필요한 구성요소들
- (tab)목표를 달성하기 위한 명령1
- (tab)목표를 달성하기 위한 명령2
  - Makefile에 정의한 string으로 치환
  - 명령어의 시작은 반드시 tab으로 시작
  - Dependency가 없는 target도 사용 가능 

- `$ vi Makefile`
```vim
diary_exe : memo.o calendar.o main.o
  gcc -o diary_exe memo.o calendar.o main.o
  
memo.o : memo.c
  gcc -c -o mmo.o memo.c
calendar.o : calendar.c
  gcc -c -o calendar.o calendar.c
main.o : main.c
  gcc -c -o main.o main.c
clean :
  rm * .o diary_exe
```
![image](https://user-images.githubusercontent.com/65120581/127415480-e570bc4a-c702-4282-ae40-31bf19e2a259.png)

- `$ make clean` 현재 디렉토리의 모든 object 파일들과 생성된 실행파일인 diary_exe를 rm 명령어로 제거

- `$ make`로 실행 <br>
![image](https://user-images.githubusercontent.com/65120581/127418390-d6678cca-26b4-41cb-8849-37be42de95ae.png)


## 매크로 사용하기

- 중복되는 파일 이름을 단어로 치환

### 작성규칙
- 소괄호나 중괄호 둘러싸고 앞에 '$'를 붙인다.
- 탭으로 시작해서는 안되고, :,=,#,"" 등 매크로 이름에 사용할 수 없다.
- 매크로는 반드시 치환될 위치보다 먼저 정의 되어야 한다.

```vim
CC = gcc
CFLAGS = -W -WALL
TARGET = diary_exe

$(TARGET) : memo.o calendar.o main.o
    $(CC) $(CFLAGS)  $(TARGET)  memo.o calendar.o main.o

memo.o : memo.c
    $(CC) $(CFLAGS) -o mmo.o memo.c

calendar.o : calendar.c
    $(CC) $(CFLAGS) -o calendar.o calendar.c

main.o : main.c
    $(CC) $(CFLAGS) -o main.o main.c

clean :
    rm * .o diary_exe
```

### 내부 매크로 사용
```vim
CC = gcc
CFLAGS = -W -Wall
TARGET = diary_exe
OBJECT = memo.o main.o calendar.o

all : $(TARGET)

$(TARGET) : $(OBJECT)
              $(CC) $(CFLAGS) -o $@ $^

clean :
    rm * .o diary_exe

```
![image](https://user-images.githubusercontent.com/65120581/127423622-c77eb71d-cdb5-4a87-888a-8c440d2646fe.png)



<br>
<br>
<br>
<br>

> ### 참조

> [멍멍멍 make와 Makefile](https://bowbowbow.tistory.com/12#makefile-%EC%9D%98-%EA%B5%AC%EC%84%B1)
