import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * Test.java: 테스트 프로그램 
 */

//일반 버전
class KeyMultitonTask implements Runnable{
	@Override
	public void run() {
		String[] keys = {"apple", "banana", "grape", "melon"};
		String key = keys[ThreadLocalRandom.current().nextInt(keys.length)];
		try {
			System.out.println(key+" key used to create instance");
			KeyMultiton instance = KeyMultiton.getInstance(key);
		}
		catch(IllegalArgumentException e) {
			System.out.println("instance creation error using key: "+key);
		}
	}
}

public class Test {
	// 실습1.
	public static void bankAccountNumberGeneratorTest() {
		BankAccount account01 = new BankAccount();
		BankAccount account02 = new BankAccount();
		System.out.println(account01);
		System.out.println(account02);
	}
	// 실습2.
	public static void doubletonTest() {
		int count = 0;
		for(int i=0; i<100; i++) {
			if(Doubleton.FIRST==Doubleton.getInstance()) ++count;
		}
		System.out.println(count+","+(100-count));
	}
	// 실습3.
	public static void KeyMultitonTest() {
		KeyMultiton instance1 = KeyMultiton.getInstance("apple");
		KeyMultiton instance2 = KeyMultiton.getInstance("banana");
		KeyMultiton instance3 = KeyMultiton.getInstance("grape");
		KeyMultiton instance4 = KeyMultiton.getInstance("banana");
		if(instance2==instance4) System.out.println("올바르게 동작 중");
		try {
			KeyMultiton instance5 = KeyMultiton.getInstance("melon");
		}
		catch(IllegalArgumentException e) {
			System.out.println("올바르게 동작 중");
		}
	}
	public static void KeyMultitonMultiThreadTest() {
		try {
			ArrayList<Thread> threadList = new ArrayList<>();
	 		for(int i=0; i<10; i++) {
				Thread thread = new Thread(new KeyMultitonTask());
				threadList.add(thread);
				thread.start();
			}
	 		for(var thread: threadList)
	 			thread.join();
		}
		catch(Exception e) {
			System.out.println("Exception during KeyMultitonTest");
		}
 		KeyMultiton.print();
	}
	public static void main(String[] args) {
		//bankAccountNumberGeneratorTest();
		//doubletonTest();
		//KeyMultitonTest();
		//KeyMultitonMultiThreadTest();
	}
}
