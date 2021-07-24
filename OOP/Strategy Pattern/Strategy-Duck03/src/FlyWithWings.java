/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * FlyWithWings.java: 나는 전략 중 날개를 이용하여 나는 전략
 * @author 김상진
 */
public class FlyWithWings implements FlyingStrategy {
	@Override
	public void doFly() {
		System.out.println("나는 날개로 하늘을 날고 있어!!!");
	}
}
