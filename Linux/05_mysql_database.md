# MySQL CRUD C로 구현하기 
```c
#include<stdio.h>
#include<stdlib.h>
#include<mysql.h>
#include<errno.h>

void print_menu(){
    printf("==================\n");
    printf("1. Display all Tables\n");
    printf("2. Select Tables\n");
    printf("3. Add\n");
    printf("4. change\n");
    printf("5. Delete\n");
    printf("6. Exit\n");
    printf("==================\n");
    printf("Enter number : ");
}

void main(int argc, char **argv){
    MYSQL *conn;
    MYSQL_RES *result;
    MYSQL_ROW row;

    char query_buffer[2048];

    conn = mysql_init(NULL);
    int n=0;
    int i;
    char table_name[100];
    MYSQL_FIELD *field;

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
        if(n==1){                                           // 1. show tables;
            sprintf(query_buffer, "%s", "show tables;");
            if(mysql_query(conn, query_buffer)){
                printf("query faild : %s\n", query_buffer);
                exit(1);
            }

            result = mysql_use_result(conn);
            while((row = mysql_fetch_row(result)) != NULL)
                printf("%s \n", row[0]);
        }
        if(n==2){                                         // 2. select * from table;
            printf("Enter table name : ");
            scanf("%s", &table_name);
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
        if(n==3){                                         // 3. Insert into                             

        }
        if(n==4){                                         // 4. Update                             

        }
        if(n==5){                                         // 5. Delete                              

        }
    }

    mysql_free_result(result);
    mysql_close(conn);
}

```

## 1번
- `Show tables;` 결과화면 <br>
![image](https://user-images.githubusercontent.com/65120581/126948380-b7fd74d1-1de0-481b-a752-0d66b82273a3.png)

## 2번
- `select * from table;` 결과화면 <br>
![image](https://user-images.githubusercontent.com/65120581/126948495-6f96b1d2-d519-40a5-92e2-796a718a3a71.png)


## 3번
![image](https://user-images.githubusercontent.com/65120581/127613119-428dedac-0b14-40be-ab00-83e9657a0561.png)

## 4번
![image](https://user-images.githubusercontent.com/65120581/127613295-7d62f37f-c4c9-4941-8cec-2d6ab8bd81f9.png)

## 5번
![image](https://user-images.githubusercontent.com/65120581/127613443-b76d9348-3c67-4681-96a0-00a4f43a4fd3.png)

# MySQL JOIN 
- `rename table topic to topic_backup` <br>
![image](https://user-images.githubusercontent.com/65120581/126920967-d86cdd58-c986-4f94-be32-2d73ff36534c.png)

## 테이블 분리
- Table structure for table `author`
```mysql
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `profile` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
- Dumping data for table `author`
```mysql
 
INSERT INTO `author` VALUES (1,'khNoh','developer');
INSERT INTO `author` VALUES (2,'hsLee','database administrator');
INSERT INTO `author` VALUES (3,'jsPark','data scientist, developer');
```
- Table structure for table `topic`
```mysql
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `description` text,
  `created` datetime NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
```
-  Dumping data for table `topic`
```mysql
INSERT INTO `topic` VALUES (1,'MySQL','MySQL is...', NOW(),1);
INSERT INTO `topic` VALUES (2,'Oracle','Oracle is ...','2018-01-03 13:01:10',1);
INSERT INTO `topic` VALUES (3,'SQL Server','SQL Server is ...','2018-01-20 11:01:10',2);
INSERT INTO `topic` VALUES (4,'PostgreSQL','PostgreSQL is ...','2018-01-23 01:03:03',3);
INSERT INTO `topic` VALUES (5,'MongoDB','MongoDB is ...','2018-01-30 12:31:03',1);
```
## 테이블 JOIN <br>
![image](https://user-images.githubusercontent.com/65120581/126921728-7222480c-685e-4332-b51f-8959e72aeba2.png)

- `SELECT * FROM topic LEFT JOIN author ON topic.author_id = author.id`
![image](https://user-images.githubusercontent.com/65120581/126922038-df640e3e-6e6d-4c6a-bb63-8c49a8009e4b.png)
- 중복을 제거하기 위해서 테이블을 분리하며, JOIN 연산자를 이용해서 테이블을 효과적으로 조회 가능
