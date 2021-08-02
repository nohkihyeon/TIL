# 2261번 : 가자 가까운 두 점


- for문 2가지를 이용할 경우 (brute force) 
```java
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

	  
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String[] line;
		int diff0, diff1;
		int min = Integer.MAX_VALUE;
		
		int[][] num = new int[n][2];
		
		for(int i=0; i<n; i++) {
			line = br.readLine().split(" ");
			num[i][0] = Integer.parseInt(line[0]);
			num[i][1] = Integer.parseInt(line[1]);
		}
		for(int i=0; i<n; i++) {
			for (int j=i; j<n-1; j++) {
				diff0 = num[j][0] - num[j+1][0];
				diff1 = num[j][1] - num[j+1][1];
				min = (int) Math.min(Math.pow(diff0, 2) + Math.pow(diff1, 2) 
						, min);
			}
		}
		System.out.println(min);
	}
}
```
![스크린샷 2021-08-02 오후 11 31 22](https://user-images.githubusercontent.com/65120581/127877933-2e7be41e-5e75-47b3-9d57-19ff7b17f4ef.png)
- 시간 복잡도가 log(n^2) 초과하게된다.
