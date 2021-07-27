# C api Mysql

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
    if(!mysql_real_connect(conn, "127.0.0.1", "root", ".dlfndhs", NULL, 0, NULL, 0)){
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
            sprintf(query_buffer, "%s", "show tables;");
            if(mysql_query(conn, query_buffer)){
                printf("query faild : %s\n", query_buffer);
                exit(1);
            }

            result = mysql_use_result(conn);
            while((row = mysql_fetch_row(result)) != NULL)
                printf("%s \n", row[0]);
        }
        if(n==2){
            printf("Enter table name : ");
            scanf("%s", &table_name);
            //sprintf(query_buffer, "%s %s", "select * from", table_name);
            //if(mysql_query(conn, query_buffer)){
            //  printf("query faild : %s\n", query_buffer);
            //  exit(1);
            //}
            //result = mysql_use_result(conn);
            //while((row = mysql_fetch_row(result)) != NULL){
            //while((row = mysql_fetch_row(result)) != NULL){
            //  for(i=0; i<mysql_num_fields(result); i++){
            //      if(i==0){
            //          while(field = mysql_fetch_field(result))
            //          {
            //              printf("%-25s", field->name);
            //          }
            //          printf("\n");
            //      }
            //      printf("%-25s", row[i]);
            //  }
            //}
            //printf("\n");]
            print_select_all();
        }if(n==3){                                         // 3. Insert into
            printf("Enter table name : ");
            scanf("%[^\n]s", &table_name);
            getchar();
            printf("Enter Columns : ");
            scanf("%[^\n]s", &insert_columns);
            getchar();
            printf("Enter Values : ");
            scanf("%[^\n]s", &insert_values);
            getchar();
            sprintf(query_buffer, "%s %s %s %s %s %s%s", "INSERT INTO ", table_name, " (",insert_columns ,") VALUES(",insert_values ,")");
            if(mysql_query(conn, query_buffer)){
                printf("query failed : %s\n", query_buffer);
                exit(1);
            }
            printf("Query OK, 1 row affected\n");
            print_select_all();
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
