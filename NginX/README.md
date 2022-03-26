# Nginx
- 동시 접속 처리에 특화된 웹 서버 프로그램
- Apache 보다 동작이 단순하고, 전달자 역할만 하기 때문에 동시접속 처리에 특화


# Nginx(웹서버)의 역할
1. 정적 파일을 처리하는 http 서버로서의 역할 <br>
![Zpw9D7x](https://user-images.githubusercontent.com/65120581/159229733-ea93b17a-35df-40dd-b359-47adf81a4d12.png)

2. 응용프로그램 서버에 요청을 보내는 리버스 프록시로서의 역할 <br>
![yReDKjj](https://user-images.githubusercontent.com/65120581/159229798-3402a15e-046b-422f-a06e-1a814a79a967.png)


- nginx : 서버시작
- nginx -s stop : 서버종료(워커들이 요청을 처리중이더라도 그냥 종료한다.)
- nginx -s quit : 워커 프로세스가 현재 요청 처리를 완료할 때까지 대기하고 모두 처리완료된 후에 서버 종료.
- nginx -s reload : nginx config를 새로 로드한다. 마스터 프로세스가 설정을 다시 로드하라는 요청을 받으면 설정 유효성 검사후 새로운 워커 프로세스를 시작하고, 이전 워커 프로세스에게 종료 메시지를 보내게 되고 이전 워커 프로세스는 요청을 완료하게 되면 종료된다.

# Reverse Proxy
![image](https://user-images.githubusercontent.com/65120581/160223720-7d9a16f6-db35-4da6-9341-9428d0ae2bf6.jpeg)
- nginx는 리버스 프록시로도 활용 가능
- 보안 : 외부 사용자로부터 내부망에 있는 서버의 존재를 숨길 수 있다.
- 로드밸런싱 : 리버스 프록시 서버가 내 서버에 대하 정보르 알고 있으므로 각 서버의 상태에 따라 부하르 분산시키며 요청으 전달





>> https://coding-start.tistory.com/381 [코딩스타트]
>> https://whatisthenext.tistory.com/123

>> [생활코딩-NGINX](https://opentutorials.org/module/384/3462)
