/*
 * @file: Stero.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴
 */

#ifndef STERO_H_
#define STERO_H_
#include <iostream>
#include "Command.h"

class Stero {
public:
	enum class InputType {CD, RADIO, USB};
private:
	bool isOn{false};
	InputType currentInput{InputType::CD};
	int volume{0};
public:
	Stero() = default;
	virtual ~Stero() = default;
	void on(){
		isOn = true;
		std::cout << "스테레오 전원 켜짐\n";
	}
	void off(){
		isOn = false;
		std::cout << "스테레오 전원 짐\n";
	}
	bool getCurrentState(){
		return isOn;
	}
	InputType getCurrentInput() {
		return currentInput;
	}
	int getVolume() {
		return volume;
	}
	void changeInput(InputType inputType){
		switch(inputType){
		case InputType::CD: setCD(); break;
		case InputType::RADIO: setRadio(); break;
		default: setUSB(); break;
		}
	}
	void setCD(){
		currentInput = InputType::CD;
		std::cout << "스테레오 입력 CD로 바꿈\n";
	}
	void setRadio(){
		currentInput = InputType::RADIO;
		std::cout << "스테레오 입력 CD로 바꿈\n";
	}
	void setUSB(){
		currentInput = InputType::CD;
		std::cout << "스테레오 입력 CD로 바꿈\n";
	}
	void setVolume(int volume){
		this->volume = volume;
		std::cout << "볼륨 조정: " << volume << "\n";
	}
};

class SteroOnWithCDCommand final: public Command {
private:
	Stero* stero;
	bool isOn{false};
	Stero::InputType inputType{Stero::InputType::CD};
	int volume{0};
public:
	SteroOnWithCDCommand(Stero* stero): stero{stero}{}
	~SteroOnWithCDCommand() = default;
	virtual void execute() override {
		isOn = stero->getCurrentState();
		inputType = stero->getCurrentInput();
		volume = stero->getVolume();
		stero->on();
		stero->setCD();
		stero->setVolume(11);
	}
	virtual void undo() override{
		if(isOn){
			stero->on();
			stero->changeInput(inputType);
			stero->setVolume(volume);
		}
		else stero->off();
	}
};

class SteroOffCommand final: public Command {
private:
	Stero* stero;
	bool isOn{false};
	Stero::InputType inputType{Stero::InputType::CD};
	int volume{0};
public:
	SteroOffCommand(Stero* stero): stero{stero}{}
	~SteroOffCommand() = default;
	virtual void execute() override {
		isOn = stero->getCurrentState();
		inputType = stero->getCurrentInput();
		volume = stero->getVolume();
		stero->off();
	}
	virtual void undo() override{
		if(isOn){
			stero->on();
			stero->changeInput(inputType);
			stero->setVolume(volume);
		}
	}
};

#endif /* STERO_H_ */
