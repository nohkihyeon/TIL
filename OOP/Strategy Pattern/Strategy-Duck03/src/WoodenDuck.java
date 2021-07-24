/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * WoodenDuck.java: 나무오리, 부모: Duck
 * 전략 패턴 적용하기 전 버전
 * @author 김상진
 */
public class WoodenDuck extends Duck {
	public WoodenDuck(FlyingStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	@Override
	public void display() {
		System.out.println("난 나무오리");
	}
}
