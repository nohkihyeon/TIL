# 프라미스 체이닝
```javascript
new Promise(function(resolve, reject) {

  setTimeout(() => resolve(1), 1000); // (*)

}).then(function(result) { // (**)

  alert(result); // 1
  return result * 2;

}).then(function(result) { // (***)

  alert(result); // 2
  return result * 2;

}).then(function(result) {

  alert(result); // 4
  return result * 2;

});
```
프라미스 체이닝은 result가 .then 핸들러의 체인(사슬)을 통해 전달된다는 점에서 착안한 아이디어입니다.

위 예시는 아래와 같은 순서로 실행됩니다.

1초 후 최초 프라미스가 이행됩니다. – (*)
이후 첫번째 .then 핸들러가 호출됩니다. –(**)
2에서 반환한 값은 다음 .then 핸들러에 전달됩니다. – (***)
이런 과정이 계속 이어집니다.
result가 핸들러 체인을 따라 전달되므로, alert 창엔 1, 2, 4가 순서대로 출력됩니다.

## 프로미스는 성공 도는 실패만 한다.

```javascript
let promise = new Promise(function(resolve, reject) {
  resolve("완료");
  
  reject(new Error("..."));           // 무시됨
  setTimeout((() -> resolve("..."));  // 무시됨
});
```

## Error 객체와 함께 거부하기
- executor는 Reject를 호출해야한다.
- 인수는 resolve와 마찬가지로 어떤 타입도 가능하지만 Error 객체 또는 Error를 상속받은 객체를 사용ㅎ할 것을 추천한다.

## resolve reject 함수 즉시 호출하기
- executor는 대개 무언가를 비동기적으로 수행하고, 약간의 시간이 지난 후에 resolve, reject를 호출하는데 꼭 이렇게 할 필요가 없다.
```javascript
let promise = new Promise(function(resolve, reject) {
  // 일을 긑마치는 데 시간이 들지 않음
  resolve(123); // 결과(123)를 즉시 resolve wjsekf
});
```
