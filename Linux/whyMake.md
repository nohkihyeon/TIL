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





> ### 참조

> [멍멍멍 make와 Makefile](https://bowbowbow.tistory.com/12#makefile-%EC%9D%98-%EA%B5%AC%EC%84%B1)
