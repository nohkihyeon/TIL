/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습 
 * @version 2020년도 2학기
 * @author 김상진
 * @file SingletonV2.java
 * 싱글톤 패턴
 * eager instantiation
 * 다중쓰레드 환경 문제 없음
 */
public class SingletonV2 {
	// eager instantiation의 경우에는 final 키워드 사용 가능 
	public static final SingletonV2 unique = new SingletonV2();
	// 생성자는 반드시 private이어야 생성을 제한할 수 있음
	private SingletonV2() {}
	// lazy instantiation과 달리 getter를 꼭 제공할 필요가 없음
}