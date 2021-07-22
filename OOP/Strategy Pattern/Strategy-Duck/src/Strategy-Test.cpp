/*
 * @file: Strategy-Test.cpp
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 테스트 프로그램
 */

#include <iostream>
#include "Duck.h"
#include "Bee.h"

int main() {
	MallardDuck duck{new FlyWithWings()};
	duck.display();
	duck.fly();
	duck.setFlyBehavior(new FlyWithRocket());
	duck.fly();

	HoneyBee bee{std::make_unique<FlyWithWings>()};
	bee.display();
	bee.fly();
	bee.setFlyBehavior(std::make_unique<FlyWithRocket>());
	bee.fly();
	return 0;
}
