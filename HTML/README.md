# 웹 표준
- 웹에서 표준적으로 사용되는 기술의 총칭
- w3c 권고안을 준수하여 사용
- http://www.w3schools.com/tags/


## 웹 표준 관련 기술의 소재
- 퍼블리싱
  - 구조 HTML
  - 표현 CSS
  - 동작 Script

- 시맨틱 웹
  - 인간의 언어가 기계어에게도 이해될 수 있도록


## HTML 발전
- HTML 4.01 -> XHTML1.1 -> HTML5.0

## 의미있는 마크업
- HTML, XHTML, XML : structure 언어
- CSS : 비주얼적 표현을 위한 언어
- XML : HTML에 담겨져 있는 형식적 요소를 완전히 배제하는 방식
- XHTML : 
- HTML5 : doctype(html5로 사용할 때 브라우저에게 선언)

## HTML5
- api 제공
- 플러그인 필요 없이 동영상 재생
- 다중 메시징 처리

### 문서형의 정의
- DTD (Digital Type Definition)
- 문서형 선언은 반드시 html 문서 첫 줄에 위치해야 하며, 문서형 선언의 위에는 공백을 포함해서 어떤 요소도 올 수 없다.
-
- HTML 4.01 
  - -HTML Strict DTD
    ```HTML
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" http://www.w3.org/TR/html4/strict.dtd">
    ```

  - HTML Transitional DTD
    ```HTML
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    ```

  - HTML Frameset DTD
    ```HTML
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd"> 
    ```
- XHTML 1.0 
  - XHTML Strict DTD
    ```HTML
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
    ```

  - XHTML Transitional DTD
    ```HTML
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    ```

  - XHTML Frameset DTD
    ```HTML
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
    ```
- HTML5 5 
  ```HTML
  <!DOCTYPE html>
  ```
- 

### 문서 타입 정의DTD 요약
- 사라진 엘리먼트
  - big, u, frame, center
- HTML5 추가된 에리
  - article aside, audio, canvas, command, detailst

### HTML 구성요소
```html
<p><a href - "http:///www.w3c.org"> XHTML W3c.ORG</a></p>
```
- 태그
- 요소
- 속성

- 값
- html
- head
  - meta, title, link, script 등 선언
- body

## 엘리먼트
- 브라우저에 디스플레이 되는 상태에 따라서 크게 두 개의 엘리먼트 그룹으로 나눌 수 있다.

### 블럭 레벨 엘리먼트(block)
- div, h1~h6, p, hr, ul, li, ol, dl., dt, dd 등
- 독립적인 형태의 상자를 의미
- 마크업에서는 아래쪽으로 쌓인다.


### 인라인 엘리먼트(inline)

### 휴먼 랭귀지
```html
<html xml:lang 
```

### 문서 정보와 문자 코드 세트(meta)
- 문자 코드 세트 지정하기
```html

```
- 키워드 지정하기
```html

```


##### 문단 - paragraph
```html
<p> 문단 텍스트 </p>
<br>

```

##### 줄바꿈 br
```html
<p>
첫 줄 테스트 <br>
</p>

```

##### 주소 address
```html
<address> 작성자 정보 콘텐츠 </address>
```

##### 하이퍼링크 a - anchor
- a요소는 텍스트나 이미지 콘텐츠에 링크를 설정할 때 사용
- href 속성, target 속성, title 속성 등
- Transitional DTD 일 경우 target(_self, _blank, _parent, _top) 속성 지정
```html
<p>
    <a href = " ">
    </a>
</p>
```

##### 이미지 img
```html
<p> <img src = "이미지 명" alt="대체텍스트" /> </p>
```
- 웹 접근성 : alt반드시 제공 (시각장애인이 이미지를 볼 수 없었을 때)
- al 속성으로 대체 불가시 longdesc 속성 사용

##### 비순서형 목록 ul
- ul 안에 li사용 li사용시 ul로 묶어야한다.
```html
<ul>
```

