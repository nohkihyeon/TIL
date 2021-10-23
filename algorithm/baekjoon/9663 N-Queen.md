# 9663번 [N-Queen](https://www.acmicpc.net/problem/9663)

## 문제
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.
## 입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)
```
8
```
## 출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.


```
92
```
## 설명
백트래킹 대표적인 문제
현재위치에서 유망한 자식만 탐색한다.
## 소스코드
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
