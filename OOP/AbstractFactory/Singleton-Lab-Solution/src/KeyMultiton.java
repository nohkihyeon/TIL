import java.util.HashMap;
import java.util.Map;

/**
 * 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2020년도 2학기
 * @author 2016136035 노기현
 * Doubleton.java
 * 생성된 객체를 접근할 때 사용하는 키가 존재함
 * 총 생성되는 객체 수를 NUMBEROFINSTANCE로 제한함
 * 다중 쓰레드 환경에서는 정해진 객체 수가 제한되기 위한 추가 조치 필요
 */
public class KeyMultiton {
	private static final Map<String, KeyMultiton> registry 
		= new HashMap<>();
	private static final int NUMBEROFINSTANCE = 3;
	private KeyMultiton() {}
	public static KeyMultiton getInstance(String key) {
		if(registry.containsKey(key)) {
			return registry.get(key);
		}
		else {
			//synchronized (KeyMultiton.class) {
				if(registry.size()>=NUMBEROFINSTANCE) 
					throw new IllegalArgumentException();
				KeyMultiton instance = new KeyMultiton();
				registry.put(key, instance);
				return instance;
			//}
		}
	}
	//Debug용
	public static void print() {
		System.out.print("Keys: ");
		for(var key: registry.keySet()) {
			System.out.print(key+", ");
		}
		System.out.println();
	}
}
