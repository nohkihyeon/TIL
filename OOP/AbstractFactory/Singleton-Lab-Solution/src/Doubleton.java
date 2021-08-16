import java.util.concurrent.ThreadLocalRandom;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * Doubleton.java
 * 두 개의 객체만 생성하여 사용함
 * 열거형 버전
 * 문제점. getInstance만을 사용하여 접근하도록 제한할 수 없음
 */
public enum Doubleton {
	FIRST, SECOND;
	private static boolean flag = true;
	public static Doubleton getInstance() {
		flag = !flag;
		return flag? FIRST: SECOND;
		// return (ThreadLocalRandom.current().nextBoolean())? FIRST: SECOND;
	}
}
