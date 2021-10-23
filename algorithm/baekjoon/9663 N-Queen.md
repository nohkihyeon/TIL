```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
	public static int n;
	public static int count =0;
	static int[] color = new int[16];
	
	public static boolean check(int here) {
		for(int i=0; i< here; i++) {
			if (color[here] == color[i]) return false;
			if(Math.abs(color[here] - color[i]) == (here-i)) return false;
		}
		return true;
	}
	public static void solution(int here) {
		if (here ==n) {
			count++;
			return;
		}
		for (int i=0; i<n; i++) {
			color[here] = i;
			if(check(here)) {
				solution(here+1);
			}
		}
	}
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		solution(0);
		System.out.println(count);
 
	}
}
```
