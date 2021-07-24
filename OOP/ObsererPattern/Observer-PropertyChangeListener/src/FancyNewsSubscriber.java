import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * @file FancyNewsSubscriber.java
 * 관찰자
 */
public class FancyNewsSubscriber implements PropertyChangeListener {
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("*********************");
		System.out.println((String)evt.getNewValue());
		System.out.println("*********************");
	}
}
