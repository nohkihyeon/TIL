# Observer Pattern

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
