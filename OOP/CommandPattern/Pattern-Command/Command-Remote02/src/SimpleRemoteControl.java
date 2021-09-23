/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * @author 김상진
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * @file SimpleRemoteControl.java: 단순 만능 리모컨
 * 버튼 수 9개: On 4개, Off 4개, undo 1개 
 */
public class SimpleRemoteControl {
	private Command[] onCommands = new Command[4];
	private Command[] offCommands = new Command[4];
	private Command undoCommand;
	
	public SimpleRemoteControl(){}
	public void setCommand(int slot, Command onCommand, Command offCommand){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}
	public void onButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		if(onCommands[slot]!=null) onCommands[slot].execute();
	}
	public void offButtonWasPressed(int slot){
		if(slot<0||slot>=onCommands.length) 
			throw new IndexOutOfBoundsException();
		if(onCommands[slot]!=null) offCommands[slot].execute();
	}
	// 마지막으로 실행한 명령을 undo 
	public void undoButtonPressed() {
		if(undoCommand!=null) undoCommand.undo();
	}
}
