# Samsung Software Academy For Youth

## 컴퓨팅 사고력 요소 
1. 문제 분석
2. 핵심 요소 추출
3. 문제 분해
4. 모델링
5. 절차 구성
6. 패턴 인식 분석

## 1번 편집거리
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

<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

최대 공통부분 수열 LCS 로 dp 풀이법 중 하나로 접근할 수 있다.

결국 LCS를 찾아서
1. A -> LCS -> B 로갈때
2. A -> LCS : 몇 개를 -
3. LCS -> B : 몇 개를 +
4. 이 두 합을 구하면 답이 된다.

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

				  
</div>
</details>

## 2번 비트 변환
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
5번) 1111010101010101010101. 0100101011011010101		
```


<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">
	
해설)
이미 원하는 숫자가 들어가있는 자리는 건드릴 필요가 없다. 따라서 우리가 건드려야 할 칸은 아래 두가지 경우에 속한다.
1. 현재 숫자가 0이고 목표 숫자가 1인 경우
2. 현재 숫자가 1이고 목표 숫자가 0인 경우
	
```java
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());  //테스트 케이스 개수
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < t; i++) {
			String[] line = br.readLine().split(" ");
			
			String n = line[0];  //이진수 1
			String m = line[1];  //이진수 2
			
			int one = 0;
			int zero = 0;
			
			for (int j = 0; j < m.length(); j++) {
				if (n.charAt(j) != m.charAt(j)) {
					if(m.charAt(j) == '1') {
						one ++;
					}
					else {
						zero ++;
					}
				}
			}
			sb.append(Math.max(one, zero) + "\n");
		}
		System.out.println(sb);
	}

}
```
#### 입력
```
5
01110 11110
01010 10101
1111000 0000111
10111001101 10111101000
1111010101010101010101 0100101011011010101
```
#### 출력	
```
1
3
4
2
8
```
				  
</div>
</details>

## 3번 이상한 음식점
이곳은 맛집이 아니라서 어떤 음식은 너무 짜고 어던 음식은 너무 싱겁다. 짠 음식과 싱거운 음식을 번갈아가며 먹으면 맛이 없기 때문에 우리는 코스 음식 중 몇개만 골라서 싱거운 음식으로 싲가해서 점점 싸게 먹다가 어느 순간부터 점점 싱겁게 먹으려고 한다. 각 음식의 염도가 순서대로 주어질 때, 위와 같이 먹으면 최대 몇 개를 먹을 수 있는지 구하여라.
단, 음식이 식기 전에 먹어야 하므로 음식의 순서를 임의로 바꿔서 먹을 수 없으며, 증가하는 부분이나 감소하는 부분이 없어도 된다.


예) 1 9 8 3 6 3 9 5 1 4 2
음식의 염도가 위와 같다면 증가하다가 감소하게 먹으려면 1 9 8 6 5 4 2로 먹는 것이 최선이다. => 7



### LIS 최장 증가 수열 

|| 1| 9| 8| 3| 6| 3| 9| 5| 1| 4| 2|
|:---:|---|---|---|---|---|---|---|---|---|---|---|
|LIS<br>정방향|1 |2 |2 |2 |3 |2 |4 |3 |1 |3 |2 |
|LIS<br>역방향|1 |6 |5 |2 |4 |2 |4 |3 |1 |2 |1 |
|합|3 |8 |7 |4 |7 |4 |8 |6 |2 |5 |3 |

최대값의 합에서 1을 뺀 것이 답이 된다.


```
1번) 98 2 37 5 12
2번) 23 32 12 98 3 2 1 9 6 2 12 32 12 3 2 8 45 2 3 21
3번) 32 12 98 3 86 42 23 12 2 1 9 6 2 12 32 12 3 2 8 45 2 3 21 37 92 53 68 49 13 87
```
<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

#### 1번
|| 98| 2| 37| 5| 12| 
|:---:|---|---|---|---|---|
|LIS<br>정방향|1 |1|2 |2 |3 |
|LIS<br>역방향|3|1|2|1|1|
|합|4|2|4|3|4 |

	=> 3
	
#### 2번
|| 23| 32| 12| 98| 3|  2| 1| 9| 6|  2| 12| 32| 12|  3| 2| 8|45| 2| 3|21| 
|:---:|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|LIS<br>정방향| 1| 2| 1| 3| 1|  1| 1| 2| 2|  2| 3| 4| 4|  3| 3| 4|5| 4| 5|6|
|LIS<br>역방향| 7|7| 6| 6| 5| 4|  1| 5| 4| 3|  5| 5| 4| 3|  2| 2| 2|1| 1| 1|
|합| 8| 9| 7| 9| 6|  5| 2| 7| 6|  5| 8| 9| 8|  6| 5| 6|7| 5|6|7|
	
	=> 8

#### 3번
	

#### 3번
	
|| 32| 12| 98|  3| 86| 42| 23|  12 | 2|1|9|6 | 2|12|32|12|3|2|8|45|2| 3| 21| 37| 92| 53|68| 49| 13|87|
|:---:|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|LIS<br>정방향| 1| 1| 2| 1| 2| 2  | 2| 1|  1| 1 | 2 | 2 | 3 | 4 | 4 |  3 | 3 |  4 | 5  | 4 | 5 | 6 |  7 |  8| 8  | 9  | 10  | 8  |6   |10|
|LIS<br>역방향| 8|  7|  10|  5|  9|  8|  7| 6|  4| 1| 5|  4| 3|  5|  5|  4|  3| 2|2 |  3|  1| 1| 2|  2|  4| 3|   3|   2|  1 |1|
|합| 9| 8| 12| 6| 11| 10| 9| 8| 5| 2| 7| 6| 5| 8| 9| 8| 6| 5| 6|8|  5 |  6| 8| 9| 12| 11|12 | 10| 7 |12|
	
	=> 11
	
```java
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int lis[][] = new int[N+2][2];
		int arr[] = new int[N+2];
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		lis[N+1][1] = 0;
		arr[N+1] = 0;
		for(int i=1;i<=N;i++) {
			for(int j=0;j<i;j++) {
				if(arr[i] >= arr[j])
					lis[i][0] = Math.max(lis[j][0]+1,lis[i][0]);
			}
		}
		
		for(int i=N;i>=1;i--) {
			for(int j=N+1;j>i;j--) {
				if(arr[i] >= arr[j]) {
					lis[i][1] = Math.max(lis[j][1]+1, lis[i][1]);
				}
			}
		}
		int ans = 0;
		for(int i=1;i<=N;i++) {
			ans = Math.max(ans, lis[i][0] + lis[i][1]);
		}
		System.out.println(ans-1);
		
		for(int i=1;i<=N;i++) {
			System.out.print(lis[i][0] + " ");
		}
		System.out.println();
		for(int i=1;i<=N;i++) {
			System.out.print(lis[i][1] + " ");
		}
		System.out.println();
		for(int i=1;i<=N;i++) {
			System.out.print(lis[i][1]+ lis[i][0] + " ");
		}
	}

}	
```
	
</div>
</details>



## 4번 삼 거듭제곱 계산기

숫자 n을 3의 거듭제곱 숫자들을 중복 없이 더해서 만들 수 있는지 구하여라.
(1도 3^0으로 3의 거듭제곱으로 본다.)

예시)
109는 3<sup>0</sup> + 3<sup>3</sup>+ 3<sup>4</sup> 나타낼 수 있다.
7은 3<sup>0</sup> + 3<sup>0</sup> + 3<sup>1</sup>으로 나타낼 수 없다.


문제)
1. 36
2. 120
3. 278
4. 19424
5. 10492831

<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

```python
def solution(n, q):
    rev_base = ''

    while n > 0:
        n, mod = divmod(n, q)
        rev_base += str(mod)

    return rev_base[::-1] 
    # 역순인 진수를 뒤집어 줘야 원래 변환 하고자하는 base가 출력


