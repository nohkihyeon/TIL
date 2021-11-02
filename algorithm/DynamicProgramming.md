# 동적 계획법
이미 했던 연산이 반복되는 결점을 보완하기 위해서 동적 계획법DP이 고안되었다.
처음 진행되는 연산은 기록ㄷ해 두고, 이미 진행했던 연산이라면 다시 연산하는 것이 아니라 기록되어 있는 값을 가져오는 것

### Top-Down
```java

public static int fibo(int n){
  if(n<=2)
    return 1;
  if(fiboData[n] == 0)
    fiboData[n] = fibo(n-1) + fibo(n-2);
  return fiboData[n];
  }
```

### Buttom-Up
```java
public static int fibo(int n){
  if(n<=1)
    return n;
  else{
    int[] fib = new int[n+1];
    fib[0] = 0;
    fib[1] = 1;
    for(int i=2; i<=n; i++){
      fib[i] = fib[i-1] + fib[i-2];
    }
    return fib[n];
  }
```

## Memoization
위의 코드에서는 하위 문제를 해결할 때 그 해결책을 저장해 두고, 똑같은 문제가 발생했을 때 저장되어 있던 해결책을 가지고 간단하게 해결 가능
동일한 문제를 반복해야 할 경우, 한 번 계산된 결과를 저장해 두었다가 활용하는 방식으로 중복 계산을 줄이는 것을 메모이제이션이라고 한다.
