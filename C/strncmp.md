# strncmp와 strcnp

## 헤더파일
```c
<string.h>    // c언어
<cstring>     // c++
```

## function prototype
```c
int strcmp(const char* str1, const char* str2);
int strncmp(const char* str1, const char* str2, size_t n);
```
- strcmp
  - str1 : 비교할 문자열 1
  - str2 : 비교할 문자열 2
- strncmp
  - str1 : 비교할 문자열 1
  - str2 : 비교할 문자열 2
  - n : 비교할 문자열 길이 (size_t는 unsigned int / 0보다는 큰 값이 들어와야한다.) 
    -  str1이 문자 8개 str2가 문자열 18개로 구성되어 있을 때 n이 10, 100을 넣어도 str1의 8개 문자만 비교한다.


```c
char str1[] = "Loki MCU";
char str2[] = "Loki MCU";

strcmp(str1, str2);       // 문자열 같다 : 0 반환
strcmp(str1, "Loki PCU"); // 'M' < 'P' 음수 반환  cf) M : 120 P : 123
strcmp(str1, "Loki DCU"); // 'M' > 'D 양수 반환   cf) M : 120 D : 111
```
- C언어 문자열 char*, char[]의 끝에는 문자열의 끝인 '\0'이 들어가 있다.
- 이것은 null을 의미 = 아스키코드 0
- 아스키코드가 'M' < 'P' 음수반환
- 아스키코드가 'M' > 'D' 양수반환

```c
#include <stdio.h>
#include <string.h>

int main() {

        char str1[] = "Loki MCU";
        char str2[] = "Loki MCU";

        printf("strcmp(%s, %s)\t = %d\n", str1, str2, strcmp(str1, str2));                   // 문자열 같다 : 0 반환
        printf("strcmp(%s, %s)\t = %d\n", str1, "Loki PCU", strcmp(str1, "Loki PCU"));       // 문자열 : 음수 반환
        printf("strcmp(%s, %s)\t = %d\n", str1, "Loki DCU", strcmp(str1, "Loki DCU"));       // 문자열 : 양수 반환
}

```


```c
const char* str1 = "LokiMcinema";
const char* str2 = "LokiDcinema";
strncmp(str1, str2, 4);           // Loki 까지 문자열 같다 : 0 반환
strncmp(str1, str2, 5);           // 'M' -  'D' : 9 반환 cf) M : 120 D : 111
``` 
- 4글자 비교시 Loki까지 전부 같다
- 5글자 비교시 'M' -  'D' : 9 반환 cf) M : 120 D : 111
```c
#include <stdio.h>
#include <string.h>

int main() {
        const char* str1 = "LokiMcinema";
        const char* str2 = "LokiDcinema";
        printf("strncmp(%s, %s, 4)\t = %d\n", str1, str2, strncmp(str1, str2, 4));
        printf("strncmp(%s, %s, 5)\t = %d\n", str1, str2, strncmp(str1, str2, 5));
}
```
