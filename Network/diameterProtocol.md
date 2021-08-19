# Diameter Protocol

- 다이어미터 프로토콜은 컴퓨터 네트워크에서 사용되는 인증, 인과 및 과금 프로토콜이다.
- RADIUS 프로토콜보다 더 유용하게 진화되었고 RADIUS 프로토콜을 대체하고 있다.
- 인증, 인가 및 과금(Authentication, Authorization and Accounting, AAA) 용 프래임 웍을 제공할 목적
- 다이어미터 애플리케이션은 Cx, Dh, Dx, Rf, Ro와 Sh 인터페이스를 지원

![image](https://user-images.githubusercontent.com/65120581/129839392-c656bec9-4a9d-489d-b2df-5a89b007154b.png)

## diameter protocol 주요 기능
- 신뢰성 있는 전송 프로토콜 (TCP 또는 SCTP)
- 네트워크 또는 전송 계층 보안(IPsec, TLS)
- 완벽하게 호환되지 않지만 RADIUS에서 Diameter로의 전환을 지원
- 속성값쌍(Attribute-value pairs, AVPS)과 식별자를 위한 대용량 주소 공간(32비트)
- 서버에서 시작되는 몇몇 메시지의 지원을 제외한 Client_Server 프로토콜
- 상태 기반 모델 및 상태 없는 모델 모두 사용가능
- 응용 계층(Application layer) 응답 지원, 장애 처리 방법 및 상태 관리 정의
- 오류 인지
- 로밍 지원
- 사용자 세션과 과금을 위한 기본적인 지원

## 패킷 형식
패킷은 다이어미터 헤더와 다이어미터 메시지와 관련된 정보를 캡슐화하기 위한 다양한 개수의 속성값 쌍(Attribute-Value Pairs, or AVPs)으로 이루어져 있다.


## 어플리캐이션-ID
- 애플리케이션-ID는 메시지가 어떤 다이어미터 애플리케이션에 적용가능한지 구별하는데 사용된다. 그 애플리케이션은 인증 애플리케이션이 될 수도 있고 과금 또는 벤더 특화 애플리케이션이 될 수도 있다.
- 어떤 다이어미터 확장을 따르는 다이어미터 에이전트는 Capabilities-Exchange-Request (CER)와 Capabilities-Exchange-Answer (CEA) 명령의 Auth-Application-Id Attribute에 특정값을 포함함으로써 지원을 공식화한다.
- 헤더 안에 있는 애플리케이션-ID의 값은 관련된 애플리케이션-ID APV와 같다. 예를 들어, 다이어미터 신용-제어 애플리케이션 용 Credit-Control-Request (CCR) 와 Credit-Control-Answer (CCA) 명령의 애플리케이션-ID 값와 인증-애플리케이션-ID 속성값은 4이다

## Hop-by-Hop 식별자 (4Bytes)
- 요청에 대한 응답에 존재하는 Hop-by-Hop 식별자는 같은 값이어야 하기 때문에 이 식별자는 응답과 요청을 일치시키는데 사용되며 부호가 없는 32-비트 정수 필드로 구성된다.(네크워크 바이트 순서)

- 다이어미터 프로토콜은 장애 조치를 위해 사용되는 트랜잭션 상태를 릴레이와 프록시 에이전트가 유지할 것을 요구한다. 트랜잭션 상태는 요청을 전달할 때, 그 Hop-by-Hop 식별자가 저장된다는 의미를 내포하고 있다; 필드는 대응하는 응답을 수신 할 때 원래의 값으로 복원되는 로컬 고유 식별자로 대체된다. 요청 상태는 응답의 수신시에 해제된다. 수신된 응답이 알고있는 Hop-by-Hop 식별자와 일치하지 않을 때 다이어미터 에이전트는 이 응답을 무시한다.

## End-to-End 식별자 (4Bytes)
- End-to-End 식별자는 Origin-Host AVP 조합과 함께 중복된 메시지를 감지하는데 사용되는 부호없는 32-bit 정수 필드이다.(네트워크 바이트 순서)

- 요청을 생성했을 때, End-to-End 식별자는 로컬 고유 식별자로 설정된다. End-to-End 식별자는 어떤 종류의 다이어미터 에이전트도 수정할 수 없고, 대응하는 요청에 있는 같은 값이 응답에 사용된다.
![image](https://user-images.githubusercontent.com/65120581/129839416-cbe8c11e-c7f3-4332-a84b-df127030ef81.png)

 
## 메시지 흐름
- 두 다이어미터 피어(peer) 간의 통신은 전송 연결(transport connection)이 이루어지면서 시작된다(TCP 또는 SCTP). 이후 한 쪽에서 Capabilities-Exchange-Answer (CEA)로 응답하는 또 다른 피어로 Capabilities-Exchange-Request (CER)를 보낸다. RFC 3588을 따르는 피어 TLS (Transport Layer Security)는 추가적으로 협상될 수 있다. 
- RFC 6733을 따르는 피어 TLS 협상은 CER/CEA 이전에 추가적으로 이루어질 수 있다.
- 이후 애플리케이션 메시지 교환을 위한 연결은 준비 완료된다.
- 이정 시간동안 메시지 교환이 없다면 둘 중에 한 쪽에서 Device-Watchdog-Request (DWR)을 보낼 수 있고 다른 쪽은 Device-Watchdog-Answer로 응답해야한다.
- 둘 중 한쪽에서 다른 쪽에서 Disconnect-Peer-Answer로 응답해야하는 Disconnect-Peer-Request (DPR)을 보내 통신을 종료할 수 있다. 이후 전송 연결은 종료된다.

![image](https://user-images.githubusercontent.com/65120581/129839992-e5f6e3b6-60e6-49f6-a441-19b871f438fb.png)

