# React

## Component 작성

```html
<html>

<body>
    <Top>
        <Sidebar></Sidebar>
    </Top>
</body>

</html>
```
1. 가독성
2. 재사용성

## Create React App

1. npm 설치 위해 node.js 선수 설치
2. <img width="331" alt="스크린샷 2021-12-11 오후 11 23 04" src="https://user-images.githubusercontent.com/65120581/145679988-fb423ba2-acd2-421f-97a8-0a72ee5e429d.png">


- g : global
<img width="766" alt="스크린샷 2021-12-11 오후 11 24 07" src="https://user-images.githubusercontent.com/65120581/145680027-b25a88ab-da2a-4d81-b1df-a85360cd694b.png">

- 에러 발생시 sudo
<img width="553" alt="스크린샷 2021-12-11 오후 11 25 16" src="https://user-images.githubusercontent.com/65120581/145680064-3179c435-596b-4544-acf3-f528b394c912.png">

- npx create-react-app
  - 항상 최신상태를 다운받아서 1번만 사용
  - 다 사용후 삭제하는 개념

- 해당 경로에 file 생성 후
<img width="624" alt="스크린샷 2021-12-11 오후 11 28 00" src="https://user-images.githubusercontent.com/65120581/145680153-acbbaf98-615e-464c-ac9b-14688725f9ee.png">
- . 현재 디렉토리에 설치
<img width="592" alt="스크린샷 2021-12-11 오후 11 30 40" src="https://user-images.githubusercontent.com/65120581/145680249-cd574a3d-27c2-4844-a7df-fcfbc2c9c29e.png">
- 설치 완료

<img width="602" alt="스크린샷 2021-12-11 오후 11 34 23" src="https://user-images.githubusercontent.com/65120581/145680369-2c0452eb-769d-4fd3-b4eb-5a69dea432b4.png">


## 샘플 웹앱 실행
- vscode Terminal에서 start
<img width="958" alt="스크린샷 2021-12-11 오후 11 36 55" src="https://user-images.githubusercontent.com/65120581/145680444-3bbce0f9-6227-432d-acc0-0f801222c61f.png">
- web app 실행
<img width="1945" alt="스크린샷 2021-12-11 오후 11 37 32" src="https://user-images.githubusercontent.com/65120581/145680460-21b117cc-7fa1-45bd-bc1a-49e2017e19a7.png">


## JS 코딩하는 법

- 요소 안의 root 태그
<img width="1066" alt="스크린샷 2021-12-11 오후 11 39 17" src="https://user-images.githubusercontent.com/65120581/145680515-4b713d0a-dc7d-481c-8f26-5a64107a68ba.png">

<img width="966" alt="스크린샷 2021-12-11 오후 11 40 17" src="https://user-images.githubusercontent.com/65120581/145680546-e02dc8bb-6286-4324-a31d-76af8b915073.png">


<img width="1139" alt="스크린샷 2021-12-11 오후 11 42 48" src="https://user-images.githubusercontent.com/65120581/145680627-f7420cf0-7ceb-41c3-98cb-b8aabf078a94.png">

<img width="1953" alt="스크린샷 2021-12-11 오후 11 43 47" src="https://user-images.githubusercontent.com/65120581/145680678-1388e8ca-d77b-4f96-a348-c90ccc2ba81b.png">


## 배포하는 방법
- 1.7MB 나 사용
<img width="1265" alt="스크린샷 2021-12-11 오후 11 47 46" src="https://user-images.githubusercontent.com/65120581/145680834-cc278962-51b0-4301-b8a7-a92d9e3fc49a.png">

- npm run build
<img width="260" alt="스크린샷 2021-12-11 오후 11 49 23" src="https://user-images.githubusercontent.com/65120581/145680874-992a1ff7-ac5d-4e06-a4e7-4dd632ed3ecb.png">

- npm serve -g serve
  - 컴퓨터 어디에서든 serve라는 명령어로 설치 가능
- npx serve -s build
  - 한번만 웹서버를 설치 -s build : build라는 디렉토리르 루트로 설정
<img width="524" alt="스크린샷 2021-12-11 오후 11 52 01" src="https://user-images.githubusercontent.com/65120581/145680955-12b9cd47-b2c1-487d-9a4b-2e453094882d.png">

- 145 kB로 대폭 하향
<img width="1072" alt="스크린샷 2021-12-11 오후 11 52 24" src="https://user-images.githubusercontent.com/65120581/145680965-602b2d0c-c74c-437d-89cd-d65ed9196858.png">



## 리액트가 없다면

- 태그들을 하나하나 전부 수정해야 
```html
<html>
    <body>
        <h1>WEB</h1>
        World Wide Web!

        <ul>
            <li><a href="1.html">HTML</a></li>
            <li><a href="2.html">CSS</a></li>
            <li><a href="1.html">JavascriptL</a></li>
        </ul>

        <h2>HTML</h2>
        HTML is HyperText Markup Language.
    </body>
</html>
```




