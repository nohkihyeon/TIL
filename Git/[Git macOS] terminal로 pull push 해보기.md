# [Git macOS] terminal로 pull push 해보기

<img width="932" alt="스크린샷 2021-09-16 오전 1 23 30" src="https://user-images.githubusercontent.com/65120581/133471785-532184f6-5563-45fb-a0fb-21906b7174c8.png">
여기있는 Repository에 특정 파일을 terminal로 업로드 하는 방법

## 1. 원하는 파일을 해당 리포지토리와 일치하는 디렉토리에(로컬 저장소) 파일을 옮긴다.
<img width="1614" alt="스크린샷 2021-09-16 오전 1 25 10" src="https://user-images.githubusercontent.com/65120581/133472097-690b3c1c-3b58-4227-aed0-e55f0264e39f.png">

## 2. github에 있는 원격 저장소를 로컬 저장소에 반영한다.
로컬 저장소 : /Users/nohgihyeon/git/OOP

```
cd /Users/nohgihyeon/git/OOP
...
git pull origin master
```
<img width="566" alt="스크린샷 2021-09-16 오전 1 28 50" src="https://user-images.githubusercontent.com/65120581/133472626-cfae30cc-1476-4e0a-b075-541555b0cb65.png">

## 3. git init
<img width="570" alt="스크린샷 2021-09-16 오전 1 30 05" src="https://user-images.githubusercontent.com/65120581/133472826-9482fd0e-274c-42d7-a98a-a645d5b0ee2e.png">

## 4. git add <파일이름>
`git add 2020-Pattern-Compound` <br>
<img width="571" alt="스크린샷 2021-09-16 오전 1 30 53" src="https://user-images.githubusercontent.com/65120581/133472936-42aa44f5-43a2-449d-b577-9306562432cf.png">

#### cf) git add . : git add . 하는 것은 현재 폴더 안에 있는 모든 파일들이 올라간다.

## 5. git commit -m "first commit"
<img width="773" alt="스크린샷 2021-09-16 오전 1 32 22" src="https://user-images.githubusercontent.com/65120581/133473159-e9efc2aa-424e-4ce0-bab6-2130a7061c06.png">

## 6. git remote add origin <원격 저장소 url>
<img width="736" alt="스크린샷 2021-09-16 오전 1 35 09" src="https://user-images.githubusercontent.com/65120581/133473565-f5447203-aa36-433b-8399-627a3d91567a.png">

                                                    
### error: remote origin already exists. 가 뜨는 경우 : git remote
- git remote 정보 보기
  - git remote -v 
- git remote 정보 변경
  - git remote set-url origin <url>

## 7. git push origin master
<img width="678" alt="스크린샷 2021-09-16 오전 1 35 29" src="https://user-images.githubusercontent.com/65120581/133473607-a3e99be6-e8ef-4d48-a02e-83e119d0b250.png">
  
## 결과 화면
<img width="876" alt="스크린샷 2021-09-16 오전 1 39 45" src="https://user-images.githubusercontent.com/65120581/133474256-b8634705-08ec-4d27-84c6-a581ac8c0534.png">


