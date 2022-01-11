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
  - big, u, frame, center, xmp, frameset, dir, noframe, font, applet, strike, basefont
- HTML5 추가된 에리
  - article aside, audio, canvas, command, detailst, details, embed, figure, footer, Header, hgroup, mark, nav, outer, progress, ruby, section, source, summary, time, track, video

### HTML 구성요소
```html
<p><a href - "http:///www.w3c.org"> XHTML W3c.ORG</a></p>
```
- 태그 : < >로 표현하는 명령어
- 요소 : 시작태그부터 종료태그까지의 모든 명령어 집합
- 속성 : 시작 태그는 태그의 의미와 필요에 따라 개별적인 옵션을 가질 수 있는데, 이러한 옵션을 속성이라함

- 값 : 각 속성이 가지는 값
- html : 모든 웹 페이즈의 콘텐츠는 `<html>` 태그와 `</html>` 태그 안에 선언 (웹 페이지의 시작과 종료를 의미)
- head : 문서의 일반적인 정보
  - meta, title, link, script 등 선언
- body : 웹 브라우저 화면에 나타나는 모든 콘텐츠

## 엘리먼트
- 브라우저에 디스플레이 되는 상태에 따라서 크게 두 개의 엘리먼트 그룹으로 나눌 수 있다.

### 블록 레벨 엘리먼트(block)
- div, h1~h6, p, hr, ul, li, ol, dl., dt, dd 등
- 독립적인 형태의 상자를 의미
- 마크업에서는 아래쪽으로 쌓인다.


### 인라인 엘리먼트(inline)
- span, img, a, em, strong 등
- 마크업 할 때 인라인 엘리먼트는 단독으로 마크업될 수 없고, 반드시 블록 레벨 엘리먼트 안에 둘러싸여야 한다.

### 휴먼 랭귀지
```html
<html xml:lang="ko" lang="ko">
<p>사과는 영어로<span lang="en">apple</span>,
독일어로 <span lang="de">apfel</span>라고 합니다.</p>

```

### 문서 정보와 문자 코드 세트(meta)
- 문자 코드 세트 지정하기
```html
<meta http-equiv="content-Type" content="text/html; charset=euc-kr" />
```
- 키워드 지정하기
```html
<meta name="keywords" content= " 키워드,키워드,키워드,키워드" />
```
- 다양한 문서 정보 지정하기
```html
<meta name="subject" content="문서 제목 정보" />
<meta name="description" content="요약 설명 내용" />
<meta name="author" content="작성자 정보" />
<meta name="copyright" content="저작권 정보" />
```

#### 문단 - paragraph
```html
<p> 문단 텍스트 </p>
<br>
```

### 줄바꿈 br
```html
<p>
첫 줄 테스트 <br>
</p>
```

### 주소 address
```html
<address> 작성자 정보 콘텐츠 </address>
```

### 하이퍼링크 a - anchor
- a요소는 텍스트나 이미지 콘텐츠에 링크를 설정할 때 사용
- href 속성, target 속성, title 속성 등
- Transitional DTD 일 경우 target(_self, _blank, _parent, _top) 속성 지정
```html
<p> 
     <a href="파일명 또는 URL" target="_blank" title="대체설명">
     텍스트 또는 이미지</a>
</p>
```

#### 이미지 img
```html
<p> <img src = "이미지 명" alt="대체텍스트" /> </p>
```
- 웹 접근성 : alt반드시 제공 (시각장애인이 이미지를 볼 수 없었을 때)
- al 속성으로 대체 불가시 longdesc 속성 사용

#### 비순서형 목록 ul
- ul 안에 li사용 li사용시 ul로 묶어야한다.
```html
<ul>
     <li> 목록 항목1 </li>
     <li> 목록 항목2 
         <ul>
             <li> 목록 항목2-1 </li>
             <li> 목록 항목2-2 </li>
        </ul>

    </li>
</ul>
```

