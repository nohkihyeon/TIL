# Algorithm - DP

### 2565 전깃줄

- Top-Down
```java
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static Integer[] dp;
	static int[][] line;

	static int recursive(int N) {
		if(dp[N] == null) {
			dp[N] = 1;
			
			for(int i=N+1; i<dp.length; i++) {
				if(line[N][1] < line[i][1])
					dp[N] = Math.max(dp[N], recursive(i)+1);
			}
		}
		return dp[N];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		dp = new Integer[n];
		line = new int[n][2];
		
		String[] li;
		
		for(int i=0; i<n; i++) {
			li = br.readLine().split(" ");
			line[i][0] = Integer.parseInt(li[0]);
			line[i][1] = Integer.parseInt(li[1]);
		}
		
		Arrays.sort(line, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int max = 0;
		
		for(int i=0; i < n; i++)
			max = Math.max(recursive(i), max);
		
		System.out.println(n - max);
	}
}
```
- Bottom-Up
```java
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
	static Integer[] dp;
	static int[][] line;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int max = 0;
		
		dp = new Integer[n];
		line = new int[n][2];
		
		String[] li;
		
		for(int i=0; i<n; i++) {
			li = br.readLine().split(" ");
			line[i][0] = Integer.parseInt(li[0]);
			line[i][1] = Integer.parseInt(li[1]);
		}
		
		Arrays.sort(line, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for(int i=0; i < n; i++) {
			dp[i] = 1;
			for (int j=0; j<i; j++) {
				if(line[i][1] > line[j][1]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		for(int i=0; i<n; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(n - max);
	}
}
```

## Comparable & Comparator

### Comparable
- 기본 정렬기준을 구현하는데 사용
```java
...

String[] sports = new String[]{"Soccer", "Baseball", "Tennis", "Football", "Basketball", "Ski", "Hockey", "Taekwondo"};
String[] names = new String[]{"이강인", "김진수", "손흥민", "박주영", "박지성", "차범근", "이영표", "차두리", "하정수"}
Arrays.sort(sports);
Arrays.sort(names);

for(String a : sports)
  System.out.print(a + " ");
System.out.println();
for(String n : names)
  System.out.print(n + " ");
...
```
- Arrays.sort()의 작동 결과 <br>
![스크린샷 2021-07-26 오후 9 38 44](https://user-images.githubusercontent.com/65120581/126990001-ffe3a739-b305-41d9-a73c-d2a6c5693c8f.png)
- 위 두 Arrays.sortsms String의 Comparable 구현에 의해 정렬된 것이다.
- SoccerPlayer 클래스 예시
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


	  
public class Main {
	static class SoccerPlayer implements Comparable<SoccerPlayer>{
		  private String name;
		  private String position;
		  private int age;
		  
		  public SoccerPlayer(String name, String position, int age){
		    this.name = name;
		    this.position = position;
		    this. age = age;
		    }
		  public String getName() {
			  return name;
		  }
		  public int getAge() {
			  return age;
		  }
		  
		  @Override
		  public int compareTo(SoccerPlayer player){
		      return name.compareTo(player.getName());
		      }
	}
	
	public static void main(String[] args) {
		ArrayList<SoccerPlayer> playerList = new ArrayList<SoccerPlayer>();
	    
	    SoccerPlayer player1 = new SoccerPlayer("긱스", "공격수", 52);
	    SoccerPlayer player2 = new SoccerPlayer("나니", "미드필더", 22);
	    SoccerPlayer player3 = new SoccerPlayer("다다", "골키퍼", 32);
	    SoccerPlayer player4 = new SoccerPlayer("루이스", "수비수", 25);
	    SoccerPlayer player5 = new SoccerPlayer("호날두", "공격수", 32);
	    SoccerPlayer player6 = new SoccerPlayer("메시", "공격수", 31);
	    
	    playerList.add(player1);
	    playerList.add(player2);
	    playerList.add(player3);
	    playerList.add(player4);
	    playerList.add(player5);
	    playerList.add(player6);
	    
	    Collections.sort(playerList);
	    
	    for(int i=0; i< playerList.size(); i++){
	      System.out.println(playerList.get(i).getName());
	     }
	}
}
```
- 결과화면 <br>
<img width="1010" alt="스크린샷 2021-07-26 오후 9 59 42" src="https://user-images.githubusercontent.com/65120581/126992645-6e45569f-8d36-4139-a54a-cd102d5e086b.png">

- Comparator를 사용하면 정렬기준을 본인이 원하는대로 바꾸는 것이 가능

### comparator
- 기본 정렬기준 외에 다른 기준으로 정렬하고자 할 때 사용
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


	  
public class Main {
	static class SoccerPlayer {
		  private String name;
		  private String position;
		  private int age;
		  
		  public SoccerPlayer(String name, String position, int age){
		    this.name = name;
		    this.position = position;
		    this. age = age;
		    }
		  public String getName() {
			  return name;
		  }
		  public int getAge() {
			  return age;
		  }
		  
	}
	
	public static void main(String[] args) {
		ArrayList<SoccerPlayer> playerList = new ArrayList<SoccerPlayer>();
	    
	    SoccerPlayer player1 = new SoccerPlayer("긱스", "공격수", 52);
	    SoccerPlayer player2 = new SoccerPlayer("호날두", "공격수", 32);
	    SoccerPlayer player3 = new SoccerPlayer("루이스", "수비수", 25);
	    SoccerPlayer player4 = new SoccerPlayer("다다", "골키퍼", 32);
	    SoccerPlayer player5 = new SoccerPlayer("나니", "미드필더", 22);
	    SoccerPlayer player6 = new SoccerPlayer("메시", "공격수", 31);
	    
	    playerList.add(player1);
	    playerList.add(player2);
	    playerList.add(player3);
	    playerList.add(player4);
	    playerList.add(player5);
	    playerList.add(player6);
	    
	    Collections.sort(playerList, new Comparator<SoccerPlayer>() {
	    	@Override
	    	public int compare(SoccerPlayer p1, SoccerPlayer p2) {
	    		if (p1.getAge() > p2.getAge()) {
	    			return 1;
	    		}else if (p1.getAge() < p2.getAge()) {
	    			return -1;
	    		}else
	    			return 0;
	    	}
	    });
	    
	    
	    for(int i=0; i< playerList.size(); i++){
	      System.out.println(playerList.get(i).getName() + " : " + playerList.get(i).getAge());
	     }
	}
}
```
- 결과화면 <br>
<img width="638" alt="스크린샷 2021-07-26 오후 10 07 09" src="https://user-images.githubusercontent.com/65120581/126993681-33d1d304-d04d-4e07-9f73-df0da33b1e79.png">

- Comparable 구현 후 내부 compareTo 메소드를 오버라이드 해야하며, 정의 결과에 따라 정렬값이 바뀐다.
- 오브젝트의 다르 값을 비교를 원한다며 Comparator를 이용


### 출처
> #### [잡동사니 정보공유- 자바 정렬 Java Comparable Comparator 확실히 알고 넘어가기](https://cwondev.tistory.com/15)
