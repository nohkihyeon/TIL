# tracert 사용법
tracert는 출발지에서 목적지까지 가는 라우팅 경로를 나타내는 명령어
도착지까지의 홉수와 각 구간 별 응답시간도 알 수 있다.
![image](https://user-images.githubusercontent.com/65120581/132459863-1f3fee62-2f7f-4d77-a5fb-4d6bffd55c49.png)
- 윈도우에서는 tracert 사용
- 리눅스에서는 traceroute 사용
- tracert도 ping에서 사용하는 ICMP를 사용 최대 30홉까지의 경로를 나타낸다.
- 사용방법 : tracert [도메인 네임] / traceroute 8.8.8.8 ==>> traceroute dns.google
![image](https://user-images.githubusercontent.com/65120581/132460398-51855d7b-a15e-4dbf-ae95-cb640e35fc82.png)
- 위의 그림은 traceroute의 목적지인 dns.google까지의 경로를 보여주며, 목적지까지의 홉수는 8이다.
- 중간에 *** 표시는 보안상의 이유로 라우터 또는 장비의 IP가 외부에 노출되지 않도록 ICMP를 차단되어 있어 응답하지 않은 경우이다.

## traceroute --help
![image](https://user-images.githubusercontent.com/65120581/132460652-c21c0811-8939-4cc5-b311-28af174d72a9.png)

## 운영체제별 홉수
- Windows = 128홉
- Linux = 64홉
- Cisco = 256홉
- mac OS = 60홉
1. nslookup으로 목적지 ip주소를 알아낸다. <br>
![image](https://user-images.githubusercontent.com/65120581/132461728-6f260675-6f49-41e9-aef0-25304bfcd0b7.png)

2. ping으로 목적지에 도착한 후 남은 홉수를 알아낸다. (TTL = 115) <br>
 ![image](https://user-images.githubusercontent.com/65120581/132461799-f71db1d9-a0b2-4b90-810f-4459cfa17413.png)

3. tracert로 목적지(8.8.8.8)까지의 홉수 <br>
![image](https://user-images.githubusercontent.com/65120581/132461959-0d506fa6-e9e4-41d3-a241-e0872a48e0b3.png)

4. TTL + 목적지까지의 홉수 = 128
- os가 Windows로 추정된다.
