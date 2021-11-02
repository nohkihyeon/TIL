# 재귀 알고리즘
재귀 함수는 자기 자신을 참조하는 함수이다. 원래의 문제를 동일한 유형의 하위 문제로 나누고 하위 문제를 해결한 다음 결과와 결합한다.
- base Case : 재귀함수의 종료 조건으로 더 이상 문제를 쪼갤 수 없을 때, 자기 자신을 호출하지 않고 답이 나올 때
- Recursion Case : 복잡한 입력을 더 간단한 입력으로 분류하여 자기자신을 호출


```python
def multi_table_2(n):
    if n==0:
        print('end')
    else:
        multi_table_2(n-1)
        print('2 * {} = {}'.format(n, 2*n))

multi_table_2(9)
```

<img width="346" alt="스크린샷 2021-11-02 오후 10 31 17" src="https://user-images.githubusercontent.com/65120581/139856559-2c99f191-4666-4111-89dd-0f5b8b46011e.png">
