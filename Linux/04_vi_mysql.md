# MySql lib를 리눅스 C에서 사용하기

## 콘솔에서 mysql 접속방법
- 로콜 서버에 접속
  - 명령어 : `mysql -u 계정명 -p`
    - ex) `mysql -u root -p` <br>
    ![image](https://user-images.githubusercontent.com/65120581/126608222-1aa7fd36-0982-492e-b1fe-cf595a56591c.png)

- 원격지에 접속
  - 명령어 : `mysql -h 호스트주소(ip) -P 포트번호 -u 계정명 -p`
    - ex) mysql -h 192.168.2.201 -P 22 -u root -p

- 원격지의 특정 database에 접속
  - 명령어 : `mysql -h 호스트주소(ip) - P 포트번호 -u 계정명 -p 디비이름`
    - ex) mysql -h 192.168.2.201 -P 22 -u root -p userDB 

## mysql 접속하기
- Mysql 디렉토리 안의 db_lib 경로를 찾는 법
![image](https://user-images.githubusercontent.com/65120581/126729945-b4965266-3671-489c-aec6-394ee0d1ddcd.png)
- 각 db_lib 경로로 export를 먼저 진행 <br>
`$ export LD_LIBRARY_PATH=:/home/khnoh/mysql/db_lib`

- C 파일 컴파일 하는 법 ex) main.c <br>
```C
#include<stdio.h>
#include<stdlib.h>
#include<mysql.h>
#include<errno.h>

void main(int argc, char **argv){
    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    char query_buffer[2048];

    conn = mysql_init(NULL);

    if(!mysql_real_connect(conn, "127.0.0.1", "root", ".dlfndhs", NULL, 0, NULL, 0)){
        printf("Cannot connect");
        exit(1);
    }
    else {
        if (mysql_select_db(conn, "ausf")){
            printf("cannot use database");
            exit(1);
        }
    }

    sprintf(query_buffer, "%s", "show tables");
    if(mysql_query(conn, query_buffer)){
        printf("query faild : %s\n", query_buffer);
        exit(1);
    }

    result = mysql_use_result(conn);
    while((row = mysql_fetch_row(result)) != NULL)
        printf("%s \n", row[0]);

    mysql_free_result(result);
    mysql_close(conn);
}

```
- include 경로와 library경로를 반드시 지정 / lmysqlclient 추가적으로 입력해야 정상적으로 실행된다. <br>
`$ gcc main.c -o main -I/home/khnoh/mysql/include -L/home/khnoh/mysql/db_lib -lmysqlclient`

- C API 자료형
  - MYSQL : Database와의 연결을 관리하는 구조체이다. 대부분의 C API 함수에서 쓰인다.
  - MYSQL_RES : SELECT 등 결과를 리턴하는 query의 결과를 나타내는 자료형이다.
  - MYSQL_ROW : MYSQL_RES에서 하나의 레코드씩 값을 얻어 올때 쓰이는 자료형이다.
  - MYSQL_FIELD : 필드의 이름과 필드의 타입 등 필드에 관한 정보를 저장하는 자료형이다.

- 정상적으로 생성된 모습 <br>
![image](https://user-images.githubusercontent.com/65120581/126730704-0b88005f-2387-4410-8d3c-3a2f8f243c50.png)

![image](https://user-images.githubusercontent.com/65120581/126731304-33e5b296-72de-4b8d-a1c2-0d6da95c3dd3.png)






## 참조
> ## [나긋한 개발자](https://sacstory.tistory.com/entry/mysql-lib%EB%A5%BC-c%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)

> ## [코드공장](https://code-factory.tistory.com/44)

> ## [AweSome Life](https://0x616b616d61.tistory.com/entry/Linux-%EB%A6%AC%EB%88%85%EC%8A%A4-C-%EC%97%90%EC%84%9C-mysql-%EC%A0%91%EC%86%8D%ED%95%98%EA%B8%B0linux-c-programming-with-mysql)