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
  
  
  
  
  
  
### set enc
  ![image](https://user-images.githubusercontent.com/65120581/133207530-f6ea3054-c1d2-4a7a-aeb7-4b40602b6553.png)
:set enc=utf-8
  
