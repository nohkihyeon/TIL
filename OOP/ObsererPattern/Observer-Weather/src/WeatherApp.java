/**
 * 관찰자 패턴: Head First Pattern 예제
 * @file WeatherApp.java: 날씨 응용 테스트 프로그램
 */
public class WeatherApp {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		weatherData.registerObserver(new CurrentConditionDisplay());
		weatherData.registerObserver(new StatisticDisplay());
		
		weatherData.setMeasurement(30, 65, 30.4f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(28, 55, 29.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(29, 50, 30.8f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(28, 55, 30.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(31, 55, 29.2f);
		weatherData.measurementChanged();
		weatherData.setMeasurement(30, 60, 28.2f);
		weatherData.measurementChanged();
	}

}
