/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * FlyWithRocket.java: 나는 전략 중 로켓을 이용하여 나는 전략
 * @author 김상진
 */
public class FlyWithRocket implements FlyingStrategy {
	@Override
	public void doFly() {
		System.out.println("난 로켓으로 날고 있어!!!");
	}
}
