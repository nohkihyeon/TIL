# UDP 소켓 프로그래밍

- TCP가 연결형의 신뢰성이 담보된 자료 전송 프로토콜임에 비해서 UDP는 비연결형의 신뢰성이 다소 결여된 데이터그램 프로토콜이다.
- UDP에서는 상대방이 자료를 제대로 수신했는지 확인하지 못한다.
- UDP는 통신 경로를 설정하기 위한 별도의 과정이 필요 없다. 이로 인한 추가 overhead나 시간 지연이 발생하지 않는다.
- UDP는 하나의 자료를 여러 명의 수신자에게전달 할 수 있다. 네트워크를 효율적으로 사용
- 전송하는 자료가 하나의 패킷으로만 구성되거나 LAN 등 안전성이 높은 네트워크에서는 UDP를 사용하는 것이 효과적
- UDP를 사용하는 대표적인 예 : DNS, NFS, SNMP
- UDP는 Broadcasting과 Multicasting을 지원

# UDP 기반 에코 프로그램 구현

## 서버 프로그램
- UDP를 이용해서 구현
- 접속점의 포트를 9200 설정
- 서버는 자신의 시스템의 9200번 포트를 목적지로 하는 모든 패킷을 받아 처리
```c
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdlib.h>


int main(int argc, char *argv[]){
        int sd;
        struct sockaddr_in s_addr, c_addr;
        char buff[BUFSIZ];
        int n, n_recv;
        int addr_len;

        sd = socket(AF_INET, SOCK_DGRAM, 0);

        bzero(&s_addr, sizeof(s_addr));
        s_addr.sin_family = AF_INET;
        s_addr.sin_addr.s_addr = htonl(INADDR_ANY);
        s_addr.sin_port = htons(9200);

        if (bind(sd, (struct sockaddr *) &s_addr, sizeof(s_addr)) < 0){
                fprintf(stderr, "bind() error");
                exit(-2);
        }

        while(1){
                fprintf(stderr, "waiting\n");

                addr_len  = sizeof(c_addr);
                if((n_recv = recvfrom(sd, buff, sizeof(buff), 0, (struct sockaddr *)&c_addr, &addr_len)) < 0) {		// 9200번 포트에서 클라이언트가 전송한 자료를 읽고 자료를 전송한 구조체 c_addr로 받아온다.
                        fprintf(stderr, "recvfrom() error");
                        exit(-3);
                }

                if((n = sendto(sd, buff, n_recv, 0, (struct sockaddr *) &c_addr, sizeof(c_addr))) < 0 ) {		// 클라이언트로부터 전송받은 자료를 그ㅐ돌 해당 클라이언트로 전송
                        fprintf(stderr, "sendto() error");
                        exit(-3);
                }
        }
        close(sd);
}


```
- 소켓을 생성, 연결할 주소(IP, 포트번호)를 준비, 소켓고 ㅏ연결하는 일련으 과정은 TCP와 동일
- 자료를 송수신할 때 read, write함수 대신 sendto 함수를 사용
- 자료의 수신지를 알아낼 수 있는 recvfrom 함수 사용


```c
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char * argv[]) {
        int sd;
        struct sockaddr_in s_addr;
        char sndBuffer[BUFSIZ];		// 키보드에 입력받은 문자열을 저장
        int n, n_send;
        int addr_len;

        sd = socket(AF_INET, SOCK_DGRAM, 0);

        bzero(&s_addr, sizeof(s_addr));
        s_addr.sin_family = AF_INET;
        s_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
        s_addr.sin_port = htons(9200);

        while(1){
                fprintf(stderr, "waiting\n");

                if ((n = read(0, sndBuffer, BUFSIZ)) > 0) {	// 문자열을 서버로 전송
                        sndBuffer[n] = '\0';
                        if(!strcmp(sndBuffer, "quit\n")) {
                                break;
                        }

                        printf("original Data : %s", sndBuffer);

                        if((n_send = sendto(sd, sndBuffer, strlen(sndBuffer), 0, (struct sockaddr *) &s_addr, sizeof(s_addr))) < 0){ // 자신의 소켓으로 전송되어 오는 자료를 읽어 들인다.
                                fprintf(stderr, "sendto() error");
                                exit(-3);
                        }

                        addr_len  =sizeof(s_addr);
                        if((n = recvfrom(sd, sndBuffer, sizeof(sndBuffer), 0, NULL, NULL)) < 0) {
                                fprintf(stderr, "recvfrom() error");
                                exit(-3);
                        }
                        sndBuffer[n] = '\0';

                        printf("echoed Data : %s", sndBuffer);
                }
        }
        close(sd);
}
```

### 결과화면 <br>
![image](https://user-images.githubusercontent.com/65120581/129321682-3ed5689f-3cb2-45d4-a33b-33aa33314102.png)
