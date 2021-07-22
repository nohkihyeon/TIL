/*
 * @file: FlyBehavior.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 나는 전략
 */

#ifndef FLYBEHAVIOR_H_
#define FLYBEHAVIOR_H_
#include <iostream>

class FlyBehavior {
public:
	FlyBehavior() = default;
	virtual ~FlyBehavior() = default;
	virtual void fly() const = 0;
};

class FlyWithWings: public FlyBehavior{
public:
	void fly() const override {
		std::cout << "Fly with wings!\n";
	}
};

class FlyNoWay: public FlyBehavior{
public:
	void fly() const override {
		std::cout << "I can't fly!\n";
	}
};

class FlyWithRocket: public FlyBehavior{
public:
	void fly() const override {
		std::cout << "Fly with rocket!\n";
	}
};


#endif /* FLYBEHAVIOR_H_ */
