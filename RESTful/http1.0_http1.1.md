# http1.0 http1.1 차이
- http/1.0에서는 서버와 클라이언트 간 트랜잭션 발생하여 connection 후 처리가 끝나면 바로 close한다.
![image](https://user-images.githubusercontent.com/65120581/134628312-cfc0d540-6b90-47f0-a43c-158a2336f29b.png)
- connectiong : Close
![image](https://user-images.githubusercontent.com/65120581/134628734-bede47fb-4e14-4fd0-94f8-af91189c227f.png)

- http/1.1에서는 연결시 발생하는 지연을 줄이고자 connection을 재활용한다. Keep-Alive 때문에 타임 아웃되는 현상이 발생할 수 있다.
![image](https://user-images.githubusercontent.com/65120581/134628151-9fe1ba9f-c657-4b67-a8b1-8254ba57f3ab.png)
- connection : Keep Alive
![image](https://user-images.githubusercontent.com/65120581/134628805-fa532090-275e-4384-809d-ac142d648674.png)


## Keep-Alive
Keep-Alive 일반 헤더는 송신자가 연결에 대한 타임아웃과 요청 최대 개수를 어떻게 정했는지에 대해 알려준다.

- Keep Alive의 유지 시간은 연결된 Socket에 I/O Access가 마지막으로 종료된 시점부터 정의된 시간까지 Access가 없더라도 세션을 유지하는 구조이다.
- 정의된 시간내에 Access가 이루어진다면 계속 연결된 상태를 유지할 수 있게 된다.
- 서버 자원은 무한정이 아니다. 그렇기 때문에 이러한 접속을 계속 유지하는 것은 Server에 손실을 발생시킨다.
- 서버와 연결을 맺을 수 있는 Socket은 한정되어 있고 연결이 오래 지속되면 다른 사람들이 연결을 못하게되는 상황이 닥친다.
![image](https://user-images.githubusercontent.com/65120581/134629716-326c5b24-f12c-4115-93ed-0edb365efb86.png)
