import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * StringFactory.java
 * 문자열 풀 흉내
 */
public abstract class StringFactory {
	private static final Map<Integer,String> stringPool = new HashMap<>();
	public static String getInstance(String s) {
		Integer key = Integer.valueOf(s.hashCode());
		if(!stringPool.containsKey(key)) {
			stringPool.put(key, new String(s));
		}
		return stringPool.get(key);
	}
}
