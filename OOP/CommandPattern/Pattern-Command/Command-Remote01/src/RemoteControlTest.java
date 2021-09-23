/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file RemoteControlTest.java: 만능 리모컨 테스트 프로그램
 */
public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote = new SimpleRemoteControl();
		RoomLight light = new RoomLight("안방");
		Stero stero = new Stero();
		
		Command lightOn = new RoomLightOnCommand(light);
		Command lightOff = new RoomLightOffCommand(light);
		Command steroOn = new SteroOnWithCDCommand(stero);
		Command steroOff = new SteroOffCommand(stero);
		
		remote.setCommand(0, lightOn, lightOff);
		remote.setCommand(1, steroOn, steroOff);
		
		remote.onButtonWasPressed(0);
		remote.onButtonWasPressed(1);
		remote.offButtonWasPressed(1);
		remote.offButtonWasPressed(0);
	}
}
