/**
 * 관찰자 패턴: Head First Pattern 예제
 * @file Subject.java: 관찰대상 interface
 */
public interface Subject {
	void registerObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
