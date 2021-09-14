# JSON 구조
JSON은 자바스크립트의 객체 표기법으로부터 파생된 부분 집합
JSON 데이터는 다음과 같은 자바스크립트 객체 표기법에 따른 구조로 구성
- JSON 데이터는 이름과 값의 쌍으로 이루어진다.
- JSON 데이터는 쉼표(,)로 나열된다.
- 객체는 중괄호 {}로 둘러쌓여 표현한다.
- 배열은 대괄호 []로 둘러쌓여 표현한다.


## Jansson
Janssondms JSON 데이터를 인코딩, 디코딩 및 조직하기 위한 C 라이브러리 <BR>
주요 기능 및 설계 원칙
- 간단하고 직관적인 API 및 데이터 모델
- 종합 문서
- 다른 라이브러리에 종속성 없음
- 광범위한 테스트 수행

  
```c
#include <stdio.h>
#include <jansson.h>

void insert_filename(json_t* pjson, char* filename)
{
    json_t* data;

    data = json_string(filename);
    json_array_append(pjson, data);
}

int main (int argc, char** argv)
{
    json_t* pjson;
    json_t* data;
    int i;
    char* result;

    if ( argc <= 1 ) {
        printf(" Usage : %s filenames ... \n", argv[0] ) ;
        printf(" Example > %s /usr/* \n" , argv[0] ) ;
        return 0 ;
    }

    pjson = json_array();

    for(i=1; i<=argc; i++)
        insert_filename(pjson, argv[i]);

    printf("size : %d\n", json_array_size(pjson));
    result = json_dumps(pjson, JSON_ENCODE_ANY);
    json_dump_file(pjson, "./data.json", JSON_ENCODE_ANY);
    printf("%s\n", result);

    json_decref(pjson);

    return 0;
}

```
  
- `gcc jansson_test.c -o jansson_test` 하면 에러 발생 <br>
![image](https://user-images.githubusercontent.com/65120581/133227530-519b8dc7-3ae5-40d6-9b4a-79e2c6309a39.png)
  
- `-ljansson` 추가해서 gcc <br>
![image](https://user-images.githubusercontent.com/65120581/133227603-971392d5-af9f-4e6b-8803-2b9e227aceba.png)


  
## data.json
```json
  ["/usr/bin", "/usr/etc", "/usr/games", "/usr/include", "/usr/lib", "/usr/lib64", "/usr/libexec", "/usr/local", "/usr/sbin", "/usr/share", "/usr/src", "/usr/tmp"]
```
  
## 결과화면
![image](https://user-images.githubusercontent.com/65120581/133227849-da656037-d02a-46d9-8728-c18419c57590.png)

  
  

