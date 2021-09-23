import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * 명령패턴: GUI의 이벤트 처리
 * GUI의 이벤트 처리는 관찰자 패턴에서 노드가 Subject, Event 처리자가 Observer 개념으로 설명한 바 있음
 * GUI의 이벤트 처리는 명령 패턴에서 노드가 invoker가 되고 Event 처리자가 명령 객체로 생각할 수 있음
 * 기존 구현 방식은 execute와 그것에 대응되는 undo가 하나의 객체로 모델링되지 않으며, 실행 로그를 유지하기 힘듦
 * 하지만 자바8 이후 객체 대신에 함수를 처리자로 등록할 수 있기 때문에 다음과 같은 경우를 제외하고는 명령 패턴을 이용할 필요는 없음
 * 1) 여러 노드가 어떤 이벤트가 발생하였을 때 수행해야 하는 기능이 실행시간에 바뀔 수 있는 경우
 * 2) Undo 기능의 제공이 필요한 경우
 * 3) 실행 이력을 유지해야 하는 경우
 * 실제 여러 노드가 특정 이벤트 때 동일한 기능을 수행해야 하면 동일 함수를 등록하면 
 * 되기 때문에 명령 패턴을 이용하는 이점이 별로 없어 보임
 * 하지만 user interface를 처리하는 클래스에서 프로그램 로직을 명령 객체로 옮기는 효과는 있음
 * 
 * 이 예제에서 클라이언트와 receiver가 모호함.   
 * 또 일반적인 명령 패턴과 달리 명령 객체를 invoker가 유지하고 있는 형태가 아님
 * 매번 명령 객체를 생성하고 바로 실행함 
 * 
 */
public class CommandPatternButton extends Application {
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	private static final int IMAGESIZE = 80;
	private Button pikachuDoButton = new Button("피카츄");
	private Button pikachuUndoButton = new Button("피카츄 취소");
	private Button bulbasaurDoButton = new Button("이상해");
	private Button charmanderDoButton = new Button("파이리");
	private Button undoButton = new Button("취소");
	private Pane centerPane = new Pane();
	// 명령 객체를 유지하는 것과 최근에 그린 이미지 노드를 유지하는 것과의 차이는?
	private Stack<ImageView> undoPikachuImages = new Stack<>();
	private Stack<Command> undoCommands = new Stack<>();
	
	private ImageView createInstance(Image image) {
		ImageView iView = new ImageView(image);
		iView.setFitWidth(100d);
		iView.setPreserveRatio(true);
		double width = 100d;
		double height = 100d*image.getHeight()/image.getWidth();
		double x = ThreadLocalRandom.current().nextInt((int)centerPane.getWidth());
		double y = ThreadLocalRandom.current().nextInt((int)centerPane.getHeight());
		if(x+width>=centerPane.getWidth()) x = centerPane.getWidth()-width;
		if(y+height>=centerPane.getHeight()) y = centerPane.getHeight()-height;
		iView.setX(x);
		iView.setY(y);
		return iView;
	}
	
	private void drawImage(Image image) {
		ImageView iView = createInstance(image);
		undoPikachuImages.push(iView);
		centerPane.getChildren().add(iView);
	}
	private void removeImage() {
		if(!undoPikachuImages.isEmpty()) {
			ImageView iView = undoPikachuImages.pop();
			centerPane.getChildren().remove(iView);
		}
	}
	private void setActions() {
		Image pikachu = new Image("pikachu.png");
		Image bulbasaur = new Image("bulbasaur.png");
		Image charmander = new Image("charmander.png");
		
		// 명령 패턴을 이용하지 않고 있음
		pikachuDoButton.setOnAction(e->drawImage(pikachu));
		pikachuUndoButton.setOnAction(e->removeImage());
		
		// 명령 패턴을 이용하고 있음
		bulbasaurDoButton.setOnAction(e->{
			Command command = new ImageDrawCommand(bulbasaur, centerPane);
			undoCommands.push(command);
			command.execute();
		});
		charmanderDoButton.setOnAction(e->{
			Command command = new ImageDrawCommand(charmander, centerPane);
			undoCommands.push(command);
			command.execute();
		});
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
		buttonPane.getChildren().addAll(pikachuDoButton, pikachuUndoButton,
				bulbasaurDoButton, charmanderDoButton, undoButton);
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
