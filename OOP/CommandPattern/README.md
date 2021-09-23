# Command Pattern
- 패턴의 분류 : 행위 패턴
- 패턴의 목적: This pattern encapsulates a request as an object, thereby letting you parameterize other objects with different request, queue, or log requests, and support undoable operation. – GoF – (parameterize objects: 명령 객체를 등록하고 나중에 실행할 수 있도록 한다는 의미)
- 패턴의 수준: component
- 패턴의 적용
  - 요청을 하는 소스와 그 요청을 실제 실행하는 객체를 분리하기 위해
  -  실행된 행위에 대한 undo기능, 로그 기능이 필요할 때
  -  요청을 큥 유지하 나중에 실행하 필요성이 있을 때
- 명령 interface: 구체적 명령 객체를 위한 interface, 보통 execute와 undo 메소드를 가지고 있음
- 구체적 명령: 명령 처리자와 행동을 연결함. 처리자가 제공하는 메소드를 이용하여 실제 행동을 실행함. 구체적 명령은 처리자와 의존관계를 맺음
- 실행자(invoker): 요청의 실행을 요구함. 보통 명령 객체를 유지함
-  처리자(receiver): 요청을 수행하기 위해 해야 하는 기능이 구현되어 있는 객체. 어떤 종류의 객체도 처리자가 될 수 있음
-  클라이언트: 구체적 명령 객체를 생성하고 처리자와 연결함

## 패턴의 구조 
<img width="656" alt="스크린샷 2021-09-23 오후 11 53 48" src="https://user-images.githubusercontent.com/65120581/134530801-bbfbf20f-ba56-4948-8145-a2d04c6d4c66.png">


- 장점
  - 명령 객체르 여러 객체 공유하 수 있음
  - 실행시간에 명령고 명령 처리자르 변경할 수 있음
  - 새로유 명려 객체를 만들기 쉬움
  - 명령의 요청자와 처리자를 분리하 수 있음
  - 여러 명려 객체를 조합하여 더 큰 명령을 만들 수 있음
- 단점
  - 작은 명령 클래스들이 많이 만들어질 수 있으
  - 코드 자체가 복잡해질 수 있음
