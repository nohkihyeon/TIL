### 1. 구체적인 클래스

```java
public class Point {
	public double x;
	public double y;
}
```

- 명확하게 직교 좌표계를 사용하는지 알 수 있음
- 개별적으로 좌표값을 읽고 설정 (구현을 노출)
- 각 변수마다 getter 함수와 setter 함수를 제공한다면 구현을 외부로 노출
- 변수 사이에 함수를 넣는다 한들 구현이 감춰지지 않고, 구현을 감추기 위해 추상화 필요!

### 2. 추상적인 클래스

```java
public interface Point {
	double getX();
	double getY();
	void setCartesian(double x, double y);
	double getR();
	double getTheta();
	void setPolar(double r, double theta);
}
```

- 점이 직교 좌표계를 사용하는지 극 좌표계를 사용하는지 알 수 없음
- 인터페이스로 자료구조를 명백하게 표현
- 클래스 메서드가 접근 정책을 강제
    - 좌표를 읽을 때는 x, y 각각 개별적으로 호출
    - x, y 값을 설정할 때는 한꺼번에 파라미터를 전달
    

```java
// 구체적으로 연료 상태를 표현
public interface Vehicle {
	double getFuelTankCapacityInGallons();
	double getGallonsOfGasoline();
}

// 자동차 연료 상태를 백분율로 표현 (추상적)
public interface Vehicle {
	double getPercentFuelRemaining();
}
```

- 자료를 세세하게 표현, 공개하기보다는 추상적인 개념으로 표현하는 편이 효과적 (객체 지향)
- 공통의 속성이나 기능을 묶어 이름을 붙여 관리

---

### 3. 절차적인 클래스

```java
public class Square {
	public Point topLeft;
	public double side;
}

public class Rectangle {
	public Point topLeft;
	public double height;
	public double width;
}

public class Circle {
	public Point center;
	public double radius;
}

public class Geometry {
	public final double PI = 3.141592;
	
	public double area(Object shape) throws NoSuchShapeException {
		if(shape instanceof Square) {
			Square s = (Square) shape;
			return s.side * s.side;
			
		} else if(shape instanceof Rectangle) {
			Rectangle r = (Rectangle)shape;
			return r.height * r.width;
			
		} else if(shape instanceof Circle) {
			Circle c = (Circle)shape;
			return PI * c.radius * c.radius;
		}
	}
}
```

- Geometry 클래스에 둘레 길이를 구하는 perimeter() 함수를 추가 시
    - 도형 클래스 수정 없음
- 새 도형을 추가 시
    - Geometry 클래스에 속한 함수를 모두 수정
- 기본 자료구조를 변경하지 않으면서 새 함수를 추가 용이
- 새로운 자료 구조를 추가하기 어렵다. (모든 클래스 수정 필요)

### 4. 객체 지향적인 클래스

```java
public class Square implements Shape {
	public Point topLeft;
	public double side;
	
	public double area() {
		return side * side;
	}
}

public class Rectangle implements Shape {
	public Point topLeft;
	public double height;
	public double width;

	public double area() {
		return height * width;
	}
}

public class Circle implements Shape {
	public Point center;
	public double radius;
	public final double PI = 3.141592;
	
	public double area() {
		return PI * radius * radius;
	}
}
```

- 기존 함수를 변경하지 않으면서 새 클래스를 추가 용이
- 새로운 함수를 추가하기 어렵다. (모든 클래스 수정 필요)

> 정리
> 

객체지향 코드에서 어려운 변경은 절차적인 코드에서 쉽다. 반대로 절차적인 코드에서 어려운 변경은 객체지향 코드에서는 쉽다고 할 수 있다.

단순한 자료 구조와 절차적인 코드가 적합한 상황이 있을 수 있다. 무조건적인 OOP 관점은 피하는 게 좋다.

---

### 디미터 법칙

- 모듈은 자신이 조작하는 객체의 속사정을 몰라야 한다는 법칙
- 클래스 C의 메서드 f는 다음과 같은 객체의 메서드만 호출해야 한다.
    - 클래스 C
    - f가 생성한 객체
    - f가 인수로 넘어온 객체
    - C 인스턴스 변수에 저장된 객체
- A가 B를 사용하고 B가 C를 사요알 때 A가 C를 알 필욘 없다.

```java
final String outputDir = ctxt.getOptions().getScratchDir().getAbsolutePath();

// 나누어 호출
Options opts = ctxt.getOptions();
File scratchDir = opts.getScratchDir();
final String outputDir = scratchDir.getAbsolutePath();
```

### 자료 전달 객체

- 자료 구조체의 전형적인 형태는 공개 변수만 있고 함수가 없는 클래스
- 데이터베이스와 통신하거나 소켓에서 받은 메시지의 구문을 분석할 때 유용
- 가공되지 않은 정보를 애플리케이션 코드에서 사용할 객체로 변환하는 일련의 단계에서 처음 사용되는 구조체

```java
public class Address {
	private String street;
	private String city;
	private String state;
	
	public Address(String street, String city, String state) {
		this.street = street;
		this.city = city;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
}
```

### 활성 레코드

- DTO의 특수한 형태
- 공개 변수가 있거나 비공개 변수에 조회/설정 함수가 있는 자료구조
- save나 find와 같은 탐색 함수도 제공
- 활성 레코드에 비즈니스 규칙 메서드를 추가해 객체로 취급하는 경우는 잡종 구조가 나온다.
- 비즈니스 규칙을 담는 객체는 따로 생성하는 것이 좋다.

```java
class Person {
	private String name;
	private String email;

	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	...

	public void sendEmail(){
		...
	}	
}
```

```java
class EmailSender{
	private Person receiver;
	...
	public void sendEmail() {
		...
	}
	...
}
```

> 참고
> 

[[Clean Code / 클린코드] - 6장 객체와 자료 구조](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=zzang9ha&logNo=222068362876)

**[클린코드(Clean Code) 6장. 객체와 자료구조](https://bang-jh.tistory.com/12)**

[클린코드 6장 - 객체와 자료 구조](http://amazingguni.github.io/blog/2016/05/Clean-Code-6-%EA%B0%9D%EC%B2%B4%EC%99%80-%EC%9E%90%EB%A3%8C-%EA%B5%AC%EC%A1%B0)
