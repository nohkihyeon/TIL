
## 오류 내용
<img width="902" alt="스크린샷 2021-09-21 오전 11 28 54" src="https://user-images.githubusercontent.com/65120581/134102686-110117bb-a49e-4220-9d2d-a1665360ad16.png">
- 해당 파일에 .git이 생겨서 나는 오류이다.

## 해결방법
해당 디렉토리로 이동 해서 
### 1. rm -rf .git
![스크린샷 2021-09-21 오전 11 32 39](https://user-images.githubusercontent.com/65120581/134102974-fdceb220-08e7-41c1-a37b-c4a4b80c49e2.png)

### 2. git rm --cached . -rf
![스크린샷 2021-09-21 오전 11 32 22](https://user-images.githubusercontent.com/65120581/134102962-4a4fb13a-6752-46a0-909d-eb90998938f5.png)

### 3. add . / commit / push
![스크린샷 2021-09-21 오전 11 31 04](https://user-images.githubusercontent.com/65120581/134102861-c62cba5b-27b8-4236-91cf-be2cba5d87d0.png)


## 해결된 모습
<img width="1260" alt="스크린샷 2021-09-21 오전 11 31 25" src="https://user-images.githubusercontent.com/65120581/134102891-54a468ba-6eb1-42d0-846c-10d7ffc88673.png">
