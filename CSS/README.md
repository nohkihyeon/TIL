# CSS
- 웹 페이지에서 디자인
- css 초기화 : 브라우저마다 렌더링 방식이 다르기 때문에 사용

## Rule set
`selector {property : value;}`
- selector : 스타일을 적용하는 대상
- property : 스타일의 종류
- value : 속성이 가질 수 있는 값
- 예
  ```css
  body {color : gray ; font-size : small;}
  ```

### CSS 적용하기
- External Style Sheet
  - Linked Style
  - Import Style
- Internal Style Sheet

```html
<style>
	CSS 코드
</style>
```
- Inline Style Sheet
```html
<h1 style=“CSS 코드”><h1>
```

### type 선택자
- 요소명을 선택자로 사용
- `p {color : orange;}`

### 전체 선택자
- *(asterisk)를 선택자로 사용
```CSS
* { 
	margin : 0 ; 
	padding : 0 ;  
  }

```

### class와 id 선택자
- 문서 내 여러번 적용
- class 선택자 (요소명 class명-요소명 생략가능)
  - `p.note {color : orange;}`
- id 선택자 (요소명#id명-요소명 생략가능)
  - `ul#gnb { list-style-type : none;}`

### 속성(attribute) 선택자
```CSS
h1[title]{background : #ffff00;}
a[href=“#wcag”]{font-style : italic ;}
```
### 가상 클래스
- pseduo-classes는 상황에 따라 스타일 적용
- 클래스 선언 순서에 유의
 ```css
a:link { color : blue ; }
a:visited { color : purple ; }
a[href="use"]:visited {color: #dc086f;}
a:hover { color : orange ; }
a:active { color : red ; }
a:focus { background: #fcf ; }
```

### 가상 요소
- first-line, first-letter
- before, after
  ```html
  p.memo:before
  ```


### 선택자 조합
- 하위 선택자
  - `.note p {color : orange;}`
  - 
- 자식 선택자
  - `.note > p {color : orange;}`
  - 부모 요소 바로 뒤의 자식 요소
- 인접 형제 선택자
  - `h1 + h2 {color : orange;}`
  - 문서 트리 구조상 동일한 부모 요소를 가진 병렬 관계에 있는 요소 강ㄴ데 먼저 등록한 요소를 형 요소, 나중에 등록한 요소를 동생 요소라고 하는데 이때 나중에 등록한 동생 요소에 스타일을 적용하는 방법

- 선택자 그룹화
```html
  <!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			.title, #example, p {
				color:				#0000FF;
				text-decoration:	underline;
			}
		</style>
	</head>
	<body>
		<h1 class="title">선택자의 그룹화</h1>
		<h2 id="example">선택자의 그룹화 예시</h2>
		<p>모든 선택자는 ,를 사용하여 그룹으로 한 번에 선언할 수 있다.</p>
		<h1 class="title2">선택자의 그룹화</h1>
		<h2 id="example2">선택자의 그룹화 예시</h2>
	</body>
</html>
```


### 상속
```CSS
<p>부모 요소에 적용한 스타일이 자식 요소에 계속되는 것을  <a href=“inherit.html”>상속(inherit)</a>라고 한다. </p>
  
p { border : 1px solid red ;  }
a { border : inherit ; }
```

### 겹침
`<p style="color : blue; ">스타일 우선순위</p>`
- 위와 같이 스타일 내부 지정 시 무조건 내부 스타일로 적용

### 단위
- 절대 단위 
  - pt, cm, mm, pc, in
- 상대 단위
  - px, ex, em, %

### 색상 지정
- RGB 또는 Keyword로 지정
  - 16진수 color : #fff00;
  - 10진수 color : rgb(255, 0, 0);
  - keyword color : orange;

### 박스 모델
- 실제 화면에 차지하는 가로 영역 크기 계산식
  - width + margin + border + padding
- 실제 화면에 차지하는 세로 영역 크기 계산식
  - height + margin + border + padding
- width : 콘텐츠의 가로 크기
- height : 콘텐츠의 세로 크기
- border : 콘텐츠 테두리
- margin : 콘텐츠 바깥쪽 여백(border 기준)
- padding : 콘텐츠 안쪽 여백(border 기준)


### padding
```CSS

```
- 음수값 사용 불가
### margin
```CSS

```
- 음수값 / auto값 기능(width 값이 함께 지정되어야 한다.)
- 백그라운드 색값이 들어가지 않는다. (padding까지만 들어감)
### margin 겹침 현상
- 위쪽 margin과 아래쪽 margin이 겹침
- 마진이 하나로 합쳐질 때 두 개의 마진값 중에서 큰 값으로 적용
```CSS

```
### border
- 테두리를 꾸밀 수 있다.
```CSS

```
### font 
- 타이포 그라피
- font-family
  - 글꼴 패밀리를 지정하고자 할 때 사용
  - 글꼴을 여러개 지정해서 있는 것을 폰트로 지정
- font size
  - px, em, %를 사용해서 정확하게 표현
  - 모바일 디바이스 환경에 따라 %, em 사용
```CSS

```
- line-height
- font-weight
  - 글꼴의 크기를 지원하는 폰트가 많다. (bold, normal 만 지원하는 경우가 대부분)
- font-style
- font-variant
- text-indent
  - 문단의 들여쓰기 적용
### text-align
```CSS

```
### vertical-align
```CSS

```
### text-decoration
- line-through -> 가격 변동 표시할 때 많이 사용
```CSS

```
### text-transform
```CSS

```
### letter-spacing
```CSS

```
### white-space
- nowrap
- 
- pre-wrap

```CSS

```
### 배경
- background-color
  - 요소의 배경 색상을 지정할 때 사용
- background-image
  - 요소의 배경 이미지를 지정할 때 사용
- background-repeat
  - 지정한 배경 이미지의 반복 여부를 변경하고자 할 때 사용
  - 반복적인 패턴을 요구할 때 사용
- background-position
  - 지정한 배경 이미지의 위치를 변경하고자 할 때 사용
- background-attachment
- background 
- background repeat
```CSS

```

### ..

- visible
- hidden
  - 상품명을 1줄 또는 2줄 임의적으로 자를 때 사용
- auto
- scroll
  
### display
- 요소가 가지고 있는 박스의 성격을 변경할 때 사용
- block과 inline의 경계를 오고 갈 때 사용

### visibility
- `display: none;` VS  `visibility: hidden;`
  - 모습을 밀어버리고 사용할 지 그대로 공백을 유지할 지 차이


### float
- block을 정렬할 때 사용

### clear
- block을 정렬 시키고 나서 그 다음줄은 미적용하고 싶을 때 사용

### position
- 요소가 위치할 방식을 지정

### Z-index
- position이 있을 때만 의미 있게 사용
- position이 있고 z-index가 높을 수록 위에 표현
