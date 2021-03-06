# 추상 팩토리 패턴

## 패턴의 분류 : 생성 패턴
## 패턴의 목적 : This pattern provide an interface for creating families of related or dependent objects without specifying their concrete classes. – GoF –
## 패턴의 적용
- 시스템이 특정 종류의 제품군으로 구성이 되어야 할 경우
- 특정 종류의 제품군의 동시 사용을 강제화할 필요가 있는 경우
- 생성되는 구체적 제품군을 숨기는 제품군 생성 라이브러리를 제공하고 싶은 경우
- 패턴 적용 가능시나리오 : 함께 사용해야 하는 GUI 컴포넌트를 제공하기 위해 사용할 수 있음
## 패턴의 참여자
- 추상 팩토리(abstract factory): 제품군에 소속된 각 제품을 생성하기 위한 메소드를 제공함. 이 매소드들의 합이 제품군의 소속된 제품의 목록 역할까지 할 수 있음
- 구체적 팩토리(concrete factory): 제품을 실제 생성하는 클래스
- 제품(abstract product): 생성될 객체의 추상 클래스 또는 interface. 최대 제품군에 소속된 제품 종류 만큼 필요할 수 있음
- 구체적 제품(concrete product): 실제 생성될 객체의 클래스

## 패턴의 구조 
<img width="938" alt="스크린샷 2021-08-17 오전 12 17 18" src="https://user-images.githubusercontent.com/65120581/129587304-de4e1998-7e26-40f8-99de-32f5d0e963f8.png"><br>
- 특정 제품군의 제품을 생성하고 싶으면 해당 구체적 팩토리를 사용함
- 클라이언트는 모든 구체적 팩토리를 사용할 수 있음
## 패턴의 장단점
- 장점
  - 클라이언트는 구체적 타입 대신에 추상 타입을 통해 객체를 사용함
  - 사용하는 제품군의 변경을 용이하게 해줌
  - 사용하는 객체 간 일관성(서로 호환된다는 것)을 확신할 수 있음 
- 단점
  - 구체적 팩토리는 구체적 제품과 단단하게 연결되어 있으며, 생성할 수 있는 제품군이 추상적 팩토리 interface를 통해 고정되어 있음. 따라서 제품군에 새 종류의 제품을 추가하는 것이 힘듦

## 패턴의 변형
- 제품군의 각 제품마다 하나의 메소드를 제공하는 형태가 아니라 인자를 이용하여 제품을 생성하는 형태 로 만들 수 있음. 하지만 모든 제품을 아우르는 타입을 만들어야 하기 때문에 C++, 자바와 같은 언어들을 이용하여 이와 같은 방법으로 구현하는 것은 쉽지 않음


## 관련 패턴
- 싱글톤 패턴 : 보통 구체적 팩토리들은 효율성 때문에 싱글톤 패턴으로 구현됨
