# 분할 정복
분할정복 알고리즘은 문제를 나눌 수 없을 때까지 나누어서 각각을 풀면서 다시 병합하여 문제의 답을 얻는 알고리즘이다.

1. Divide : 문제가 분할이 가능한 경우, 2개 이상의 문제로 나눈다.
2. Conquer : 나누어진 문제가 여전히 분할이 가능하면, 또 다시 Divide를 수행한다. 그렇지 않으면 문제를 푼다.
3. Combine : Conquer한 문제들을 통합하여 원래 문제의 답을 얻는다.

## 분할정복의 응용
#### 1. 병합 정렬 (Merge Sort)
시간 복잡도 : O(nlogn) 공간 복잡도 : O(n) <Br>
<img width="390" alt="스크린샷 2021-11-03 오후 7 07 00" src="https://user-images.githubusercontent.com/65120581/140041610-1666f3fb-d720-4f6c-bdb7-4b0df265cd13.png">
