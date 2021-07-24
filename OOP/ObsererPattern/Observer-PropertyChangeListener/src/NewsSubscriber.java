import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * @file FancyNewsSubscriber.java
 * 관찰자
 */
public class NewsSubscriber implements PropertyChangeListener {
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("속보: "+(String)evt.getNewValue());
	}
}
