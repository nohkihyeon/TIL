/*
 * @file: RoomLight.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴
 */

#ifndef ROOMLIGHT_H_
#define ROOMLIGHT_H_
#include <iostream>
#include <string>
#include "Command.h"

class RoomLight {
private:
	std::string location;
public:
	RoomLight(const std::string& location): location{location}{}
	virtual ~RoomLight() = default;
	void on(){
		std::cout << location << ": 불이 켜짐\n";
	}
	void off(){
		std::cout << location << ": 불이 짐\n";
	}
};

class RoomLightOnCommand final: public Command{
private:
	RoomLight* light;
public:
	RoomLightOnCommand(RoomLight* light): light{light}{}
	virtual ~RoomLightOnCommand() = default;
	virtual void execute() override {
		light->on();
	}
	virtual void undo() override{
		light->off();
	}
};

class RoomLightOffCommand final: public Command{
private:
	RoomLight* light;
public:
	RoomLightOffCommand(RoomLight* light): light{light}{}
	virtual ~RoomLightOffCommand() = default;
	virtual void execute() override {
		light->off();
	}
	virtual void undo() override{
		light->on();
	}
};

#endif /* ROOMLIGHT_H_ */
