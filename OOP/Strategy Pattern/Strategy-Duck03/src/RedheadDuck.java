/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * RedheadDuck.java: 빨간머리오리, 부모: Duck
 * 전략 패턴 적용하기 전 버전
 * @author 김상진
 */
public class RedheadDuck extends Duck {
	public RedheadDuck(FlyingStrategy flyingStrategy, QuackStrategy quackStrategy) {
		super(flyingStrategy, quackStrategy);
	}
	@Override
	public void display() {
		System.out.println("난 빨간머리오리");
	}
}
