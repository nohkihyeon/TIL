# Socket close() shutdown() 차이

## int close(int sockfd);
- 소켓을 닫고 통신을 종료 sockfd는 닫을 소켓 번호
- 성공하면 0을 반환
- 실패하면 1을 반환
- 닫힌 소켓은 더이상 사용 불가

내부적으로 TCP는 send buffer와 recv buffer가 있다. close를 호출하여 send buffer에 보낼 데이터가 남아 있다면 남아 있는 것을 모두 보낸 후에 TCP 종료 절차를 따른다.
socket으로 소켓을 열면 참조 카운터가 1 증가한다.
close는 참조 카운터를 1감소 시킨다. 그러다가 참조카운터가 0이 되면 소켓을 닫는다.

## int shutdown(int sockfd, int howto);
- 네트워크 연겨을 종료시키는 데 사용
- close()와 차이
  - close는 참조 카운터를 1감소시키키고 참조 카운터가 0이 되면 종료하지만 shutdown()은 참조 카운터와 상관없이 TCP의 연결 종료 절차를 시작 
  - close() 함수는 양방향(send recv) 둘 다 조료시키는데 반해, shutdown함수는 howto 인자에 따라 동작이 달라진다.
- howto 인자
  - SHUT_RD   : 연결의 recv 한쪽만 닫는다.
  - SHUT_WR   : 연결의 send 한쪽만 닫는다. send buffer에 남아 있는 데이터는 모두 보낸 뒤에 TCP 연결 종료 절차 실행
  - SHUT_RDWR : 연결의 양쪽 모두 받는다. 

> ### 참조
> [C, Socket close()와 shutdown() 차이 비교|작성자 몽키몽키](https://blog.naver.com/PostView.naver?blogId=cache798&logNo=120011256015&parentCategoryNo=&categoryNo=76&viewDate=&isShowPopularPosts=false&from=postList)
