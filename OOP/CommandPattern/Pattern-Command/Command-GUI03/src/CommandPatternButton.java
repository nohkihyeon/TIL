import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 2020년도 2학기 객체지향개발론및실습
 * 명령패턴: GUI의 이벤트 처리
 * 일반적인 내용은 Command-GUI01 참조
 * 차이점
 * 버튼이 명령 객체를 유지하도록 함
 * clone를 이용하지 않는 버전
 * @author 김상진
 * 
 */
public class CommandPatternButton extends Application {
	private CommandHolderButton pikachuDoButton = new CommandHolderButton("피카츄");
	private CommandHolderButton bulbasaurDoButton = new CommandHolderButton("이상해");
	private CommandHolderButton charmanderDoButton = new CommandHolderButton("파이리");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	private Stack<Command> undoCommands = new Stack<>();
	
	// 하나의 처리자에서 모든 노드의 처리할 이벤트를 하나의 코드로 모두 처리할 수 있음
	void doAction(ActionEvent event) {
		CommandHolderButton holderButton = (CommandHolderButton)event.getSource();
		Command command = holderButton.getCommand();
		System.out.println(holderButton.getCommand());
		command.execute();
		undoCommands.push(command);
	}
	
	void setActions() {
		pikachuDoButton.setCommand(
			new ImageDrawCommand(new Image("pikachu.png"), centerPane));
		bulbasaurDoButton.setCommand(
			new ImageDrawCommand(new Image("bulbasaur.png"), centerPane));
		charmanderDoButton.setCommand(
			new ImageDrawCommand(new Image("charmander.png"), centerPane));		
		
		pikachuDoButton.setOnAction(e->doAction(e));
		bulbasaurDoButton.setOnAction(e->doAction(e));
		charmanderDoButton.setOnAction(e->doAction(e));
		undoButton.setOnAction(e->{
			if(!undoCommands.isEmpty()) {
				Command command = undoCommands.pop();
				command.undo();
			}
		});		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		HBox buttonPane = new HBox();
		buttonPane.setPadding(new Insets(10));
		buttonPane.setSpacing(10);
		buttonPane.getChildren().addAll(
			pikachuDoButton, bulbasaurDoButton, charmanderDoButton, undoButton);
		setActions();
		
		centerPane.setBackground(
			new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		centerPane.setMinWidth(500d);
		centerPane.setMinHeight(500d);
		mainPane.setCenter(centerPane);
		mainPane.setBottom(buttonPane);
		primaryStage.setTitle("Command Pattern Example");
		primaryStage.setScene(new Scene(mainPane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