print(solution(36, 3))			// 1100	
print(solution(120, 3))			// 11110 		
print(solution(278, 3))			// 101022
print(solution(19424, 3))		// 222122102
print(solution(10492831, 3))		// 201202002110101
```

</div>
</details>



## 5번 이동하기
유니는 지금 2*m 행렬의 (1,1) 위치에 있다.
행렬의 각 칸에는 그 칸을 지날 때의 점수가 적혀 있다.
유니는 (1,1)위치에서 오른쪽, 아래 방향으로만 이동해 (2, m) 위치로 이동하려고 한다. 유니가 얻을 수 있는 최대 점수를 구하여라.

예시)
```
1  2 3
10 5 6

```
문제)
```
1)
1  2  3  4  5
2  2  2  2  2

2)
1  4  6  8  1
20 1  1  1  1

3)
3  6  2  7  8
4  6  2  1  9

4)
11 6  8  9  10 2 20
8  3  2  9  20 6 7

5)
20  31  14  25   6  17   2 9
25  19  30   5  18   3  11 6
```


<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

```
17 25 35 77 143
```

</div>
</details>


## 6번 낚시
n명의 사람이 일렬로 서서 낚시를 하고 있는데 낚싯줄이 서로 엉켜버렸다.
엉킨 낚싯줄 몇개를 잘라서 멀쩡한 낚싯줄만 남기려고한다. <br>
사람의 번호를 1~n이라고 하고, 왼쪽부터 1~n번째에 있다고 하자. <br>
1번 사람부터 n번 사람까지의 찌가 몇 번째에 있는지 주어질 때, 최소 몇개의 줄을 잘라야 하는지 구하여라.
	
예시) 3 1 2
1번 사람 낚싯줄을 끊으면 2,3번의 사람의 낚싯줄끼리 엉키지 않으므로 하나만 자르면 된다.
	
문제)
```
1)
4 1 5 2 3

