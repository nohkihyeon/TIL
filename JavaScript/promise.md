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
