
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * StringFactoryTest.java
 * 문자열 풀 테스트 프로그램
 */
public class StringFactoryTest {

	public static void main(String[] args) {
		String s1 = StringFactory.getInstance("apple");
		String s2 = StringFactory.getInstance("apple");
		String s3 = StringFactory.getInstance("paple");
		String s4 = StringFactory.getInstance("banana");
		String s5 = StringFactory.getInstance("banana");
		
		System.out.println(s1==s2);
		System.out.println(s3==s2);
		System.out.println(s4==s5);
	}

}