2)
5 4 3 2 1

3)
1 7 2 6 5 3 4

4)
10 8 9 3 7 1 2 5 4 6 

5)
3 9 12 8 7 2 6 1 4 5 10 11
```	
	
<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

LIS 문제

### 1번
|| 4| 1| 5| 2| 3| 
|:---:|---|---|---|---|---|
|LIS|1|2|2 |2 |3 |
	
1. 전체 사람 - LIS = 정답
2. 5 - 3 = 2


### 2번
|| 5| 4| 3| 2| 1| 
|:---:|---|---|---|---|---|
|LIS|1|2|3 |4 |5 |
	
1. 전체 사람 - LIS = 정답
2. 5 - 3 = 2
### 3번
|| 1| 7| 2| 6| 5|  3| 4| 
|:---:|---|---|---|---|---|---|---|
|LIS|1|2|2 |3 |3 |3 |4 |
	
1. 전체 사람 - LIS = 정답
2. 7 - 4 = 3
### 4번
|| 10| 8| 9| 3| 7|  1| 2| 5|  4| 6| 
|:---:|---|---|---|---|---|---|---|---|---|---|
|LIS|1|1|2 |1 |2 |1 |2 |3|  3| 4| 
	
1. 전체 사람 - LIS = 정답
2. 10 - 4 = 6
### 5번
|| 3| 9| 12| 8| 7|  2| 6| 1|  4| 5| 10| 11| 
|:---:|---|---|---|---|---|---|---|---|---|---|---|---|
|LIS|1|2|3 |2 |3 |4 |4 |5|  4| 4|  4| 5|
	
1. 전체 사람 - LIS = 정답
2. 12 - 5 = 7
	
	
</div>
</details>
	
	

## 7번 X번째 숫자
1234567891011121314151617...
위와 같이 12345... 숫자를 붙여서 만든 수열이 있다.
x번째 숫자를 구하여라 
문제)
```
1번) 17
2번) 25
3번) 33
4번) 120
5번) 274
```	
<details>
<summary>정답 접기/펼치기 버튼</summary>
<div markdown="1">

1의 자리 숫자 : 1~9 9개
2의 자리 숫자 : 10~99 180개
3의 자리 숫자 : 100~999 2700개

1번 17
1. 17-9 = 8
2. 8/2 = 4, 10부터 4번째 수열 : 13
3. 13의 3이 정답
	
2번 25
1. 25-9 = 16
2. 16/2 = 8, 10부터 8번째 수열 : 17
3. 17의 7이 정답
	
3번 33
1. 33-9 = 24
2. 24/2 = 12, 10부터 12번째 수열 : 21
3. 21의 1이 정답
	
4번 120
1. 120-9 = 111
2. 111/2 = 55, 나머지 1
3. 10부터 56번째 수열 : 65 의 십의자리 정답 정답:6
	
5번 274 (9+180=189보다 크므로 3자리 수라는 것을 알 수 있다.)
1. 274-9 = 265, 265 - 180 = 88
2. 88/3 = 29, 나머지가 1
3. 100부터 29번째 수열 : 128 의 백의자리 
3. 128의 1이 정답
	
	
```java
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException{
		String line ="";
			
		for(int i=1; i<275; i++) {
			line += i;
		}
		System.out.println(line.charAt(16));
		System.out.println(line.charAt(24));
		System.out.println(line.charAt(32));
		System.out.println(line.charAt(119));
		System.out.println(line.charAt(273));
		
		System.out.print(line.charAt(15));
		System.out.print(line.charAt(16));
		System.out.println();
		System.out.print(line.charAt(23));
		System.out.print(line.charAt(24));
		System.out.println();
		System.out.print(line.charAt(31));
		System.out.print(line.charAt(32));
		System.out.println();
		System.out.print(line.charAt(119));
		System.out.print(line.charAt(120));
		System.out.println();
		System.out.print(line.charAt(273));
		System.out.print(line.charAt(274));
		System.out.print(line.charAt(275));
	}

}



```
</div>
</details>
	

