# 함수바인딩
- setTimeout에 메서드를 전달할 때 처럼, 객체 메서드를 콜백으로 전달할때 this 정보가 사라지는 문제가 생긴다.

## 사라진 'this'
```javascript
let user = {
  firstName: "John",
  sayHi() {
    alert(`Hello, ${this.firstName}!`);
  }
};

setTimeout(user.sayHi, 1000);
```
- setTimeout 객체에서 분리된 함수인 user.sayHhi가 전달되기 때문

## 방법 1: 래퍼
```javascript
let user = {
  firstName: "John",
  sayHi() {
    alert(`Hello, ${this.firstName}!`);
  }
};

setTimeout(() => user.sayHi(), 1000);

// 1초가 지나기 전에 user의 값이 바뀜
user = { sayHi() { alert("또 다른 사용자!"); } };

// setTimeout에 또 다른 사용자!
```

## 방법 2: bind
```javascript
let boundFunc = func.bind(context);

let user = {
  firstName: "John"
};

function func() {
  alert(this.firstName);
}

let funcUser = func.bind(user);
funcUser(); // John
```

## 부분 적용
```javascript
let bound - func.bind(context, [arg1], [arg2], ...);

function mul(a,b) {
  return a * b;
}

let double = mul.bind(null, 2);

alert ( double(3) );    // = mul(2, 3) = 6
alert ( double(4) );    // = mul(2, 4) = 8
alert ( double(5) );    // = mul(2, 5) = 10
```
