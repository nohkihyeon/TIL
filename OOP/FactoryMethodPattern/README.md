# Factory Method Pattern

## 패턴의 분류 : 생성 패턴
### 패턴의 목적 : This pattern defines an interface for creating an object, but let subclasses decide which class to instantiate. Factory method lets a class defer instantiation to the subclasses. – GoF –

## 패턴의 적용
- 확장 가능한 프레임워크를 제공하고 싶을 때 사용할 수 있음
- 구현 관점에서 어떤 종류의 객체를 생성할지 나중에 결정할 수 있도록 해줌
- 같은 상위 타입을 가지는 여러 종류의 객체를 생성해야 하며, 이들을 주로 상위 타입을 통해 좆가할 경우
- 부모 클래스가 아니라 자식 클래스가 어떤 종류의 객체를 생성할지 결정할 필요가 있을 경우 (객체가 has-a로 유지하는 것의 종류가 다를 수 있을 때, 그 종류의 결정을 하위 클래스에게 위임할 필요가 있을 때)
- 객체를 생성해야 한다는 것은 알지만 어떤 종류의 객체를 생성할지 모를 경우

## 패턴 적용 가능 시나리오
- 우편의 배송은 항공, 차량, 기차를 이용할 수 있다.
- 우편과 관련된 모든 기능이 Mail 클래스에 정의되어 있을 때, 이 클래스를 상속받는 항공우편, 지상우편을 정의하여 배송에 필요한 객체 생성만 담당하도록 할 수 있음

## 패턴의 참여자
- 생성자(creator) : 팩토리 메소드가 선언 및 정의되는 추상 타입
- 구체적 생성자(concrete creator) : 팩토리 메소드를 재정의하여 실제 객체를 생성하는 클래스
- 제품(product) : 생성될 객체들의 추상 클래스 또는 interface
- 구체적 제품 클래스(concrete product) : 실제 생성될 객체의 클래스

## 패턴의 구조 
<img width="766" alt="스크린샷 2021-08-07 오후 3 51 00" src="https://user-images.githubusercontent.com/65120581/128591411-9b04617b-0051-493e-9a64-02143b147096.png">

## 참여자간 협력
- 생산자 클래스나 interface는 객체의 생성을 하위 클래스에 위임함
- 보통 역할의 이름과 달리 객체의 생성이 생산자의 핵심 역할이 아님
- 구체적 생산자는 매번 객체를 생성해야 하는 것은 아니며, 객체 풀과 같이 다양한 생성 로직을 사용할 수 있음

## 태펀의 구현
- 생성될 모든 종류의 제품이 같은 추상 타입(제품 참여자)을 사용하여야 함

## 패턴의 장단점
- 장점
  - 객체를 직접 생성하는 것보다 객체를 생성하는 추상화된 메소드를 이용하는 것이 더욱 유연한 방법이며, 이 패턴도 팩토리 메소드라는 것을 이용하여 객체를 생성함
  - 확장 가능한 객체 생성 프레임워크를 만들 수 있음 생산자는 구체적인 상품에 대해 알 필요가 없음(구체적 생산자는 구체적 상품과 밀접하게 연결됨)
  - 구체적 생산자의 응집성은 매우 높음 
- 단점
  - 새로운 종류의 객체를 생성해야 하면 기존 구체적 제품 클래스를 수정해야 할 수 있음
  - 생성되는 객체가 제품 추상 클래스나 interface를 통해 원활하게 사용될 수 있지 않으면 팩토리 메소드 패턴의 사용이 효과적이지 못할 수 있음
  - 구체적 생산자 클래스와 구체적 제품 간에는 단단한 연결은 불가피
