# HTTP 역사
![image](https://user-images.githubusercontent.com/65120581/132609062-0dd54019-cd8f-43e8-89e5-68d81467a7f7.png)

## 1. HTTP/0.9 - 원라인 프로토콜
1989년 제네바의 CERN에서 일하고 있던 Tim Berners-LEE는 인터넷의 하이퍼텍스트 시스템을 만들기 위한 제안을 하였다. 이렇게 HTML, HTTP, WorldWideWeb, httpd의 초기 버전이 1990년 말에 완료되었고 첫번째 서버는 1991년 초에 CERN 외부에서 가동을 시작하게 되었다.

초기에는 버전 번호가 존재하지 않았지만, 이후에 다른 버전들과 구분하기 위해서 0.9라는 버전을 붙이게 되었다. 0.9 버전은 단일 라인으로 구성 되었으며 path는 GET이 유일했다. 이렇게 제한적인 기능을 갖고 있었던 HTTP/0.9는 다음과 같은 특징을 갖고 있었다.
HTTP의 초기 버전에는 버전 정보가 없었고 차후에 구분을 위해 0.9라고 불리게 되었다고 합니다. 아주 단순하게 GET 통신만 가능하고 이후에 버전에 존재하는 HTTP 헤더가 없기 때문에 전송은 HTML 문서만 가능하고 다른 유형은 전송할 수 없다.
- 클라이언트-서버, 요청-응답 프로토콜
- TCP/IP 링크를 통해 실행되는 ASCII 프로토콜
- 하이퍼 텍스트 문서(HTML)을 전송하도록 설계
- 서버와 클라이언트 간의 연결은 모든 요청 후에 닫힌다.

## 2. HTTP/1.0
HTTP/1.0 에서는 상태코드가 응답값 시작 부분에 포함되어 요청에 대한 성공과 실패를 바로 확인할 수 있게 되었다. 그리고 위에 언급하였던 HTTP 헤더가 요청과 응답 모두에 추가되어 프로토콜의 확상이 가능해지고 헤더의 ‘Content-Type’의 도움으로 HTML 파일 이외의 다른 문서들도 전송이 가능하게 되었다. 또한 메서드 POST, HEAD가 추가
```html
# html 요청
GET /mypage.html HTTP/1.0
User-Agent: NCSA_Mosaic/2.0 (Windows 3.1)

# html 응답
200 OK
Date: Tue, 15 Nov 1994 08:12:31 GMT
Server: CERN/3.0 libwww/2.17
Content-Type: text/html
<HTML>
A page with an image
  <IMG SRC="/myimage.gif">
</HTML>
  
# gif 요청
GET / myimage.gif HTTP/1.0
  
# gif 응답
200 OK  
...
Content-Type: text/gif
```


## 3. HTTP/1.1 - HTTP 표준
1995년부터 다양한 HTTP/1.0 구현이 동시에 진행되었고, 몇달 뒤 1997년 초에 HTTP/1.1이 발표되었다. 그 후 2년 반 후인 199년 6월 여러 개선 사항과 업데이트가 표준에 통합되어 RFC 2616으로 출시 되었다.

## 4. HTTP/2
HTTP/2는 2010년 전반기에 구글이 실험적인 SPDY 프로토콜을 구현해서 클라이언트와 서버 간의 데이터 교환을 대체할 수단을 실증했다고 한다. 그리고 그것이 HTTP/2의 기초로서 기여했다.
- SPDY와 이를 기반으로한 HTTP2의 등장
  - SPDY는 Google에서 개발한 시험용 프로토콜이며 20009년 중반에 발표되었다. 
  - > 참고: PLT 50% 개선을 실현하기 위한 SPDY의 목표는 새 바이너리 프레이밍 계층을 도입하여 요청/응답 다중화, 우선순위 지정 및 헤더 압축을 지원함으로써 기본 TCP 연결을 보다 효율적으로 사용하는 것이었습니다. Latency as a Performance Bottleneck을 참조하세요.
  - 성능 향상에 힘입어 SPDY를 사용하는 사이트가 늘어나게 되었고 사실상의 표준이 되었다. 이러한 상황을 주시하고 있던 HTTP-WG는 HTTP/2 표준을 선보이려는 노력을 했고 이 프로토콜의 출발점을 SPDY 사양을 채택하게 되었다. 이렇게 2012년부터 2015년까지 3년간의 노력으로 HTTP/2 표준이 발행되게 되었다. 그렇게 몇년간 함께 발전해온 SPDY는 지원을 중단하며, HTTP2가 널리 채택된다는 말을 남기고 사라지게 되었다.



> # 참고
> ### [HTTP의 버전 별 차이에 대해](https://falsy.me/http%EC%9D%98-%EB%B2%84%EC%A0%84-%EB%B3%84-%EC%B0%A8%EC%9D%B4%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B3%A0-ubuntu-nginx%EC%97%90-http-2%EB%A5%BC-%EC%A0%81%EC%9A%A9%ED%95%B4/)
> ### [HTTP/2 소개 ](https://developers.google.com/web/fundamentals/performance/http2?hl=ko)


- HTTP/2 Test [GEEKFLARE](https://gf.dev/tests/5sdp4xotd2a)
