/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * SerialNumberGenerator.java: 일련번호 생성기
 * 싱글톤 패턴
 * 내부 클래스 버전
 */ 
public class SerialNumberGenerator {
	private static class Holder{
		private static final SerialNumberGenerator unique =
			new SerialNumberGenerator();
	}
	private int count = 0;
	private SerialNumberGenerator(){}
	public static SerialNumberGenerator getInstance(){
		return Holder.unique;
	}
	public int getNext(){
		++count;
		return count;
	}
	
}
