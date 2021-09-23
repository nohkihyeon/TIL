import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file RemoteView.java: 만능 리모컨 테스트를 위한 GUI
 * 버튼 9개, On 4개, Off 4개, undo 1개
 * 
 * 1) undo 기능이 동작하도록 SimpleRemoteControl 수정
 * 2) SteroOffCommand의 undo 메소드를 완성
 * 3) SteroOnWithUSBCommand 정의하고 리모콘의 3번째 slot에 등록
 * 4) NoCommand의 용도/사용법
 * 5) CeilingFanHighCommand, CeilingFanOffCommand를 정의하고 리모콘 4번째 slot에 등록
 * 6) On/OffCommand를 동시에 만들 필요가 있는지 생각해 보기
 */
public class RemoteView extends Application {
	private Button[] onButtons = new Button[4];
	private Button[] offButtons = new Button[4];
	private Button undoButton = new Button("undo");
	private SimpleRemoteControl remote = new SimpleRemoteControl();
	
	private void setRemoteControl() {	
		RoomLight light = new RoomLight("거실");
		Stero stero = new Stero();
		CeilingFan fan = new CeilingFan();
		
		Command lightOn = new RoomLightOnCommand(light);
		Command lightOff = new RoomLightOffCommand(light);	
		Command cdOn = new SteroOnWithCDCommand(stero);
		Command steroOff = new SteroOffCommand(stero);
		
		remote.setCommand(0, lightOn, lightOff);
		remote.setCommand(1, cdOn, steroOff);
		
		onButtons[0].setOnAction(e->remote.onButtonWasPressed(0));
		onButtons[1].setOnAction(e->remote.onButtonWasPressed(1));
		onButtons[2].setOnAction(e->remote.onButtonWasPressed(2));
		onButtons[3].setOnAction(e->remote.onButtonWasPressed(3));
		offButtons[0].setOnAction(e->remote.offButtonWasPressed(0));
		offButtons[1].setOnAction(e->remote.offButtonWasPressed(1));
		offButtons[2].setOnAction(e->remote.offButtonWasPressed(2));
		offButtons[3].setOnAction(e->remote.offButtonWasPressed(3));
		undoButton.setOnAction(e->remote.undoButtonPressed());
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane mainPane = new GridPane();
		for(int i=0; i<onButtons.length; i++) {
			onButtons[i] = new Button("on");
			onButtons[i].setMinWidth(60);
			onButtons[i].setMinHeight(60);
		}
		for(int i=0; i<offButtons.length; i++) {
			offButtons[i] = new Button("off");
			offButtons[i].setMinWidth(60);
			offButtons[i].setMinHeight(60);
		}
		for(int r=0; r<onButtons.length; r++) mainPane.add(onButtons[r], 0, r);
		for(int r=0; r<offButtons.length; r++) mainPane.add(offButtons[r], 1, r);
		undoButton.setMinSize(120, 40);
		mainPane.add(undoButton,0,4,2,1);
		setRemoteControl();
		
		primaryStage.setTitle("만능 리모콘");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
