/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * AccountNumberGenerator.java: 일련번호 생성기
 * 싱글톤 패턴
 * 열거형 버전
 */ 
public enum AccountNumberGenerator {
	UNIQUE;
	private int count = 0;
	public int getNext() {
		return ++count;
	}
}
