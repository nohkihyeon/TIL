/**
 * 전략 패턴: Head First Pattern 예제
 * RedHeadDuck.java: 빨간머리오리, 부모: Duck
 * 전략 패턴 적용하기 전 버전
 * @author 김상진
 */
public class RedheadDuck extends Duck {
	@Override
	public void display() {
		System.out.println("난 빨간머리오리");
	}
}
