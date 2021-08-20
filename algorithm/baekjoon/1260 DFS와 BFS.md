# 1260번 [DFS와 BFS](https://www.acmicpc.net/problem/1260)

## 문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.
## 입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.
```
4 5 1
1 2
1 3
1 4
2 4
3 4
```
## 출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
```
1 2 4 3
1 2 3 4
```
## 설명
## 소스코드
```java
import java.io.*;
import java.util.*;

public class Main{
	private static int N,V,M;
	private static boolean[] visit;
	private static int[][] graph;
	
	public static void dfs(int nV) {
		visit[nV] = true;
		System.out.print(nV + " ");
		
		for(int i=1; i<=N; i++) {
			if(graph[nV][i] == 1 && visit[i] == false) {
				dfs(i);
			}
		}
	}
	
	public static void bfs(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(s);
		visit[s] = true;
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			System.out.print(temp + " ");
			
			for (int i = 1; i <=N; i++) {
				if(graph[temp][i]==1 && visit[i]==false) {
					q.offer(i);
					visit[i] = true;
				}
			}
		}
	}
	
	public static void main(String[] argc) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		V = Integer.parseInt(line[2]);
		
		visit = new boolean[N+1];
		graph = new int[N+1][N+1];
		for (int i=0; i<M; i++) {
			line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			
			graph[x][y] = 1;
			graph[y][x] = 1;
		}
		dfs(V);
		System.out.println();
		Arrays.fill(visit, false);
		bfs(V);
	}
}

```
