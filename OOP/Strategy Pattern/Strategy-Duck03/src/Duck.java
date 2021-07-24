import java.util.Objects;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2019년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * Duck.java: 추상클래스, 오리
 * 전략 패턴 적용하기 전 버전
 * @author 김상진
 */
public abstract class Duck {
	private FlyingStrategy flyingStrategy;
	private QuackStrategy quackStrategy;
	
	public Duck(FlyingStrategy flyingStrategy, QuackStrategy quackStrategy) {
		setFlyingStrategy(flyingStrategy);
		setQuackStrategy(quackStrategy);
	}
	public void setFlyingStrategy(FlyingStrategy flyingStrategy) {
		this.flyingStrategy = Objects.requireNonNull(flyingStrategy);
	}
	public void setQuackStrategy(QuackStrategy quackStrategy) {
		this.quackStrategy = Objects.requireNonNull(quackStrategy);
	}
	public void quack() {
		quackStrategy.doQuack();
	}
	public void swim() {
		System.out.println("나 물에서 수영하고 있어");
	}
	public void fly() {
		flyingStrategy.doFly();
	}
	public abstract void display();
}
