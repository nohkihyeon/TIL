# LTE NRM(Network Reference Model)

![image](https://user-images.githubusercontent.com/65120581/126600583-8b1dfef4-89ba-453f-b5e5-b77ade8d7476.png)

## UE (User Equipment)
- 사용자 단말 그 형태는 Smartphone일 수도 있고, 아니면 데이터 통신만을 목적으로 하는 USB dongle(USB type으로 노트북에 연결하여 무선 인터넷 사용)일 수도 있다.
- UE에는 "가입자 식별/인증을 위한 IMSI 값이 내장된 USIM 카드"가 삽입될 수 있게 되어 있다.
- UE는 LTE chip을 내장하고 있어 LTE 망에 붙을 수 있는 단말이다.

## eNB (Evolved Node B)
- "LTE 기지국"이라 불리며, UE와 LTE 네트워크 간에 무선 연결을 제공하는 장비
- 위 그림에서 무선 연결은 UE와 eNB간이고, 나머지는 다 유선 연결(IP망을 통한)

## S-GW (Serving Gateway)
- eNB간 핸드오버시에 anchoring 역할
- anchoring 역할 : 하나의 S-GW를 축으로 eNB1에서 eNB2로 UE의 핸드오버가 발생

## P-GW (PDN Gateway)
- 단말에 IP 주소를 할당(DHCP 프로토콜이 아닌 3GPP에서 규정하고 있는 UE 접속 절차를 통해서 IP 주소 할당)
-  P-GW는 S-GW들에 대한 anchoring을 수행
-  UE가 이동중에 S-GW1에서 S-GW2로 변경이 되는 경우(S-GW1이 관리하는 eNB에서 S-GW2가 관리하는 eNB로 이동) P-GW가 anchoring 포인트가 되는
-  UE별로 서로 다른 QoS 정책을 적용(우선순위, 대역폭 제어등의 행위를 수행)
-  UE별로 Accounting Data를 관리합니다. Accounting Data라 함은 대표적으로 상햐향 트래픽 양(# of bytes, # of packets), 접속 시간등이 될 수 있으며, P-GW는 이 데이터를 CDR(Charging Data Record) 형태로 OFCS에게 전달
-  
## MME (Mobility Management Entity)
- LTE 망의 "두뇌" 역할을 하는 장비
- UE를 인증(Authentication)
- 인증 프로토콜은 EPS-AKA
- UE를 인증하기 위한 Key 정보는 HSS에 들어있고, 이 Key 정보를 HSS로 부터 받아서 UE 인증을 수행
-  EPS 베어러를 관리
-  EPS 베어라란 쉽게 말해서 UE가 인터넷을 사용하기 위해 {UE - eNB - S-GW - P-GW} 구간에서 생성되는 논리적인 터널(GTP 터널)이며, MME는 그 터널의 생성/변경/해제 등의 행위에 관여
-  가입자의 Mobility 상태를 관리

## HSS (Home Subscriber Server)
- 각 UE(가입자)별로 (1)인증을 위한 Key 정보와 (2) 가입자 프로파일을 가지고 있는 LTE망의 중앙 DB
- 가입자 프로파일에는 각 가입자가 가입한 서비스 상품에 맞는 QoS 등급 정보(우선순위, 최대 사용 가능 대역폭 등)가 들어있다.
- 인증을 위한 Key 정보와 가입자 프로파일은 UE가 LTE망에 접속할때 HSS에서 MME로 전달
- 이후 "두뇌" 역할을 하는 MME가 UE에 대한 인증도 수행하고 가입자 프로파일(QoS 정보)을 기반으로 EPS 베어러를 생성

## PCRF (Policy and Charging Rule Function)
- UE별로 정책(Policy)과 과금(Charging)에 대한 룰(Rule)을 정하는 장비
- 정책(Policy)은 UE가 사용할 QoS 정보로 보시면 되구요, 과금(Charging)은 offline 과금을 할 건지 online 과금을 할 것인지에 대한 정보
- 정보들은 PCRF에서 P-GW로 전달되고, P-GW는 PCRF가 준 정보를 기반으로 UE에 대한 제어(QoS, Charging)을 수행

## SPR (Subscriber Profile Repository)
- UE별 Policy 및 Charging 룰(Access Profile)은 PCRF에 저장되어 있지 않고, 대신 SPR이란 DB에 저장
- PCRF는 SPR로 부터 UE에 대한 Access Profile를 가지고 오게 된다.


## OCS (Online Charging System)
- 우라나라의 경우 후불제(Postpaid)가 일반적이지만, 선불제(Prepaid)를 사용하고 있는 해외 통신사업자도 있다.
- 실시간 사용량은 P-GW에서 관리하고 그  정보를 OCS로 전달해 주면 OCS가 사용자별로 남은 사용량(balance 혹은 credit이라 부릅니다)이 얼마인지 중앙 관리를 하고, credit을 다 사용한 가입자를 판별/판단하여 더 이상 인터넷 사용을 못하도록 P-GW에  그 사실을 알려준다


## OFCS (Offline Charging System)
- P-GW가 전달해 주는 CDR을 받아 중앙에서 관리하는 장비


## PDN (Packet Data Network)
-  Internet / PDN = Internet = IP Network

> ## [(참조)netmanias](https://netmanias.com/ko/post/blog/5344/lte/lte-network-architecture-network-reference-model)
