# Samsung Software Academy For Youth

## 컴퓨팅 사고력 요소 
1. 문제 분석
2. 핵심 요소 추출
3. 문제 분해
4. 모델링
5. 절차 구성
6. 패턴 인식 분석

## 편집거리
두 문자열 A, B가 주어진다. 문자열 A에 할 수 있는 연산은 다음 두 가지가 있다.
1. 문자 하나를 지운다.
2. 문자 하나를 추가한다.
A를 B와 같아지게 하려면위 연산을 최소 몇 번 해야 하는지 구하여라.

예시) back, bag

```
1번) melon, watermelon
2번) apple, application
3번) isiteasy, itiseasy
4번) algorithmjobs, aldentespaghetti
5번) editdistanceproblem, dijkstraalgorithm
```
최대 공통부분 수열 LCS 로 dp 풀이법 중 하나로 접근할 수 있다.

결국 LCS를 찾아서
A -> LCS -> B 로갈때
A -> LCS : 몇 개를 -
LCS -> B : 몇 개를 +
이 두 합을 구하면 답이 된다.

Bottom-up
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main { 
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(", ");
		for(int i=0; i<2; i++)
			System.out.println(line[i]);
		
		char[] str1 = line[0].toCharArray();
		char[] str2 = line[1].toCharArray();
		int[][] dp = new int[str1.length + 1][str2.length + 1];
		
		for(int i=1; i<= str1.length; i++) {
			for(int j=1; j<= str2.length; j++) {
				if(str1[i-1] == str2[j - 1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println(dp[str1.length][str2.length]);
	}
}

```
## <비트 변환>
0, 1로 이루어진 N개의 숫자로 만든 수열이 주어진다. 두가지 중 하나의 행동을 할 수 있다.
1. 두 숫자의 위치를 바꾼다.
2. 하나의 숫자를 0 또는 1로 바꾼다.

우리가 가진 수열과 원하는 수열이 주어질 때, 원하는 수열을 만들기 위해 최소 몇번의 행동을 해야 하는지 구하여라.

예) 
01100. 01010
답 : 1회

3번째와 4번째 숫자를 2번 작업을 통해 각각 뒤집으면 2번의 작업으로 목표 상태를 만들 수 있다. 하지만 1번 작업을 통해 3번째와 4번째 숫자를 골라 서로의위치를 바꾸어주면 1번 만에 목표 상태에 도달 할 수 있다.

문제)
```
1번) 01110. 11110
2번) 01010. 10101
3번) 1111000. 0000111
4번) 10111001101. 10111101000
5번) 1111010101010101010. 0100101011011010101
```

해설)
이미 원하는 숫자가 들어가있는 자리는 건드릴 필요가 없다. 따라서 우리가 건드려야 할 칸은 아래 두가지 경우에 속한다.
1. 현재 숫자가 0이고 목표 숫자가 1인 경우
2. 현재 숫자가 1이고 목표 숫자가 0인 경우

