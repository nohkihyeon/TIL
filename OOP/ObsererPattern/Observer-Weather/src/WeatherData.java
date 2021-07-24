import java.util.ArrayList;

/**
 * 관찰자 패턴: Head First Pattern 예제
 * @file WeatherData.java: 관찰대상
 */
public class WeatherData implements Subject {
	private ArrayList<Observer> observers = new ArrayList<>();
	private float temperature;
	private float humidity;
	private float pressure;
	
	// 중복 검사가 필요하면 ArrayList 대신에 Set 사용 가능
	@Override
	public void registerObserver(Observer o) {
		if(o!=null) observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// remove가 동작하기 위한 조건은??? ==> equals 메소드의 재정의가 필요함
		observers.remove(o);  
	}

	@Override
	public void notifyObservers() {
		/*
		for(int i=0; i<observers.size(); i++)
			observers.get(i).update(temperature,humidity,pressure);
		
		for(var observer: observers)
			observer.update(temperature,humidity,pressure);
		*/
		observers.forEach(o->o.update(temperature,humidity,pressure));
	}
	
	public void measurementChanged() {
		notifyObservers();
	}
	
	public void setMeasurement(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		//measurementChanged();
	}

}
