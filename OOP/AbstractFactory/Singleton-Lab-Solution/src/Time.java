import java.util.Map;
import java.util.Objects;
import java.util.HashMap;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * Time.java: TimePool 실습 
 * TimeFactoryTest 파일에서 실행결과 확인
 */

public class Time {
	private final int hour;
	private final int minute;
	private final int second;
	
	private static final Map<Integer, Time> timePool = new HashMap<>();
	
	private Time(int hour) { this(hour, 0, 0);}
	private Time(int hour, int minute) { this(hour, minute, 0);}
	private Time(int hour, int minute, int second) { 
		this.hour = (hour >= 0 && hour <24) ? hour : 0;
		this.minute = (minute >= 0 && minute <60) ? minute : 0;
		this.second = (second >= 0 && second <60) ? second : 0;
	}
	public int getHour() { return hour; }
	public int getMinute() { return minute; }
	public int getSecond() { return second; }
	@Override public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	public static Time of(int hour) { return of(hour, 0, 0); }
	public static Time of(int hour, int minute) { 
		return of(hour, minute, 0); 
		}
	
	// 3개의 int를 모두 비교하기 위해서 재정의
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Time)) {
			return false;
		}
		Time that = (Time) obj;
		return (this.hour == that.hour)
				&&(this.minute == that.minute)
				&&(this.second == that.second);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.hour, this.minute, this.second);
	}
	// 객체 풀에 존재하는 hashcode가 있으면 새로 생성하지 않는다.
	public static Time of(int hour, int minute, int second) { 
		Integer key = hour*3600 + 60*minute + second;
		if (!timePool.containsKey(key)) {
			timePool.put(key, new Time(hour, minute, second));
		}
		return timePool.get(key); 
	}
		
}
