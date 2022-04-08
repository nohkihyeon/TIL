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
- sorted는 스트림으 아이템들으 정렬항 새로우 스트림으 생성
```java
List<String> langs =
        Arrays.asList("java", "kotlin", "haskell", "ruby", "smalltalk");
System.out.println("sorted:");
langs.stream().sorted()
        .forEach(System.out::println);

System.out.println("reversed:");
langs.stream().sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
```
## 결과
```
sorted:
haskell
java
kotlin
ruby
smalltalk

reversed:
smalltalk
ruby
kotlin
java
haskell
```
