# [Linux] 명령어 백그라운드

## 1. 포그라운드, 백그라운드 차이점
- 포그라운드 작업
  - 사용자가 명령어를 입력하면 터미널에서 작업을 입력한 명령을 해석하여 실행
  - 결과를 화면에 출력
  - 출력된 결과 화면을 보고 다시 명령을 입력하는 대화식 작업
  - 입력한 명령어 실행이 결과가 나올 때까지 기다리는 방식이 포그라운드 방식
  - `sleep 9999999 &` <br>
![image](https://user-images.githubusercontent.com/65120581/127610010-6587bd66-8171-44c6-877b-30ac0f45d83d.png)

- 백그라운드 작업
  - 백그라운드 기능은 프로세스가 실행되는 동안 다른 프로세스가 실행 가능
  - 하나의 쉘에서 여러 개의 프로세스를 동시에 실행할 수 있는 방식
  - 명령어를 실행하면 곧바로 다음 명령어를 실행 가능
  - 필요한 여러 작업 동시에 진행하면서 포그라운드 작업을 계속 진행 가능
  - 해당 명령어 처리가 오래 걸릴걸 대비해 백그라운드를 이용하면 여러 작업을 동시에 수행 가능
  - 실행 방법은 & 기호를 추가하면 가능
  - `sleep 9999999 &` <br>
 ![image](https://user-images.githubusercontent.com/65120581/127609965-9225cf9d-646c-4031-a1b1-4cb6c3d69574.png)


 ## 2. jobs 명령어
 - `jobs` 실행중인 백그라운드가 목록으로 출력 <br>
 ![image](https://user-images.githubusercontent.com/65120581/127609102-3e48b365-3b7f-48a9-a781-2a2227b36a68.png)
 - `jobs -l` 고유 job id가 나타난다. <br>
![image](https://user-images.githubusercontent.com/65120581/127609158-ca3819a5-4800-4a73-8815-a998d1eb1195.png)
 - 프로세스 ID 출력 <br>
![image](https://user-images.githubusercontent.com/65120581/127609266-b11548c9-d00e-41a2-beaa-b31fe6d611a6.png)
 - 세션의 상태값
    - Running : 작업이 종료하지 않고 계속 진행 중
    - Done :  작업이 완료되어 0을 반환하고 종료 함
    - Stopped : 작업이 일시 중단
    - Done(code) : 작업이 정상적 완료 코드를 반환
    - Stopped(SIGTSTP) : SIGTSTP 신호가 작업을 일시 중단
    - Stopped(SIGSTOP) : SIGSTOP 신호가 작업을 일시 중단
    - Stopped(SIGTTIN) : SIGTTIN 신호가 작업을 일시 중단
    - Stopped(SIGTTOU) : SIGTTOU 신호가 작업을 일시 중단

 ## 3. $ 명령어 &
 
 - 명령어 뒤에 & 붙이면 백그라운드 에서 작업을 실행
 - `$ ./main &` <br>
![image](https://user-images.githubusercontent.com/65120581/127606916-a5306438-239a-4d52-88b2-d73815a5b3de.png)
 - Ctrl + z 로 현재 프로그램을 백그라운드에서 실행되도록 함
 - `fg % 1` <br>
![image](https://user-images.githubusercontent.com/65120581/127607617-9dd51183-74ad-494e-bbf7-204c59de581a.png)
 - % 다음의 숫자는 stopped 앞의 대괄호 숫자 값을 넣어주면 해당 작업 동작
 - 6을 입력하면 EXIT로 종료 (./main의 종료 조건이 6을 입력 받는 것이다. [코드 보기](https://github.com/nohkihyeon/TIL/blob/main/Linux/05_mysql_database.md))

## 4. kill 명령어
- `$ kill -9 프로세스ID`
- 해당 프로세스의 ID를 알고 있어야 하기 때문에 ps 명령어를 통해서 pid를 알아낸다.
- `ps auxf` or `ps auxf | grep 검색키워드` <br>
![image](https://user-images.githubusercontent.com/65120581/127610977-25f77c0c-0fc4-4dc8-a175-0eea77f63056.png)
- `kill -9 19070` 명령어 실행을 통해서 ID가 19070인 프로세스를 종료
![image](https://user-images.githubusercontent.com/65120581/127611133-6bd2ec23-88e7-47c9-9958-1cec9e7bf814.png)
- `kill % job_id` <br>
![image](https://user-images.githubusercontent.com/65120581/127611520-7645cee4-7929-4d7e-98ff-0d8d029cae5c.png)
![image](https://user-images.githubusercontent.com/65120581/127612029-8eaf43e0-3e18-4109-8c76-2bf34cf8af4c.png)





> ## 참조

> [리눅스 명령어 백그라운드 실행](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=lge920904&logNo=220687339025) <br>
> [리눅스 - 포그라운드, 백그라운드 차이는?](https://gocoder.tistory.com/1814?category=714888) <br>
> [리눅스 - 백그라운드에서 실행중인 프로세스 확인 명령어/jobs](https://gocoder.tistory.com/1815?category=714888) <br>
> [background process 종료하는 법](https://brown.ezphp.net/entry/background-process-%EC%A2%85%EB%A3%8C%ED%95%98%EB%8A%94-%EB%B2%95) <br>
