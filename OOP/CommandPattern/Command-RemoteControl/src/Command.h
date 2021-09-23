/*
 * @file: Command.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 명령 패턴
 */

#ifndef COMMAND_H_
#define COMMAND_H_

class Command {
public:
	Command() = default;
	virtual ~Command() = default;
	virtual void execute() = 0;
	virtual void undo() = 0;
};

#endif /* COMMAND_H_ */
