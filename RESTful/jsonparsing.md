# json parsing
-  dapi.kakao.com  /v2/local/search/keyword.json?query=eluon 결과
```javascript
{
    "documents": [
        {
            "address_name": "경기 성남시 분당구 삼평동 670",
            "category_group_code": "",
            "category_group_name": "",
            "category_name": "서비스,산업 \u003e 인터넷,IT \u003e 소프트웨어",
            "distance": "",
            "id": "18634438",
            "phone": "070-4489-1000",
            "place_name": "이루온",
            "place_url": "http://place.map.kakao.com/18634438",
            "road_address_name": "경기 성남시 분당구 대왕판교로 660",
            "x": "127.10714197082693",
            "y": "37.40077576672964"
        },
        {
            "address_name": "경기 성남시 분당구 삼평동 670",
            "category_group_code": "",
            "category_group_name": "",
            "category_name":"서비스,산업 \u003e 제조업 \u0 03e 전기,전자 \u003e 보안제품",
            "distance": "",
            "id": "1676681776",
            "phone": "070-4489-1078",
            "place_name": "이루온아이앤에스",
            "place_url": "http://place.map.kakao.com/1676681776",
            "road_address_name": "경기 성남시 분당구 대왕판교로 660",
            "x": "127.106674018018",
            "y": "37.4005076856004"
        },
        {
            "address_name": "경기 성남시 분당구 삼평동 670",
            "category_group_code": "",
            "category_group_name": "",
            "category_name": "서비스,산업 \u003e 인터넷,IT \u003e 소프트웨어",
            "distance": "",
            "id": "16059856",
            "phone": "1588-6670",
            "place_name": "이루온엘비에스",
            "place_url": "http://place.map.kakao.com/16059856",
            "road_address_name": "경기 성남시 분당구 대왕판교로 660",
            "x": "127.10738515856319",
            "y": "37.40103233706369"
        }
    ],
    "meta": {
        "is_end": true,
        "pageable_count": 3,
        "same_name": {
            "keyword": "eluon",
            "region": [],
            "selected_region": ""
        },
        "total_count": 3
    }
}
```

## jansson을 이용한 json parsing - 출력
<details>
<summary>접기/펼치기 버튼</summary>
<div markdown="1">
 
