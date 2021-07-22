/*
 * @file: Duck.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 오리 클래스
 * 스마트 포인터를 사용하지 않고 구현
 */

#ifndef DUCK_H_
#define DUCK_H_

#include <iostream>
#include "FlyBehavior.h"

class Duck {
private:
	FlyBehavior* flyBehavior{nullptr};
public:
	Duck(FlyBehavior* flyBehavior): flyBehavior{flyBehavior}{}
	virtual ~Duck(){
		if(flyBehavior) delete flyBehavior;
	}
	void fly() const{
		flyBehavior->fly();
	}
	void setFlyBehavior(FlyBehavior* flyBehavior){
		if(flyBehavior) delete flyBehavior;
		this->flyBehavior = flyBehavior;
	}
	virtual void display() const = 0;
};

class MallardDuck : public Duck
{
public:
	MallardDuck(FlyBehavior* flyBehavior): Duck{flyBehavior}{}
	virtual void display() const noexcept override {
		std::cout << "난 청둥오리\n";
	}
};

#endif /* DUCK_H_ */
