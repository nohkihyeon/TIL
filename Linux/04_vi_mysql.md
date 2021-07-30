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
- Mysql 디렉토리 안의 db_lib 경로를 찾는 법 <br>
![image](https://user-images.githubusercontent.com/65120581/126729945-b4965266-3671-489c-aec6-394ee0d1ddcd.png)
- 각 db_lib 경로로 export를 먼저 진행 <br>
`$ export LD_LIBRARY_PATH=:/home/khnoh/mysql/db_lib`

> - 위의 방법은 터미널을 실행할 때 마다 export 해야 정상적으로 작동된다.
> - `vi ~/.bashrc` bashrc는 별칭(alias)과 bash가 수행될 때 실행되는 함수를 제어하는 지역적인 시스템 설정과 관련된 파일로써, 모든 프로그램이 실행되기 전 수행된다. <br>
> `export LD_LIBRARY_PATH=:/home/khnoh/mysql/db_lib`를 추가해준다.
> ![image](https://user-images.githubusercontent.com/65120581/127604539-27bb35dc-75b9-4ccc-a949-f1534f3773b8.png)


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

    if(!mysql_real_connect(conn, "127.0.0.1", "root", "PASSWORD", NULL, 0, NULL, 0)){
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

## MySQL

### CRUD
>#### [1. CREATE](#create)
>#### [2. READ](#read)
>#### [3. UPDATE](#update)
>#### [4. DELETE](#delete)
>#### [5. etc](#etc)

### CREATE
- Syntax
```MySQL
CREATE [TEMPORARY] TABLE [IF NOT EXISTS] tbl_name
    (create_definition,...)
    [table_options]
    [partition_options]
```

- DATABASE 생성
  - `$ CREATE DATABASE khnoh;` <br>
![image](https://user-images.githubusercontent.com/65120581/126738212-5f091785-2aca-4f55-87c2-5eaf472b69b6.png)

  
- 테이블 생성
```mysql
CREATE TABLE topic(
    -> id INT(11) not null auto_increment,
    -> title varchar(100) not null,
    -> description TEXT null,
    -> created datetime not null,
    -> author varchar(30) null,
    -> profile varchar(100) null,
    -> PRIMARY KEY(id));
```
![image](https://user-images.githubusercontent.com/65120581/126741288-b0bf3601-10ca-4d4f-9be3-49b2bdf62c48.png)

- 테이블 행 추가
```MySQL
INSERT INTO topic (title, description, created, author, profile)  VALUES('MySQL','MySQL is...', NOW(), 'Eluon', 'Developer');
```
![image](https://user-images.githubusercontent.com/65120581/126741307-ca6e0901-1440-435d-bd3d-902032103c35.png)


### READ
- Syntax
```mysql
SELECT
    [ALL | DISTINCT | DISTINCTROW ]
    [HIGH_PRIORITY]
    [STRAIGHT_JOIN]
    [SQL_SMALL_RESULT] [SQL_BIG_RESULT] [SQL_BUFFER_RESULT]
    [SQL_NO_CACHE] [SQL_CALC_FOUND_ROWS]
    select_expr [, select_expr] ...
    [into_option]
    [FROM table_references
      [PARTITION partition_list]]
    [WHERE where_condition]
    [GROUP BY {col_name | expr | position}, ... [WITH ROLLUP]]
    [HAVING where_condition]
    [WINDOW window_name AS (window_spec)
        [, window_name AS (window_spec)] ...]
    [ORDER BY {col_name | expr | position}
      [ASC | DESC], ... [WITH ROLLUP]]
    [LIMIT {[offset,] row_count | row_count OFFSET offset}]
    [into_option]
    [FOR {UPDATE | SHARE}
        [OF tbl_name [, tbl_name] ...]
        [NOWAIT | SKIP LOCKED]
      | LOCK IN SHARE MODE]
    [into_option]

into_option: {
    INTO OUTFILE 'file_name'
        [CHARACTER SET charset_name]
        export_options
  | INTO DUMPFILE 'file_name'
  | INTO var_name [, var_name] ...
}
```

- 테이블 조회
```MySQL
SELECT *
FROM topic;
```
![image](https://user-images.githubusercontent.com/65120581/126744311-52dcd19e-047e-4ec4-8c76-e04ceef1aee3.png)

- 테이블 정렬
```MySQL
SELECT *
FROM topic
ORDER BY id DESC;
```
![image](https://user-images.githubusercontent.com/65120581/126746585-cb7589fc-cf99-4201-89f1-22d691e65f2f.png) <br>
```MySQL
SELECT *
FROM topic
ORDER BY id DESC LIMIT 2;   // 2개만 보고 싶을 경우
```
![image](https://user-images.githubusercontent.com/65120581/126746718-aea45876-d9e7-4f28-8c3e-481deb02c5d5.png)

### UPDATE
- Syntax
```MySQL
UPDATE [LOW_PRIORITY] [IGNORE] table_reference
    SET assignment_list
    [WHERE where_condition]
    [ORDER BY ...]
    [LIMIT row_count]

value:
    {expr | DEFAULT}

assignment:
    col_name = value

assignment_list:
    assignment [, assignment] ...
```
- `UPDATE topic SET title='Oracle', author = 'jsPark' WHERE id = 3;` 
![image](https://user-images.githubusercontent.com/65120581/126747827-107c27a2-e8d5-49a0-8ada-e47842442b08.png)

### DELETE
- Syntax
```MySQL
DELETE [LOW_PRIORITY] [QUICK] [IGNORE] FROM tbl_name [[AS] tbl_alias]
    [PARTITION (partition_name [, partition_name] ...)]
    [WHERE where_condition]
    [ORDER BY ...]
    [LIMIT row_count]
```
- 테이블 행 삭제
```MySQL
DELETE FROM topic WHERE id = 1;
```

```MySQL
DELETE FROM topic
WHERE id = 6;
```
![image](https://user-images.githubusercontent.com/65120581/126748214-8340962a-0848-45ad-93f7-2734c7af9a5a.png)

### etc
- 테이블 dsecribe <br>
  - DESC {테이블명}
    - `> DESC topic;`

![image](https://user-images.githubusercontent.com/65120581/126741363-4e4be9c4-b8ea-46b6-ba4a-f53f851a8cc1.png)
- 테이블 삭제
  - `DROP DATABASE khnoh;` <br>
 
- 테이블 지정
  - `USE khnoh;` <br>
![image](https://user-images.githubusercontent.com/65120581/126738480-28837109-4bdf-4799-b4de-077e49e5384e.png)

## 참조
> ## [생활코딩-MySQL](https://opentutorials.org/course/3161)

> ## [나긋한 개발자-[MySQL] MySQL lib를 리눅스 C에서 사용하기](https://sacstory.tistory.com/entry/mysql-lib%EB%A5%BC-c%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)

> ## [코드공장-console에서 MySQL 접속방법](https://code-factory.tistory.com/44)

> ## [AweSome Life-[Linux] 리눅스 C 에서 mysql 접속하기](https://0x616b616d61.tistory.com/entry/Linux-%EB%A6%AC%EB%88%85%EC%8A%A4-C-%EC%97%90%EC%84%9C-mysql-%EC%A0%91%EC%86%8D%ED%95%98%EA%B8%B0linux-c-programming-with-mysql)
