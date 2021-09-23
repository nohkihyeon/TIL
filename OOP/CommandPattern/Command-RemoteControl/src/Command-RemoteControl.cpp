/*
 * @file: Command-RemoteControl.cpp
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴
 */

#include <iostream>
#include "Command.h"
#include "RoomLight.h"
#include "Stero.h"
#include "RemoteControl.h"

int main() {
	Stero stero;
	RoomLight roomLight{"거실"};
	RemoteControl remoteControl;
	RoomLightOnCommand roomLightOnCommand{&roomLight};
	RoomLightOffCommand roomLightOffCommand{&roomLight};
	SteroOnWithCDCommand steroOnWithCDCommand{&stero};
	SteroOffCommand steroOffCommand{&stero};

	remoteControl.setCommand(0, &roomLightOnCommand, &roomLightOffCommand);
	remoteControl.setCommand(1, &steroOnWithCDCommand, &steroOffCommand);

	remoteControl.onButtonWasPressed(0);
	remoteControl.undoButtonWasPressed();
	remoteControl.onButtonWasPressed(1);
	remoteControl.offButtonWasPressed(1);
	remoteControl.onButtonWasPressed(2);

	return 0;
}
