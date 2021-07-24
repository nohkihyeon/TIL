/**
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * @file NewsTest.java
 * 테스트 프로그램
 */
public class NewsTest {
	public static void main(String[] args) {
		NewsAgency publisher = new NewsAgency();
		NewsSubscriber observer1 = new NewsSubscriber();
		FancyNewsSubscriber observer2 = new FancyNewsSubscriber();
		 
		publisher.addPropertyChangeListener(observer1);
		publisher.addPropertyChangeListener(observer2);
		
		publisher.setNews("리버풀 0: 뉴캐슬 1");
		publisher.setNews("리버풀 1: 뉴캐슬 1");
		publisher.setNews("리버풀 1: 뉴캐슬 1"); // 통보되지 않음
		publisher.setNews("리버풀 2: 뉴캐슬 1");
		publisher.setNews("리버풀 3: 뉴캐슬 1");
		
	}
}
