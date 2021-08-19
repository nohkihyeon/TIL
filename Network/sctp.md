# SCTP (Stream Control Transmission Protocol
UDP 메시지 스트리밍 특성과 TCP의 연결형 및 신뢰성 제공 특성을 조합한 프로토콜
## SCTP 특성
- 멀티 호밍 : 1세션 당 다중, IP, 동적인 Path 사용 연결
- 멀티 스트리밍 : Stream 별 독립적 순서화, 스트림 ID 별 데이터 복구, 재전송 가능
- 4-Way 연결 : DOS 공격, TCP SYN 공격 방지 가능, Verification, Cookie 값 사용
- 3-Way 종료 : TCP Half Open 문제 해결, 데이터 전송 경로 통합적 수행

## SCTP 구조
![image](https://user-images.githubusercontent.com/65120581/129989357-5795fe18-cb27-4b9c-a994-4d98b0902e21.png)
- 응용계층과 네트워크 계층 사이에 위치하며, SCTP peers 간 응용 데이터를 API로 전달받아 IP망을 통해 전송

|세션 연결|세션 종료|
|--|--|
|- SCTP 세션 연결 시 TCP의 3단계와 달리 4단계로 구성<br> - 세션에 대한 사용자 인증 및 TCP-SYN보안취약점해결|- SCTP 세션 종료 시 TCO와 달리 3단계로 구성 <br> - TCP half-Open closing 해결하여 상태 관리 최적화|
|![image](https://user-images.githubusercontent.com/65120581/129989472-71a2f1cc-3df1-4dec-87ec-3f26f8e65fcd.png)|![image](https://user-images.githubusercontent.com/65120581/129989481-a2f02bde-674e-4ad2-8b41-681838f201b1.png)


## Mobile SCTP
- 이동 단말이 세션도중 다른 IP망으로 이전 시 Seamless 핸드오버 기능 지원
- 터널링 등 라우팅 도움 없이 적용 

