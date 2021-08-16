/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * @file SingletonV1.java
 * 싱글톤 패턴
 * 가장 단순한 버전. lazy instantiation
 * 다중쓰레드 환경에서 문제점이 발생할 수 있음
 */
public class SingletonV7 {
	private int count = 0;
	private static SingletonV7 unique = null;
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV7() {}
	public static synchronized SingletonV7 getInstance() {
		if(unique==null) unique = new SingletonV7();
		return unique;
	}
	public void increase() {
		++count;
	}
	public int getCount() {
		return count;
	}
}

