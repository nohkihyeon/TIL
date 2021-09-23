/*
 * @file: NoCommand.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴: null 객체, dummy 객체
 */

#ifndef NOCOMMAND_H_
#define NOCOMMAND_H_
#include <iostream>
#include <string>
#include "Command.h"

class NoCommand final: public Command{
	NoCommand(){}
public:
	static NoCommand& getInstance(){
		static NoCommand instance;
		return instance;
	}
	NoCommand(const NoCommand& other) = delete;
	NoCommand operator=(const NoCommand& other) = delete;

	virtual ~NoCommand() = default;
	void execute() override {}
	void undo() override {}
};

#endif /* NOCOMMAND_H_ */
