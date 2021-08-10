# 계산기
- 계산의 결과를 ip를 입력한 곳으로 전송
## cal.server.c
```c
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT_NUM 3800
#define MAXLEN 256

struct cal_data
{
        int left_num;
        int right_num;
        char op;
        int result;
        short int error;
};

int main(int argc, char **argv)
{
        int sockfd;
        socklen_t addrlen;
        int cal_result;
        int left_num, right_num;
        struct sockaddr_in addr, cliaddr;
        struct cal_data rdata;

        if((sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1)
        {
                return 1;
        }

        memset((void *)&addr, 0x00, sizeof(addr));                                                                                                                                                                          
        addr.sin_family = AF_INET;
        addr.sin_addr.s_addr = htonl(INADDR_ANY);
        addr.sin_port = htons(PORT_NUM);

        addrlen = sizeof(addr);
        if(bind(sockfd, (struct sockaddr *)&addr, addrlen) == -1)
        {
                return 1;
        }

        while(1)
        {
                addrlen = sizeof(cliaddr);
                recvfrom(sockfd, (void *)&rdata, sizeof(rdata), 0, (struct sockaddr *)&cliaddr, &addrlen);
#if DEBUG
                printf("Client Info : %s (%d)\n", inet_ntoa(cliaddr.sin_addr), ntohs    (cliaddr.sin_port));
                printf("%d %c %d\n", ntohl(rdata.left_num), rdata.op, ntohl(rdata.ri    ght_num));
#endif

                left_num  = ntohl(rdata.left_num);
                right_num  = ntohl(rdata.right_num);
                switch(rdata.op)
                {
                        case '+':
                                cal_result = left_num + right_num;
                                break;
                        case '-':
                                cal_result = left_num - right_num;
                                break;
                        case '*':
                                cal_result = left_num * right_num;
                                break;
                        case '/':
                                if(right_num == 0)
                                {
                                        rdata.error = htons(2);
                                        break;
                                }
                                cal_result = left_num / right_num;
                                break;
                }

                rdata.result=htonl(cal_result);
                sendto(sockfd, (void *)&rdata, sizeof(rdata), 0, (struct sockaddr *)&cliaddr, addrlen);
        }
        return 1;
}


```

## cal_client.c

```c
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/socket.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>

#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT_NUM 3800
#define MAXLEN 256

struct cal_data
{
        int left_num;
        int right_num;
        char op;
        int result;
        short int error;
};

int main(int argc, char **argv)
{
        int sockfd;
        struct sockaddr_in addr;
        struct cal_data sdata, recvaddr;

        char msg[MAXLEN];
        int left_num;
        int right_num;
        socklen_t addrlen;

        char op[2];

        if (argc != 2)
        {
                printf("Usage : %s [ipaddress]\n", argv[0]);
                return 1;
        }
        if ( (sockfd = socket(AF_INET, SOCK_DGRAM, 0)) == -1 )
        {
                return 1;
        }
        memset((void *)&addr, 0x00, sizeof(addr));
        addr.sin_family = AF_INET;
        addr.sin_addr.s_addr = inet_addr(argv[1]);
        addr.sin_port = htons(PORT_NUM);

        while(1)
        {
                printf("> ");
                fgets(msg, MAXLEN-1, stdin);
                if(strncmp(msg, "quit\n",5) == 0)
                {
                        break;
                }
                sscanf(msg, "%d%[^0-9]%d", &left_num, op, &right_num);
                memset((void *)&sdata, 0x00, sizeof(sdata));
                sdata.left_num = htonl(left_num);
                sdata.right_num = htonl(right_num);
                sdata.op = op[0];

                addrlen = sizeof(addr);
                sendto(sockfd, (void *)&sdata, sizeof(sdata), 0,
                                (struct sockaddr *)&addr, addrlen);

                recvfrom(sockfd, (void *)&sdata, sizeof(sdata), 0,
                                (struct sockaddr *)&recvaddr, &addrlen);
                printf("%d %c %d = %d\n", ntohl(sdata.left_num), sdata.op, ntohl(sdata.right_num), ntohl(sdata.result));
        }
        close(sockfd);
}


```



### 결과화면
![image](https://user-images.githubusercontent.com/65120581/128832509-ec168351-9251-430b-9315-c7d9d6182da5.png)
- server를 먼저 실행
- client 실행 127.0.0.1(자신)로 전달 
