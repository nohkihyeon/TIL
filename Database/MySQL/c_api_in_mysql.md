# C api Mysql

> ###  DB 경로 export 진행
> - include 경로와 library경로를 반드시 지정 / lmysqlclient 추가적으로 입력 <br>
> `export LD_LIBRARY_PATH=:/home/khnoh/mysql/db_lib` <br>
> `gcc main.c -o main -I/home/khnoh/mysql/include -L/home/khnoh/mysql/db_lib -lmysqlclient`                         


> [자세한설명 보기](https://github.com/nohkihyeon/TIL/blob/main/Linux/04_vi_mysql.md)


## 1. Display all Tables <br>
![image](https://user-images.githubusercontent.com/65120581/127096600-7bb2a558-8ad4-409b-b5d8-a4b5da8f3a6a.png)
```c
MYSQL *conn;                // Database와의 연결을 관리하는 구조체이다. 대부분의 C API 함수에서 쓰인다.
char query_buffer[2048];
...
sprintf(querry_buffer, "%s", "Show tables;");
if(mysql_query(conn, query_buffer){
    printf("query failed : %s\n", querry_buffer);
    exit(1);
}
```

## 2. Select * from table <br>
![image](https://user-images.githubusercontent.com/65120581/127096626-83a81d25-f641-4c5c-843a-6597b3827a2d.png)

```c
...
void print_select_all(){

    sprintf(query_buffer, "%s %s", "select * from", table_name);
    if(mysql_query(conn, query_buffer)){
        printf("query failed : %s\n", query_buffer);
        exit(1);
    }
    result = mysql_use_result(conn);                // 한 번에 한 개의 ROW를 서버로부터 가져 온다
    while((row = mysql_fetch_row(result)) != NULL){ // result에 있는 ROW들에서 한 개의 ROW를 얻어온다.
        for(i=0; i<mysql_num_fields(result); i++){
            if(i==0){
                while(field = mysql_fetch_field(result))
                {
                    printf("%-25s", field->name);
                }
                printf("\n");
            }
            printf("%-25s", row[i]);
        }
    }
    printf("\n");
}
...
```
- 서버로부터 결과 값을 얻어오는 방법
    - * MYSQL_RES* mysql_store_result(MYSQL* mysql)
        - 한꺼번에 모두 서버로부터 얻어옴
    - * MYSQL_RES* mysql_use_result(MYSQL* mysql)
        -  한번에 한 개의 ROW를 얻어옴

## 3. Create(Insert) <br>
![image](https://user-images.githubusercontent.com/65120581/127096822-74d2a110-c011-4cc5-8d76-a72b94d62cba.png)
- 유사하게 sprintf를 이용해서 INSERT INTO 테이블명 (칼럼1, 칼럼2, ...) VALUES (값1, 값2, ...)
- mysql_query(conn, query_buffer)를 이용

## 4. Read(Select) <br>
![image](https://user-images.githubusercontent.com/65120581/127097335-98f2449c-58fc-4773-a1f5-31b57fde13a0.png)
- table_name을 사용자로부터 입력받아 해당 테이블을 출력하는 API

## 4. Update(Update) <br>
![image](https://user-images.githubusercontent.com/65120581/127096961-ada830ec-f489-4140-9b75-07e46af32781.png)
- sprintf를 이용해서 UPDATE 테이블명 SET 칼럼1 = '값1' ... WHERE 칼럼 = 값
- mysql_query(conn, query_buffer)를 이용

## 5. Delete(Delete) <br>
![image](https://user-images.githubusercontent.com/65120581/127097230-8cb66661-99ee-4ac0-bdd6-1473ec4c9b08.png)
- sprintf를 이용해서 DELETE FROM 테이블명 WHERE 칼럼1 = '값1'
- mysql_query(conn, query_buffer)를 이용


> ## 코드 
```C
#include<stdio.h>
#include<stdlib.h>
#include<mysql.h>
#include<errno.h>

    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    char query_buffer[2048];

    int n=0;
    int i;
    char table_name[100];
    MYSQL_FIELD *field;
    char insert_columns[200];
    char insert_values[200];

void input_info(){
    printf("Enter table name : ");
    scanf("%[^\n]s", &table_name);
    getchar();
    printf("Enter Columns : ");
    scanf("%[^\n]s", &insert_columns);
    getchar();
    printf("Enter Values : ");
    scanf("%[^\n]s", &insert_values);
    getchar();
}

void query_affet(){
    if(mysql_query(conn, query_buffer)){
        printf("query failed : %s\n", query_buffer);
        exit(1);
        }
    printf("Query OK, 1 row affected\n");
}

void print_menu(){
    printf("==================\n");
    printf("1. Display All Tables\n");
    printf("2. SELECT All FROM Tables\n");
    printf("3. INSERT\n");
    printf("4. UPDATE\n");
    printf("5. DELETE\n");
    printf("6. EXIT\n");
    printf("==================\n");
    printf("Enter number : ");
}

void print_select_all(){

    sprintf(query_buffer, "%s %s", "select * from", table_name);
    if(mysql_query(conn, query_buffer)){
        printf("query faild : %s\n", query_buffer);
        exit(1);
    }
    result = mysql_use_result(conn);
    while((row = mysql_fetch_row(result)) != NULL){
        for(i=0; i<mysql_num_fields(result); i++){
            if(i==0){
                while(field = mysql_fetch_field(result))
                {
                    printf("%-25s", field->name);
                }
                printf("\n");
            }
            printf("%-25s", row[i]);
        }
    }
    printf("\n");
}

void main(int argc, char **argv){
    conn = mysql_init(NULL);

    if(!mysql_real_connect(conn, "127.0.0.1", "root", "PASSWORD", NULL, 0, NULL, 0)){
        printf("Cannot connect");
        exit(1);
    }
    else {
        if (mysql_select_db(conn, "khnoh")){
            printf("cannot use database");
            exit(1);
        }
    }
    while(n!=6){
        print_menu();
        scanf("%d", &n);
        getchar();
        if(n==1){
            sprintf(query_buffer, "%s", "show tables;");    // 1. SHOW TABLES;
            if(mysql_query(conn, query_buffer)){
                printf("query faild : %s\n", query_buffer);
                exit(1);
            }

            result = mysql_use_result(conn);
            while((row = mysql_fetch_row(result)) != NULL)
                printf("%s \n", row[0]);
        }
        if(n==2){                                         // 2. SELECT * FROM TABLE
            printf("Enter table name : ");
            scanf("%s", &table_name);
            print_select_all();
        }if(n==3){                                        // 3. Insert into
            input_info();
            sprintf(query_buffer, "%s %s %s %s %s %s%s", "INSERT INTO ", table_name, " (",insert_columns ,") VALUES(",insert_values ,")");
            query_affet();
            print_select_all();
        }
       if(n==4){                                         // 4. Update
            input_info();
            sprintf(query_buffer, "%s %s %s %s %s %s", "UPDATE ", table_name, " SET ",insert_columns ,"WHERE ",insert_values);
            query_affet();
            print_select_all();
        }
       if(n==5){                                         // 5. Delete
            input_info();
            sprintf(query_buffer, "%s %s %s %s %s %s", "DELETE FROM ", table_name, " WHERE ",insert_columns ," = ",insert_values);
            query_affet();
            print_select_all();
        }
    }
    mysql_free_result(result);
    mysql_close(conn);
}

```
