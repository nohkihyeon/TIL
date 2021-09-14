## strtok
- strtok 헤더파일
  - c : `<string.h> `
- 함수 정의
  - `char *strtok(char* str, char* delimiters);` 
```c
char *toekn strtok(str, " ");
char str[] = "String Tokenizer Token"

while(token != NULL){
  printf("%s\n", token);
  token = strtok(NULL, " ");
}
```
![image](https://user-images.githubusercontent.com/65120581/133225455-08017c31-8ca8-4d04-b760-f4972a5af35d.png)

- " "공백을 찾게되면 문장의 끝을 알리는 \0 으로 교체
- 반환 첫번째 : String
- 반환 두번째 : Tokenizer
- 반환 세번째 : Token

## 함수 htons()
- hton(host to network) : 호스트값으로 저장된 값을 네트워크에서 사용하기 위한 용도로 값을 반환
- s(unsigned short)
- ` c_addr.sin_port = htons(80);`

## stderr
- stderr : 기본 출력장치(모니터) --> 에러
- `fprintf(stderr, "Usage : <Domain Address>\n");` 
  - fprintf는 스트림 출력 함수
  - 사용이유 : printf로 쓸경우 버퍼 문제로 제대로 출력이 되지 않을 경우 에러 메시지 출력을 제때 버퍼 없이 바로 출력하기 위해 사용

## set enc
한글이 깨지는 형상을 UTF-8 형식으로 지정해주면 
![image](https://user-images.githubusercontent.com/65120581/133207530-f6ea3054-c1d2-4a7a-aeb7-4b40602b6553.png) <br>
`:set enc=utf-8` <Br>
![image](https://user-images.githubusercontent.com/65120581/133210482-9d331f1a-f238-43bd-85d4-9b95de6b5793.png) 

  
## 리눅스 C warning
  ![image](https://user-images.githubusercontent.com/65120581/133219647-b9507952-1f92-4c9a-ac39-981f3db96bee.png)
 - 해결방법
   - `#include <stdlib>`추가
  

