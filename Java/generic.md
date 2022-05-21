# generic
- 파라미터 타입이나 리턴 타입에 대한 정의를 외부로 미룬다.
- 타입에 대한 유연성과 안전성을 확보한다
- 런타임 환경에 아무런 영향이 없는 컴파일 시점의 전처리 기술

```java
class Sample<T> {
  private T data;
  
  public void setData(T data) {
    this.data = data;
  }
  
  public T getData() {
    return data;
  }
} 
```

> 참조
> [[JAVA] 제네릭(Generic)이란](https://jehuipark.github.io/java/java-generic)
