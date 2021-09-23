/*
 * @file: RemoteControl.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴
 */

#ifndef REMOTECONTROL_H_
#define REMOTECONTROL_H_
#include <stdexcept>
#include "Command.h"
#include "NoCommand.h"

class RemoteControl {
	Command *onCommands[3];
	Command *offCommands[3];
	Command *undoCommand{&NoCommand::getInstance()};
public:
	RemoteControl(){
		for(int i=0; i<3; i++){
			onCommands[i] = &NoCommand::getInstance();
			offCommands[i] = &NoCommand::getInstance();
		}
	}
	virtual ~RemoteControl() = default;
	void setCommand(int slot, Command* onCommand, Command* offCommand){
		if(slot<0||slot>=3) throw std::range_error("없는 slot");
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;

	}
	void onButtonWasPressed(int slot){
		if(slot<0||slot>=3) throw std::range_error("없는 slot");
		onCommands[slot]->execute();
		undoCommand = onCommands[slot];
	}
	void offButtonWasPressed(int slot){
		if(slot<0||slot>=2) throw std::range_error("없는 slot");
		offCommands[slot]->execute();
		undoCommand = offCommands[slot];
	}
	void undoButtonWasPressed(){
		undoCommand->undo();
	}
};

#endif /* REMOTECONTROL_H_ */
