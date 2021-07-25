# Observer Pattern
- 패턴의 분류 : 행위패턴
- 패턴의 목적 : This pattern defines a one-to-many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically. – GoF –
- 패턴의 적용
	 - RSS Feed: 웹 사이트들은 새로운 정보가 발생하면 관심을 가지고 있는 측에게 자동 통보하여 줌
	 - Facebook과 같은 많은 SNS의 경우 사용자가 글을 게시하면 그 게시자의 모든 follower에게 자동 통보하여 줌
	 - GUI 프로그래밍에서 사건 소스에 사건 경청자를 등록하여 사건이 발생하였을 때 처리한다. 보통 하나의 사건 경청자만 등록하지만 원하면 여러 개를 등록할 수도 있다. 이 측면에서 사건 소스는 관찰 대상, 사건 경청자는 관찰자가 되며, 보통 내부적으로 관찰자 패턴으로 구현
- 패턴의 참여자
	- 관찰자 interface: 관찰대상이 상태 변화를 알려주기 위해 호출하는 메소드가 선언되어야 있어야 함. 소비자(consumer) 또는 구독자(publisher)라고도 함
	- 관찰자클래스 : 관찰 대상에 관심을 갖는 객체이며, 관찰 대상은 자신의 상태가 변했을 때 이 객체에게 자동 통보함
	- 관찰대상 interface: 관찰대상이 되기 위해 필요한 메소드들이 선언되어 있음 생산자(producer) 또는 출판자(publisher)라고도 함.
	- 관찰대상 클래스: 자신을 관찰하는 관찰자들을 유지하고 및 관리할 수 있고, 자산의 상태가 변화하면 등록된 관찰자에게 알림
- 패턴의 구조 <br>
![스크린샷 2021-07-25 오후 11 52 01](https://user-images.githubusercontent.com/65120581/126903463-e95e47a7-1c9c-4329-b92d-476fe2f036cd.png)


- 관찰자 패턴 예시 <br>
<img width="534" alt="스크린샷 2021-07-24 오후 7 12 28" src="https://user-images.githubusercontent.com/65120581/126865324-4a4a71d4-744b-4e86-8641-f3357219caaa.png">

```java
class A implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Apple\n";
			textOutArea.setText(textData);		
		}
	}
class B implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Banana\n";
			textOutArea.setText(textData);			
		}
	}
class C implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			textData += "Cherry\n";
			textOutArea.setText(textData);			
		}
```

```java
Button normalButton = new Button("단일 사건처리자");
		Button multiActionButton = new Button("다중 사건처리자");
		Button clearButton = new Button("전체지우기");
		buttonPane.getChildren().addAll(normalButton, multiActionButton, clearButton);
		
		normalButton.setOnAction(new A());
		
		multiActionButton.addEventHandler(ActionEvent.ACTION, new B());
		multiActionButton.addEventHandler(ActionEvent.ACTION, new C());
		multiActionButton.addEventHandler(ActionEvent.ACTION, e->{
			textData += "Kiwi\n";
			textOutArea.setText(textData);	
		});
		
		clearButton.setOnAction(e->{
			textData = "";
			textOutArea.setText("");
		});
```
[전체코드 보기](./Observer-GUI-Event/src/MultipleActionTest.java)
