
/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * TimeFactoryTest.java
 * timePool 테스트 프로그램
 */
public class TimeFactoryTest {

	public static void main(String[] args) {
		Time t1 = Time.of(2,10,5);
		Time t2 = Time.of(10,2,5); 
		Time t3 = Time.of(5);
		Time t4 = Time.of(5);
		Time t5 = Time.of(5,2,10);
		Time t6 = Time.of(5,2,10);
//		Time tzn = Time.getInstance(Time.of(5,2,10));
//		Time t2n = Time.getInstance(t2); 
//		Time t3n = Time.getInstance(t3);
//		Time t4n = Time.getInstance(t4);
		
		
		System.out.println("같은 Time.of(5,2,4) 비교");
		System.out.println(t5==t6);
		System.out.println(t4==t5);
		System.out.println(t4==t6);
		System.out.println(t4==t3);
		System.out.println("\n");
		
		System.out.println("다른 시간, 분, 초 비교");
		System.out.println(t1==t2);
		System.out.println(t3==t2);
		System.out.println(t1==t3);
	}

}
