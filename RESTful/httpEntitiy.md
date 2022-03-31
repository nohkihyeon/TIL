![image](https://user-images.githubusercontent.com/65120581/161084066-f5b632a5-fee6-4a37-b9b2-b72c21df2917.png)
1. Application이 RestTemplate를 생성하고, URL, HTTP 메소드 등의 헤더를 담아 요청합니다.
2. RestTemplate는 HttpMessageConverter를 사용하여 requestEntity를 요청메세지로 변환합니다.
3. RestTemplate는 ClientHttpRequestFactory로부터 ClientHttpRequest를 가져와서 요청을 보냅니다.
4. ClientHttpRequest는 요청 메세지를 만들어 HTTP 프로토콜을 통해 서버와 통신합니다.
5. RestTemplate는 ResponseErrorHandler로 오류를 확인하고 있다면 처리 로직을 태웁니다.
6. ResponseErrorHandler는 오류가 있다면 ClientHttpResponse에서 응답 데이터를 가져와서 처리합니다.
7. RestTemplate는 HttpMessageConverter를 이용해서 응답 메시지를 java object(Class responseType)로 변환합니다.
8. Application에 반환됩니다.
