/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기 
 * 전략 패턴: Head First Pattern 예제
 * Quack.java: 삑삑 우는 전략 
 * @author 김상진
 */
public class Squeak implements QuackStrategy {
	@Override
	public void doQuack() {
		System.out.println("삑삑");
	}
}