#### 순서형 목록 ol
```html
<ol>
     <li> 목록 항목1 </li>
     <li> 목록 항목2 
         <ol>
             <li> 목록 항목2-1 </li>
             <li> 목록 항목2-2 </li>
        </ol>

    </li>
</ol>
```

#### 정의형 리스트 dl
- Definition List 약자
```html
<dl>
     <dt> 용어제목</dt>
     <dd>용어설명 </dd>
</dl>
```
#### strong(bold), em
```html
<p> 텍스트 <em> 강조할 텍스트 </em> 텍스트 </p>
<p> 텍스트 <strong> 강조할 텍스트 </strong> 텍스트 </p>
```

### 테이블 요소 table, tr, th, td
- 2차원 격자 형태로 구성된 표를 생성할 때는 테이블 관련 요소를 이용할 수 있다.
```html
<table>
     <tr>
          <th>제목 셀(header cell)</th>
     </tr>
     <tr>
          <td>내용 셀(data cell)</td>
     </tr>
</table>
```


#### 셀병합 colspan, rowspan
```html
<table border="1">
     <tr>
          <td colspan="2">열이 서로 다른 2개의 셀을 병합 </td>
     </tr>
     <tr>
          <td rowspan="2"> 행이 서로 다른 2개의 셀을 병합 </td>
          <td>병합되지 않은 셀</td>
     </tr>
     <tr>
           <td>병합되지 않은 셀</td>
     </tr>
</table>
```


### 테이블 제목 및 요약문 caption요소와 summary속성
```html
<table border="1" summary="웹 접근성 및 웹표준 관련 3권의 서적명, 출판사, 가격 정보">
    <caption>웹 접근성 및 웹표준 관련 서적</caption>
    ......
</table>
```

### 제목 셀과 내용 셀의 연관성 scope
- 웹 접근성을 위해 넣어줘야한다.
```html
<table border="1">
     <tr>
          <th scope="col">열 제목</th>
          <th scope="col">열 제목</th>
          <th scope="col">열 제목</th>
     </tr>
     <tr>
          <th scope="row">행 제목</th>
          <td>내용 셀</td>
          <td>내용 셀</td>
     </tr>
</table>

```

### 열 그룹 요소 colgroup, col
```html
<table>
     <colgroup span="2">
          <col id="publishing" />
          <col id="bookTitle" />
     </colgroup>
     <col id="circulation" />
</table>
```

### 행 그룹 요소 thead, tfoot, tbody
```html
<table border="1">
     <thead><tr>헤더 행</tr></thead>
     <tfoot><tr>푸터 행</tr></tfoot>
     <tbody><tr>본문 행</tr></tbody>
</table>
```

### 폼 form
```html
<form action="서버URI" method=""get 또는 post ">
          폼의 내용
</form>
```
### 레이블 label
- 폼을 구조화하고 접근성을 높일 수 있는 요소
```html
<form action="member.asp", method="post">
    <fieldset>
        <legend>회원가입 정보</legend>
        <p><label for="userName">이름</label><input type="text" id = "userName" name = "name" value="value" /></p>
    </fieldset>
</form>
```
### input 요소
- text, password, radio, checkbox, file, image, submit, reset, button, hidden의 10가지
```html
```
### 목록상자 select, option
```html
<select name = "변수명" id = "식별자">
    <option value = "초기값"></option>

</select>

```
### 여러 줄 글상자 textarea
```html
<textarea cols= "가로크기" rows= "세로크기" name= "변수명" id= "식별자">
     초기값이 되는 텍스트 작성
</textarea>
```
### 버튼요소 button
```html
<button type="버튼의 종류" name="변수명" id="식별자">
    버튼명
</button>
```
### 인라인 프레임 iframe
- 인라인 프레임을 이용하여 콘텐츠를 삽입할 경우
```html
<iframe src ="삽입할 문서" id ="식별자" name="" width="" height="" title="">
    프레임이 지원되지 않는 환경 대체 콘텐츠
</iframe>
```

