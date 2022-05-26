# AWS cognito 아키텍처

## 1. 프론테엔드단에서 가입, 자격 증명
- 장점 : 구성 쉬움
- 단점 : user pool id, user identity pool id를 프론트엔드에서 노출
<img width="630" alt="스크린샷 2022-05-26 오후 10 32 02" src="https://user-images.githubusercontent.com/65120581/170497812-07d113bd-269a-4f02-ab67-b2222e321917.png">


## 2. 백엔드에서 가입, 프론트엔드에서 자격 증명
- 장점 : 1번 단점 상쇄
- 단점 : : user pool id, user identity pool id를 프론트에서 노출 방지
<img width="522" alt="스크린샷 2022-05-26 오후 10 32 09" src="https://user-images.githubusercontent.com/65120581/170497836-ccfb8062-b2f1-4ab0-a622-39428ff49acd.png">



## 3. lambda + api gateway를 가입, 프론트엔드에서 자격 증명
- 장점 : 백엔드를 분리하여 따로 개발 가능
- 단점 : 개발의 복잡도 증가
<img width="504" alt="스크린샷 2022-05-26 오후 10 32 15" src="https://user-images.githubusercontent.com/65120581/170497858-34cae188-b3dd-47c3-af1b-56b551c6f149.png">

> 출처
> [aws cognito 개념 정의, 구성 생각하기](https://jonghyeok-dev.tistory.com/40)
