	import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 관찰자 패턴: java.beans.PropertyChangeSupport 활용한 관찰자 패턴의 구현
 * @file NewsAgency.java
 * 관찰 대상
 */
public class NewsAgency {
	private String news;

	private PropertyChangeSupport support
		= new PropertyChangeSupport(this);
	public NewsAgency() {}
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl); // equals 주의 
    }
 
    public void setNews(String value) {
        support.firePropertyChange("news", this.news, value);
        this.news = value;
    }
}
