# 그리디 알고리즘
문제를 해결하는 과정에서 그 순간순간마다 최적이라고 생각되는 결정을 하는 방식으로 진행하여 최종 해답에 도달하는 문제해결 방식이다.
![image](https://user-images.githubusercontent.com/65120581/139964725-07c97404-6e66-4d66-b8fc-199249a328ed.png)
- 해당 분기점마다 큰 수를 찾게 된다면 결국 12를 찾는다.
- 실제 전체 숫자 중에서 가장 큰 수는 99이다.
- 최적의 해를 찾지는 못한다.


### 이러한 단점을 극복하는 Greedy의 가장 큰 장점은 계산 속도이다.
![image](https://user-images.githubusercontent.com/65120581/139965046-63f8a949-5ab9-424f-a706-c87fa198c616.png)

## 대표 문제
거스름돈 문제 [ex 동전 0](https://github.com/nohkihyeon/TIL/blob/main/algorithm/baekjoon/11047%20동전%200.md)
- 무조건 큰 화폐 단위부터 거슬러 준다는 알고리즘만 지켜지면 최적의 해를 보장 할 수 있다.
- 그리디 알고리즘은 위의 이유 때문에 정렬 기법이 함께 사용되는 경우가 많다.
- 크루스칼 알고리즘으로 모든 간선을 정렬한 이후에 짧은 간선부터 연결하는 최소 비용 신장 트리 알고리즘이 있다.