### span 요소
```html
<span>
    인라인 요소(Inline Element)
</span>>
```
### article 요소 
- 내용이 각기 독립적이고, 홀로 설 수 있는 내용을 담는 요소
```html
<article>
        <h1>쇼셜네트워크</h1>
        <p>온라인의 가상공간을 통해...</p>
        ...
</article>

```
### section 요소
- 서로 관계 있는 문서를 분리하는 역할을 함
```html
<section>
        <h1>HTML5</h1>
        <p>웹 문서를 만드는 HTML 최신 규격...</p>
        ...
</section>
```

### article, section, div 차이
- article 역시 section 처럼 문서를 분리하는 역할을 하므로 section과 비슷한 점이 많다.
  1. 내용이 독립적이고 스스로 쓰일 수 있는 내용이라면 article
  2. 내용이 서로 관계가 있다면 section
  3. 의미적으로 관계가 없다면 div 사용(div는 오직 내용을 묶는 역할)


```html
<article>
    <section>Section1</section>
    <section>Section2</section>
    <section>Section3</section>
</article>
```

### header 요소
- section의 머리말을 담기도 함 `<div id="header>`와 같은 역할

### footer 요소
```html
<body>
    <div>
        <p>본문 내용</p>
    </div>
    <footer><a href= "#">목차 바로가기</a></footer>
</body>
```

### nav 요소
```html
<nav>
    <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">Guest</a></li>
        <li><a href="#">Notice</a></li>
    </ul>
</nav>>
```

### aside 요소
```html
<article>        
<header>위키피디아</header>        
<section>
위키피디아는 의미있는 웹 기반의 자유로운 백과사전으로, 전 세계 누구나 참여할 수 있다.
</section>        
<aside>
"지구상에 모든 사람이 모든 인류 지식에 자유롭게 접근할 수 있는 세계를 상상해 보라.
그것이 우리가 하는 일이다"
</aside>
</article>
```

### canvas 요소
- 그래프나 게임 같은 동적인 비트맵 그래픽을 표시하는데 사용
- Canvas 요소는 앞서가는 특징이다. 자바스크립트로 무엇이든지 그리기를 원한다면 canvas 요소가 필요
```html
<canvas id="clock" width="150" height="150">
  <img src="images/clock.png" width="150" height="150" alt="">
</canvas>
```
### figure, figcaption 요소
```html
<figure id="zoo">
  <img src="lion.png" alt="lion">
  <img src="tigar.png" alt="tigar">
  <img src="bear.png" alt="bear">
  <figcaption>동물원 동물들. 왼쪽에서 오른쪽으로 사자, 호랑이, 곰</figcaption>
</figure>
```
### 요소
```html
(X) <p>여기에 강조 된 <em>문단이 있다.</p></em>
(O) <p>여기에 강조 된 <em>문단</em>이 있다.</p>

잘못된 사용예 (X) 
<ul>
    <li>Coffee</li>
    <li>Tea
        <ul>
            <li>Black tea</li>
        </ul>
</ul>

올바른 사용예(O)
<ul>
    <li>Coffee</li>
    <li>Tea
        <ul>
            <li>Black tea</li>
        </ul>
     </li></ul>

```

- 마우스와 키보드 이벤트 처리기 대응표
  - onmousedown = onkeydown
  - onmouseup = onkeyup
  - onclick = onkeypress
  - onmouseover = onfocus
  - onmouseout = onblur


- 마우스의 움직임이나 포커스의 이동만으로 콘텐츠의 내용이나 순서가 변하지 않도록 설계
```
(△) onmouseover & onfocus 시각이 없는 사용자는 내용의 변화를 예측하거나 인식하지 못하므로 되도록 지양 
(O) onclick 시각이 없는 사용자도 내용의 변화를 예측할 수 있음
```
  
- 마우스가 없는 장치에도 키보드만으로도 인터페이스의 조작이 가능하도록 설계
```html
<a href="#gnb" onclick="RemoveEl('tm3','gnb'); return false;" onkeypress="RemoveEl('tm3','gnb'); return false;" class="ms"><img src="../images/icon_minus_off.gif" class="rollover" alt="메뉴" /></a>
```
