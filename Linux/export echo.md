# [Linux] 환경변수
Linux 커맨드 창에 `$ cd` 입력하면 로그인 했을 시 디렉토리로 돌아가게 된다.
![image](https://user-images.githubusercontent.com/65120581/131077352-908ca55f-c917-4e65-a79c-22dfe7928068.png) <br>
이와 관련된 것이 Linux 환경변수이다.
리눅스의 환경변수를 등록하는 방법에 대해 알아보자  


## 1. export 명령어
![image](https://user-images.githubusercontent.com/65120581/131076496-f7055fe6-1e6c-43f7-bf46-3fca887d1132.png)
- export 명령어로 쉘 변수를 환경변수로 저장할 수 있다.
이 환경 변수는 터미널이 꺼지면 사라지게 된다.
![image](https://user-images.githubusercontent.com/65120581/131080928-8789c015-1dcf-494c-b462-126468faf170.png)

- 쉘을 실행할 때마다 쉘변수를 환경변수로 자동으로 설정
- `vi .bashrc` 스크립트 안에 설정할 수 있다.
![image](https://user-images.githubusercontent.com/65120581/131081119-3c010650-d57e-4c02-b463-bb2a9b789599.png)

## 2. echo 명령어
export를 이용하면 전체 변수가 나오기 때문에 각각의 변수만을 보고 싶다면 `echo $[환경변수명]` 입력
![image](https://user-images.githubusercontent.com/65120581/131081502-59f4e12a-ff78-410e-bf43-35838c92b5d9.png)

터미널 창에 `[환경변수명] = [변수]` 입력
값 변경이 잘 되었는지 `echo $[환경변수명]`를 통해서 확인
![image](https://user-images.githubusercontent.com/65120581/131082758-ca5d39e3-e86a-4fd1-ae38-8ebdd1a471b6.png)

`unset [환경변수명]` 입력 시 등록된 환경변수를 삭제하는 명령어이다.
![image](https://user-images.githubusercontent.com/65120581/131082136-0845de58-23b0-41ee-946b-47fa3f7237df.png)

![image](https://user-images.githubusercontent.com/65120581/131082180-c1bb4c4e-c709-428f-afd6-2691277cdfbd.png)


## 3. printenv 명령어
![image](https://user-images.githubusercontent.com/65120581/131076595-2a689bac-ffaa-43d3-8c68-582956700265.png)

