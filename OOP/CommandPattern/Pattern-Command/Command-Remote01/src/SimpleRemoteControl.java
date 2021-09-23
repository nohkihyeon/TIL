/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file SimpleRemoteControl.java: 단순 만능 리모컨
 * 버튼 수 4개: On 2개, Off 2개 
 */
public class SimpleRemoteControl {
	private Command[] onCommands = new Command[2];
	private Command[] offCommands = new Command[2];
	public SimpleRemoteControl(){}
	public void setCommand(int slot, Command onCommand, Command offCommand){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
		
	}
	public void onButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		onCommands[slot].execute();
	}
	public void offButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException("없는 slot");
		offCommands[slot].execute();
	}
}
