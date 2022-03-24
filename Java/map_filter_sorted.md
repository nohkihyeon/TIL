# map
- 요소들으 특정조건에 해당하는 값으로 변환
```java
ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple","Samsung","LG","Microsoft","Google"));

System.out.println(list.stream().map(s->s.toUpperCase()).collect((Collectors.joining(" "))));

list.stream().map(String::toUpperCase).forEach(s->System.out.println(s));
```

# filter
- 요소들을 조건에 따라 걸러내느 작업
```java
System.out.println(list.stream().filter(t->t.length() < 5).collect(Collectors.joining(" ")));
```
# sorted
- 요소들을 정렬해주는 작업
```java
System.out.println(list.stream().filter(t->t.length() > 5).sorted().collect(Collectors.toList()));
```
