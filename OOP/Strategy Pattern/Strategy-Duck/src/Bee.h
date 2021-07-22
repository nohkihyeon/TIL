/*
 * @file: Bee.h
 * @copyright: 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version: 2020년도 2학기
 * @author: 김상진
 * 꿀벌 클래스
 * 스마트 포인터 활용한 구현
 */

#ifndef BEE_H_
#define BEE_H_
#include <memory>

class Bee {
private:
	std::unique_ptr<FlyBehavior> flyBehavior;
public:
	Bee(std::unique_ptr<FlyBehavior>&& flyBehavior): flyBehavior{std::move(flyBehavior)}{}
	virtual ~Bee() = default;
	void fly() const noexcept {
		flyBehavior->fly();
	}
	void setFlyBehavior(std::unique_ptr<FlyBehavior>&& flyBehavior){
		this->flyBehavior = std::move(flyBehavior);
	}
	virtual void display() const = 0;
};

class HoneyBee: public Bee{
public:
	HoneyBee(std::unique_ptr<FlyBehavior>&& flyBehavior): Bee(std::move(flyBehavior)){}
	virtual void display() const noexcept {
		std::cout << "난 꿀벌\n";
	}
};

#endif /* BEE_H_ */