```c
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <jansson.h>
#include <stdbool.h>


void error_handling(char* msg);
char buffer[BUFSIZ];

int main(int argc, char *argv[]){
        int status;
        struct addrinfo hints;
        struct addrinfo *servinfo, *tmp;                // 결과를 저장할 변수
        char host[256];
        int c_socket;
        struct sockaddr_in c_addr;
        char message_fmt[] = "GET %s HTTP/1.0\r\nHost: %s\r\nAuthorization: KakaoAK %s\r\n\r\n";

        int left_len, sent_len, recv_len, bytes;
        char rcvBuffer[BUFSIZ];
        char data[BUFSIZ];
        char* afterData;
        json_t *root;
        json_error_t error;
        int i;


        c_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&hints, 0, sizeof(hints));               // hints 구조체의 모든 값을 0으로 초기화
        hints.ai_family = AF_UNSPEC;                    // IPv4 IPv6 상관없이 결과 모두 반환
        hints.ai_socktype = SOCK_STREAM;                // TCP stream sockets

        if(argc !=4){
                fprintf(stderr, "Usage : <host> <resource> <API_KEYS>\nex) ./search_keyword dapi.kakao.com  /v2/local/search/keyword.json?query=<query> <API_KEYS>\n");
                return -1;
        }

        status = getaddrinfo(argv[1], "80", &hints, &servinfo);

        tmp = servinfo;
        getnameinfo(tmp->ai_addr, tmp->ai_addrlen, host, sizeof(host), NULL, 0, NI_NUMERICHOST);

        sprintf(buffer, message_fmt, argv[2], argv[1], argv[3]);

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(host);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(80);


        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                error_handling("Can not connect");
                close(c_socket);
                return -1;
        }

        left_len = strlen(buffer);
        sent_len = 0;
        do{
                bytes = write(c_socket, buffer + sent_len, left_len - sent_len);
                if(bytes  <0)
                        error_handling("write()Error\n");
                if(bytes ==0)
                        break;
                sent_len +=bytes;
        }while(sent_len < left_len);

        left_len = strlen(rcvBuffer);
        sent_len = 0;
        do{
                memset(rcvBuffer, 0, sizeof(rcvBuffer));
                bytes = read(c_socket, rcvBuffer, 1024);
                fprintf(stdout, "%s ", rcvBuffer);

                if(bytes < 0)
                        error_handling("read() error\n");
                if(bytes ==0)
                        break;
                recv_len +=bytes;
                //sprintf(data, "%s%s", data, rcvBuffer);
                strcat(data, rcvBuffer);
        }while(1);

        if(recv_len == left_len)
                error_handling("Error\n");

        afterData = strstr(data, "{");
        root = json_loads(afterData, 0, &error);
        if(!root)
        {
                fprintf(stderr, "Error : on line %d : %s \n", error.line, error.text);
                return 1;
        }

        if(root){
                json_t *jsonData = json_object_get(root, "documents");
                if(json_is_array(jsonData)){
                        const int length = json_array_size(jsonData);
                        for(i=0; i<length; i++){
                                json_t *jsonObject = json_array_get(jsonData, i);

                                json_t *jsonAddress_name = json_object_get(jsonObject, "address_name");
                                const char *address_name = json_string_value(jsonAddress_name);
                                json_t *jsonCategory_group_code = json_object_get(jsonObject, "category_group_code");
                                const char *category_group_code = json_string_value(jsonCategory_group_code);
                                json_t *jsonCategory_group_name = json_object_get(jsonObject, "category_group_name");
                                const char *category_group_name = json_string_value(jsonCategory_group_name);
                                json_t *jsonCategory_name= json_object_get(jsonObject, "category_name");
                                const char *category_name= json_string_value(jsonCategory_name);
                                json_t *jsonDistance= json_object_get(jsonObject, "distance");
                                const char *distance= json_string_value(jsonDistance);
                                json_t *jsonId= json_object_get(jsonObject, "id");
                                const char *id= json_string_value(jsonId);
                                json_t *jsonPhone= json_object_get(jsonObject, "phone");
                                const char *phone= json_string_value(jsonPhone);
                                json_t *jsonPlace_name= json_object_get(jsonObject, "place_name");
                                const char *place_name= json_string_value(jsonPlace_name);
                                json_t *jsonPlace_url = json_object_get(jsonObject, "place_url");
                                const char *place_url= json_string_value(jsonPlace_url);
                                json_t *jsonRoad_address_name= json_object_get(jsonObject, "road_address_name");
                                const char *road_address_name = json_string_value(jsonRoad_address_name);
                                json_t *jsonX= json_object_get(jsonObject, "x");
                                const char *x= json_string_value(jsonX);
                                json_t *jsonY= json_object_get(jsonObject, "y");
                                const char *y= json_string_value(jsonY);
                                printf("\naddress_name = %s\ncategory_group_code = %s\ncategory_group_name = %s\ncategory_name = %s\ndistance = %s\nid = %s\nphone = %s\nplace_name = %s\nplace_url = %s\nroad_address_name = %s\nx=%s\ny=%s\n\n\n", address_name, category_group_code, category_group_name, category_name,distance,id,phone,place_name,place_url,road_address_name,x,y);
                        }

                }
                json_t *jsonMetaData = json_object_get(root, "meta");
                json_t *jsonIs_end = json_object_get(jsonMetaData, "is_end");
                const short is_end = json_boolean_value(jsonIs_end);
                json_t *jsonPageable_count = json_object_get(jsonMetaData, "pageable_count");
                const int pageable_count = json_integer_value(jsonPageable_count);
                json_t *jsonTotal_count = json_object_get(jsonMetaData, "total_count");
                const int total_count = json_integer_value(jsonTotal_count);
                printf("Meta\nis_end = %d\npageable_count = %d\ntotal_count = %d\n",is_end, pageable_count, total_count);

                json_t *jsonSame_name = json_object_get(jsonMetaData, "same_name");
                json_t *jsonKeyword = json_object_get(jsonSame_name, "keyword");
                const char *keyword = json_string_value(jsonKeyword);

                json_t *jsonSelected_region = json_object_get(jsonSame_name, "selected_region");
                const char *selected_region = json_string_value(jsonSelected_region);

//              json_t *jsonregions = json_object(jsonsame_name, "region");
//              const char *region = "";
//              if(json_is_array(jsonRegions)){
//                      const int regionLength = json_array_size(jsonRegions);
//                      for(i=0; i<regionLength; i++){
//                              json_t *jsonObject = json_array_get(jsonRegions, i);
//                              strcat(region, jsonObject);
//                      }
//              }
//              printf("\nsame_name\nkeyword = %s\nregion = %s \nselected_region = %s\n", keyword, region, selected_region);
                printf("\nsame_name\nkeyword = %s\n\nselected_region = %s\n", keyword, selected_region);



        }


        close(c_socket);
        freeaddrinfo(servinfo);

}

void error_handling(char* msg){
        fputs(msg, stderr);
        fputc('\n', stderr);
        exit(1);
}
                                                 
                                                 
```
                                                 
