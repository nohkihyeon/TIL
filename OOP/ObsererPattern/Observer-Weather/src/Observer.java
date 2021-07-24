/**
 * 관찰자 패턴: Head First Pattern 예제
 * @file Observer.java: 관찰자 interface
 * push 방법 (데이터 전달)
 */
public interface Observer {
	void update(float temperature, float humidity, float pressure);
}