##### 순서형 목록 ol
```html
```

##### 정의형 리스트 dl
- Definition List 약자
```html
<dl>

</dl>
```
#### strong(bold), em
```html
```

### 테이블 요소 table, tr, th, td
- 2차원 격자 형태로 구성된 표를 생성할 때는 테이블 관련 요소를 이용할 수 있다.
```html
 <Table>
    <tr>
        <th>제목 셀</th>
    </tr>
</Table>
```


#### 셀병합 colspan, rowspan
```html
```


#### 테이블 제목 및 요약문 caption요소와 summary속성
```html
```

#### 제목 셀과 내용 셀의 연관성 scope
- 웹 접근성을 위해 넣어줘야한다.
```html
<table border
<tr>
    <th scope="col"> 열 제목</th>
    <th scope="col"> 열 제목</th>
    <th scope="col"> 열 제목</th>
</tr> = "1">
</table>
```

#### 열 그룹 요소 colgroup, col
```html
```

#### 행 그룹 요소 thead, tfoot, tbody
```html
```

#### 폼 form
```html
<form action=>
    <fieldset>
        <legend>폼 요소의 제목</legend>
    </fieldset>
</form>
```
#### 레이블 label
- 폼을 구조화하고 접근성을 높일 수 있는 요소
```html
<form action="member.asp", method="post">
    <fieldset>
        <legend>회원가입 정보</legend>
        <p><label for="userName">이름</label><input type="text" id = "userName" name = "name" value="value" /></p>
    </fieldset>
</form>
```
#### input 요소
- text, password, radio, checkbox, file, image, submit, reset, button, hidden의 10가지
```html
```
#### 목록상자 select, option
```html
<select name = "변수명" id = "식별자">
    <option value = "초기값"></option>

</select>

```
#### 여러 줄 글상자
```html
```
#### 버튼요소 button
```html
<button type="버튼의 종류" name="변수명" id="식별자">
    버튼명
</button>
```
#### 인라인 프레임 iframe
- 인라인 프레임을 이용하여 콘텐츠를 삽입할 경우
```html
<iframe src ="삽입할 문서" id ="식별자" name="" width="" height="" title="">
    프레임이 지원되지 않는 환경 대체 콘텐츠
</iframe>
```

#### span 요소
```html
<span>
    인라인 요소(Inline Element)
</span>>
```
#### article 요소 
```html
```
#### section 요소
```html
```

### article, section, div 차이
- article 역시 section 처럼 문서를 분리하는 역할을 하므로 section과 비슷한 점이 많다.
  1. 내용이 독립적이고 스스로 쓰일 수 있는 내용이라면 article
  2. 내용이 서로 관계가 있다면 section
  3. 의미적으로 관계가 없다면 div 사용(div는 오직 내용을 묶는 역할)

#### header 요소
- section의 머리말을 담기도 함 `<div id="header>`와 같은 역할

#### footer 요소

#### nav 요소
```html
<nav>
    <ul>
        <li><a href="#">HOME</a></li>
        <li><a href="#">HOME</a></li>
        <li><a href="#">HOME</a></li>
    </ul>
</nav>>
```

#### aside 요소
```html

```

#### canvas 요소
- 그래프나 게임 같은 동적인 비트맵 그래픽을 표시하는데 사용
- Canvas 요소는 앞서가는 특징이다. 자바스크립트로 무엇이든지 그리기를 원한다면 canvas 요소가 필요
```html

```
#### figure, figcaption 요소
```html

```
#### 요소
```html

```

- 마우스와 키보드 이벤트 처리기 대응표
  - onmousedown = onkeydown
  - onmouseup = onkeyup
  - onclick = onkeypress


- 마우스의 움직임이나 포커스의 이동만으로 콘텐츠의 내용이나 순서가 변하지 않도록 설계
  
- 마우스가 없는 장치에도 키보드만으로도 인터페이스의 조작이 가능하도록 설계