</div>
</details>
    
    
## jansson을 이용한 json parsing - 구조체
    - 구조체에 각 멤버변수에 GET한 정보를 넣어준다.
```c
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <jansson.h>
#include <stdbool.h>


void error_handling(char* msg);
char buffer[BUFSIZ];
#define DEFAULT_SIZE 512
#define COMM_MAX         5

typedef struct Document_t {
        char        id                  [DEFAULT_SIZE]  ;
        char        place_name          [DEFAULT_SIZE]  ;
        char        category_group_code [DEFAULT_SIZE]  ;
        char        category_group_name [DEFAULT_SIZE]  ;
        char        category_name       [DEFAULT_SIZE]  ;
        char        phone               [DEFAULT_SIZE]  ;
        char        address_name        [DEFAULT_SIZE]  ;
        char        road_address_name   [DEFAULT_SIZE]  ;
        char        x                   [DEFAULT_SIZE]  ;
        char        y                   [DEFAULT_SIZE]  ;
        char        place_url           [DEFAULT_SIZE]  ;
        char        distance            [DEFAULT_SIZE]  ;
} Document ;

typedef struct Region_t {
        char        region              [DEFAULT_SIZE]  ;
} Region ;

typedef struct SameName_t {
        Region      regions             [COMM_MAX]      ;
        char        keyword             [DEFAULT_SIZE]  ;
        char        selected_region     [DEFAULT_SIZE]  ;
} SameName ;

typedef struct Meta_t {
        int         total_count                         ;
        int         pageable_count                      ;
        short       is_end                              ;
        SameName    same_name                           ;
} Meta ;

typedef struct DataSet_t {
        Document    documents           [COMM_MAX]      ;
        Meta        meta                                ;
} DataSet ;

DataSet dataSet;
int main(int argc, char *argv[]){
        int status;
        struct addrinfo hints;
        struct addrinfo *servinfo, *tmp;                // 결과를 저장할 변수
        char host[256];
        int c_socket;
        struct sockaddr_in c_addr;
        char message_fmt[] = "GET %s HTTP/1.0\r\nHost: %s\r\nAuthorization: KakaoAK %s\r\n\r\n";

        int left_len, sent_len, recv_len, bytes;
        char rcvBuffer[BUFSIZ];
        char data[BUFSIZ];
        char* afterData;
        json_t *root;
        json_error_t error;
        int i;


        c_socket = socket(PF_INET, SOCK_STREAM, 0);

        memset(&hints, 0, sizeof(hints));               // hints 구조체의 모든 값을 0으로 초기화
        hints.ai_family = AF_UNSPEC;                    // IPv4 IPv6 상관없이 결과 모두 반환
        hints.ai_socktype = SOCK_STREAM;                // TCP stream sockets

        if(argc !=4){
                fprintf(stderr, "Usage : <host> <resource> <API_KEYS>\nex) ./search_keyword dapi.kakao.com  /v2/local/search/keyword.json?query=<query> <API_KEYS>\n");
                return -1;
        }

        status = getaddrinfo(argv[1], "80", &hints, &servinfo);

        tmp = servinfo;
        getnameinfo(tmp->ai_addr, tmp->ai_addrlen, host, sizeof(host), NULL, 0, NI_NUMERICHOST);

        sprintf(buffer, message_fmt, argv[2], argv[1], argv[3]);

        memset(&c_addr, 0, sizeof(c_addr));
        c_addr.sin_addr.s_addr = inet_addr(host);
        c_addr.sin_family = AF_INET;
        c_addr.sin_port = htons(80);


        if(connect(c_socket, (struct sockaddr *) &c_addr, sizeof(c_addr)) == -1){
                error_handling("Can not connect");
                close(c_socket);
                return -1;
        }

        left_len = strlen(buffer);
        sent_len = 0;
        do{
                bytes = write(c_socket, buffer + sent_len, left_len - sent_len);
                if(bytes  <0)
                        error_handling("write()Error\n");
                if(bytes ==0)
                        break;
                sent_len +=bytes;
        }while(sent_len < left_len);

        left_len = strlen(rcvBuffer);
        sent_len = 0;
        do{
                memset(rcvBuffer, 0, sizeof(rcvBuffer));
                bytes = read(c_socket, rcvBuffer, 1024);
                fprintf(stdout, "%s ", rcvBuffer);

                if(bytes < 0)
                        error_handling("read() error\n");
                if(bytes ==0)
                        break;
                recv_len +=bytes;
                //sprintf(data, "%s%s", data, rcvBuffer);
                strcat(data, rcvBuffer);
        }while(1);

        if(recv_len == left_len)
                error_handling("Error\n");

        afterData = strstr(data, "{");
        root = json_loads(afterData, 0, &error);
        if(!root)
        {
                fprintf(stderr, "Error : on line %d : %s \n", error.line, error.text);
                return 1;
        }

        if(root){
                json_t *jsonData = json_object_get(root, "documents");
                if(json_is_array(jsonData)){
                        const int length = json_array_size(jsonData);
                        if(length > COMM_MAX)
                                error_handling("Out of index Error\n");
                        for(i=0; i<length; i++){
                                json_t *jsonObject = json_array_get(jsonData, i);

                                json_t *jsonAddress_name = json_object_get(jsonObject, "address_name");
                                strcpy(dataSet.documents[i].address_name , json_string_value(jsonAddress_name));
                                json_t *jsonCategory_group_code = json_object_get(jsonObject, "category_group_code");
                                strcpy(dataSet.documents[i].category_group_code , json_string_value(jsonCategory_group_code));
                                json_t *jsonCategory_group_name = json_object_get(jsonObject, "category_group_name");
                                strcpy(dataSet.documents[i].category_group_name , json_string_value(jsonCategory_group_name));
                                json_t *jsonCategory_name= json_object_get(jsonObject, "category_name");
                                strcpy(dataSet.documents[i].category_name , json_string_value(jsonCategory_name));
                                json_t *jsonDistance= json_object_get(jsonObject, "distance");
                                strcpy(dataSet.documents[i].distance , json_string_value(jsonDistance));
                                json_t *jsonId= json_object_get(jsonObject, "id");
                                strcpy(dataSet.documents[i].id , json_string_value(jsonId));
                                json_t *jsonPhone= json_object_get(jsonObject, "phone");
                                strcpy(dataSet.documents[i].phone , json_string_value(jsonPhone));
                                json_t *jsonPlace_name= json_object_get(jsonObject, "place_name");
                                strcpy(dataSet.documents[i].place_name , json_string_value(jsonPlace_name));
                                json_t *jsonPlace_url = json_object_get(jsonObject, "place_url");
                                strcpy(dataSet.documents[i].place_url , json_string_value(jsonPlace_url));
                                json_t *jsonRoad_address_name= json_object_get(jsonObject, "road_address_name");
                                strcpy(dataSet.documents[i].road_address_name  , json_string_value(jsonRoad_address_name));
                                json_t *jsonX= json_object_get(jsonObject, "x");
                                strcpy(dataSet.documents[i].x , json_string_value(jsonX));
                                json_t *jsonY= json_object_get(jsonObject, "y");
                                strcpy(dataSet.documents[i].y , json_string_value(jsonY));
                                printf("\naddress_name = %s\ncategory_group_code = %s\ncategory_group_name = %s\ncategory_name = %s\ndistance = %s\nid = %s\nphone = %s\nplace_name = %s\nplace_url = %s\nroad_address_name = %s\nx=%s\ny=%s\n\n\n", dataSet.documents[i].address_name, dataSet.documents[i].category_group_code, dataSet.documents[i].category_group_name, dataSet.documents[i].category_name, dataSet.documents[i].distance, dataSet.documents[i].id,dataSet.documents[i].phone, dataSet.documents[i].place_name, dataSet.documents[i].place_url,dataSet.documents[i].road_address_name,dataSet.documents[i].x, dataSet.documents[i].y);
                        }

                }
                json_t *jsonMetaData = json_object_get(root, "meta");
                json_t *jsonIs_end = json_object_get(jsonMetaData, "is_end");
                dataSet.meta.is_end = json_boolean_value(jsonIs_end);
                json_t *jsonPageable_count = json_object_get(jsonMetaData, "pageable_count");
                dataSet.meta.pageable_count = json_integer_value(jsonPageable_count);
                json_t *jsonTotal_count = json_object_get(jsonMetaData, "total_count");
                dataSet.meta.total_count = json_integer_value(jsonTotal_count);
                printf("Meta\nis_end = %d\npageable_count = %d\ntotal_count = %d\n",dataSet.meta.is_end, dataSet.meta.pageable_count, dataSet.meta.total_count);

                json_t *jsonSame_name = json_object_get(jsonMetaData, "same_name");
                json_t *jsonKeyword = json_object_get(jsonSame_name, "keyword");
                strcpy(dataSet.meta.same_name.keyword , json_string_value(jsonKeyword));

                json_t *jsonSelected_region = json_object_get(jsonSame_name, "selected_region");
                strcpy(dataSet.meta.same_name.selected_region , json_string_value(jsonSelected_region));

                printf("\nsame_name\nkeyword = %s\n\nselected_region = %s\n", dataSet.meta.same_name.keyword, dataSet.meta.same_name.selected_region);



        }


        close(c_socket);
        freeaddrinfo(servinfo);

}

void error_handling(char* msg){
        fputs(msg, stderr);
        fputc('\n', stderr);
        exit(1);
}

```


## 결과 화면
![image](https://user-images.githubusercontent.com/65120581/134855775-1e16b8af-009d-4348-a05d-fe2e2de46e7b.png)

![image](https://user-images.githubusercontent.com/65120581/134869147-af30f002-d999-4e8a-8f08-536da59dbffd.png)


- 배열에 5개 초과 시
![image](https://user-images.githubusercontent.com/65120581/134877196-a596f3e9-461f-49a9-b470-76b6c1efd514.png)


