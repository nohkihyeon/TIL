# 1. strcpy의 비용
- 서버 프로그램에서 성능상 가장 문제가 되는 병목현상을 해결하기 위해 메모리 복사를 자제해야한다.
- 데이터베이스 필드가 복사하려는 배열 공간보다 더 큰 공간의 문자열을 가졌거나  null 문자를 소실한 상태라면 strcpy는 심각한 문제를 발생
- memcpy 함수는 복사할 크기가 늘어나도 서서히 수행시간이 늘어나 상대적으로 비용이 싸다.
  - memcpy 함수의 이용은 strncpy 사용 대비 2배 속도 개선 효과 채팅메시지와 같은 경우 5배 속도 차이가 발생
# 2. warning: function returns address of local variable

![image](https://user-images.githubusercontent.com/65120581/135230071-62e6c56d-8246-4128-875a-2658491c7442.png)

- 보통 char* 형식을 리턴하는 함수를 작성할때 경고문구를 볼 수 있다.
- 따라서 해당 지역변수를 static으로 선언하거나, 전역변수로 선언해 주어야 한다.
- 내부 정적 변수는 다른 함수에서 호출이 가능하다.
- 프로그램 종료시 파괴되기 때문에 사용에  유의해야한다.
- 외부에서는 참조할 수 없는 정보은닉효과가 있다.

```c
#include<stdio.h>

int Test1(){
        static int number ;
        number++;
        return number;
}

int main(){
        int i;
        for(i=0; i<5; i++){
                printf("%d ", Test1());
        }
        printf("\n");

        return 0;
}

```
![image](https://user-images.githubusercontent.com/65120581/135234072-3988d342-11c8-4f7f-b26f-96ab864f6ec8.png)
