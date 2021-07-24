/**
 * 관찰자 패턴: Head First Pattern 예제
 * @file CurrentConditionDisplay.java: 구체적 관찰자
 * 최신 정보를 보여주는 응용
 */
public class CurrentConditionDisplay implements Observer {
	private float temperature;
	private float humidity;
	private float pressure;

	public void display() {
		System.out.println("===최신 날씨 정보===");
		System.out.printf("현재온도: %.2f%n", temperature);
		System.out.printf("현재습도: %.2f%n", humidity);
		System.out.printf("현재기압: %.2f%n", pressure);
	}

	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}
}
