# Strategy pattern

 ![스크린샷 2021-07-25 오전 12 18 25](https://user-images.githubusercontent.com/65120581/126872939-8927d439-c2ff-46ca-b556-b8164251baa3.png)
- 오리 시뮬레이셔 프로그램
  - 모든 오리는 꽥꽥 울 수 있고, 수영을 할 수 있음
  - 이 두 종류의 행위는 오리 부모 클래스에서 구현됨
  - 오리의 display 메소드는 추상 메소드임
  - 오리 클래스는 추상 클래스임
  - 오리의 모든 자식클래스는 display 메소드를 구현해야 함
  - 여기 두 클래스 외에 다양한 클래스가 상속되어 구현될 수 있음
  - 문제점 : 오리 시뮬레이션 프로그램을 개선하기 위해 fly 기능을 추가하고 싶다면??

- Design Pricinple 1
  - Identify the aspects of your application that vary and separate them from what stays the same

- Design Pricinple 2
  - Program to an interface, not an implementation
  
  
```java
public abstract calss Duck{
  protected FlyBehavior flyBehavior;
  protected QuackBehavior quackBehavior;
  ...
  public void quack(){
    quackBehavior.quack();
  }
}
```
- 의존관계가 성립하므로 바람직하지 못하다.
```java
public calss MallardDuck extends Duck{
  public MallardDuck(){
    flyBehavior = new FlyWithWings();
    quackBehavior = new Quack();
  }
  public void display(){
    System.out.println("난 청둥오리");
  }
}
```
- Duck에서 flyBehaviordhk quackBehaviorfmf private으로 바꾸어 생성자 관계 주입으로 활용하는 것이 효과적
```java
public calss MallardDuck (FlyBehavior flyBehavior, QuackBehavior quackBehavior){
  super(flyBehavior, quackBehavior);
}
```

## 장점
 - flyBehavior를 실행시간에 변경할 수 있음
```java
flyBehavior (new FlyNoWay());
duck.fly();
```
  - 새로운 오리타입을 만들기 쉬움
```java
public class ModelDuck extends Duck{ 
  public ModelDuck(){
    flyBehavior = new FlyNoWay();
    quackBehavior = new Quack();
    }
  ...
}
```
  - 새로운 행위 추가가 쉬움 
```java
public class FlyRocketPowered implements FlyBehavior {
  public void fly(){
    System.out.println(" 난 로켓으로 난다!!!"); 
    }
  }
```

- Design Pricinple 3
  - Favor composition over inheritance.


### 자바 enum
```java
public enum Flyable{ 
  FlyNoWay{
    @Override
    public void fly(){} 
  }
  FlyWithWings{
    @Override 
    public void fly(){
      System.out.println(" 난 날개로 난다!!!"); 
    }
  } 
  FlyWithRockets{
     @Override 
     public void fly(){
      System.out.println(" 난 로켓으로 난다!!!"); }
    }
      public abstract void fly(); 
  }
```

```java
public class MallardDuck extends Duck { 
  public MallardDuck(){
	  setFlyStrategy(Flyable.FlyWithWings);
 }
```

### 자바 Lambda
```java
 public interface Flyable {
  void fly()
}
```

```java
Flyable flyWithBallons = ()->System.out.println("풍선으로 날고 있음"); 
Duck d = new MallardDuck();
d.display();
d.setFlyStrategy(flyWithBallons);
d.fly();
d.setFlyStrategy(()->System.out.println("Stealth 기능으로 날고 있음");
d.fly();
```

 
