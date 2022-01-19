## 렌더링 엔진

---

대부분의 브라우저는 렌더링을 수행하는 렌더링 엔진을 가지고 있다.

- 파이어폭스 : Gecko
- 사파리 : Webkit
- 크롬 : Blink

## 렌더링 과정

---

1. DOM(Domain Object Model), CSSOM(CSS Object Model) 생성
    
    ![DOM](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d11b3882-2681-47d3-b491-98784beddfa8/img.png)
    
    DOM
    
    ![CSSOM](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/edb24248-f964-427a-8bb7-d2ea26b0bd99/img.png)
    
    CSSOM
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9386634e-5b87-4e30-8598-74d44ee97a2c/Untitled.png)
    

## 1. HTML 렌더링 처리 중 reflow와 repaint 단계, 각 단계 어떤 역할

### Reflow

- 어떤 액션이나 이벤트에 의해 DOM 요소의 크기나 위치 등을 변경하면 해당 노드의 하위 노드와 상위 노드들을 포함하여  Layout 단계를 다시 수행
- 특정 요소의 위치와 크기뿐만 아니라 연관된 요소들의 위치와 크기도 재계산

### Repaint

- Render Tree를 다시 화면에 그려주는 과정
- paint 과정이 다시 수행
- Reflow가 발생하면 변경된 Render Tree를 화면에 다시 그려줘야 한다.
- Repaint  역시 Reflow와 같이 연산이 필요하기 때문에 브라우저의 퍼포먼스에 영향

---

## 2. reflow와 repaint를 최소화하기 위한 최적화 방법 2가지 이상 예제

### 성능 최적화를 위해 할 수 있는 것

1. 레이아웃 변화가 많은 경우 position에 fixed와 absolute를 사용
2. 애니메이션을 구현할 때 프레임을 조절
3. 화면에서 숨겨지는 노드에서 visibility: invisible보다 display:none 속성을 사용 (visibiliy는 화면에 보이지 않더라도 레이아웃 상에서 공간을 차지하기 때문에 Reflow 영향을 받는다.)
4. CSS 하위 셀렉터를 최소화하고 클래스 변경을 통해 스타일을 제어할 때는 최대한 마지막 단계에 있는 노드의 클래스를 변경
5. 자바스크립트를 통해 스타일 변경할 경우, cssText 사용

### reflow 예시

```jsx
// Reflow 2번 발생
const body = document.body;
body.style.width = '50px';
body.style.height = '100px';

// 한번에 처리
const body = document.body;
body.style.cssText = 'width: 50px; height: 100px;'
```

- cssText로 reflow가 한번만 실행

```jsx
const ulElemnet = document.getElementsByTagName('ul')[0];
for(let i =0; i < 10; i++){
   ulElemnet.innerHTML += `<li> list${i} </li>`;
}

const ulElemnet = document.getElementsByTagName('ul')[0];
let strHtml = ulElemnet.innerHTML;
for(let i=0; i<10; i++){
   strHtml += `<li> list${i} </li>`;
}
ulElemnet.innerHTML = strHtml;
```

- DOM Fragment 통해 추가

```jsx
const frag = document.createDocumentFragment();
const ul = frag.appendChild(document.createElement('ul'));

for (let i = 1; i <= 3; i++) {
  li = ul.appendChild(document.createElement('li'));
  li.textContent = `item ${ i }`;
}

document.body.appendChild(frag);
```

- 캐쉬를 활용한 Reflow 최소화

```jsx
// Bad practice
for (let i = 0; i < len; i++) {
  el.style.top = `${ el.offsetTop + 10 }px`;
  el.style.left = `${ el.offsetLeft + 10 }px`;
}

// Good practice
let top = el.offsetTop, left = el.offsetLeft, elStyle = el.style;

for (let i = 0; i < len; i++) {
  top += 10;
  left += 10;
  elStyle.top = `${ top }px`;
  elStyle.left = `${ left }px`;
}
```

## 참조

> [Reflow와-Repaint](https://velog.io/@ggong/Reflow%EC%99%80-Repaint)
>
