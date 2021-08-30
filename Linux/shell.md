# [Linux] Shell의 개념

쉘은 명령어와 프로그램을 실행할 때 사용하는 인터페이스이다.
쉘은 운영체제에서 사용자가 입력하는 명령을 입력하고 해석해서 대신 실행해주는 프로그램이다.
윈도우, 리눅스, 유닉스, 맥os 등 운영체제에 필수적인 요소가 쉘이다.
쉘을 사용하기 위해서는 운영체제에 로그인을 해야한다. (id, password 검증 후 쉘을 실행)

## bash shell
bash는 Bourne Again Shell의 축약어다.
bash 쉘은 리눅스에서 지원되는 기본쉘이다.
- Alias 기능
- History 기능
- 연산 기능
- Job Control 기능
- 자동 이름 완성 기능
- 프롬프트 제어 기능
- 명령 편집 기능

## bash의 5개 파일
- /etc/profile      : 환경변수와 bash가 수행될 때 실행되는 프로그램을 제어하는 전역적인 시스템 설정과 관련된 파일. bashrc와 마찬가지로 로그인 수행되는 시스템 전체 환경 설정 파일
![image](https://user-images.githubusercontent.com/65120581/131292087-cc8f9fe4-44a3-42cf-8a69-8fa50e7557f7.png)

- /etc/bashrc       : ./bashrc가 개인용 환경설정 /etc/bashrc는 전체(모든 사용자) 환경설정
![image](https://user-images.githubusercontent.com/65120581/131292161-37280aa6-d43c-422f-8dbc-505b1acb1eff.png)

- ~/.bash_profile   : 환경변수와 bash가 수행될 때 실행되는 프로그램을 제어하는 지역적인 시스템 설정과 관련된 파일
![image](https://user-images.githubusercontent.com/65120581/131292222-baf155fb-db99-4086-9bd6-3227a1169bd0.png)

- ~/.bashrc         : 별칭(alias)과 bash가 수행될 때 실행되는 함수를 제어하는 지역적인 시스템 설정과 관련된 파일
![image](https://user-images.githubusercontent.com/65120581/131292254-c9aa7187-4a72-4ee5-a3fb-4454fa75914e.png)

- ~/.bash_logout     : 로그 아웃 전에 실행하는 프로그램에 관한 bash의 지역적인 시스템 설정과 관련된 파일
![image](https://user-images.githubusercontent.com/65120581/131292283-af70ab41-606f-4f03-9dc5-9e9066659f19.png)


### 일반적으로 전역적인 파일은 /ect 디렉토리에 위치
- /etc/profile과 /etc/bashrc는 모든 계정에 공통적으로 적용이 된다.
bashrc은 해당하는 로그인 계정에서만 사용하는 환경설정이다. (.bashrc 각 계정의 홈 디렉토리 아래에 존재)
- root로 로그인 시 /etc/profile과 /etc/bashrc을 읽어들여서 적용하고 그 다음 루트의 홈 디렉토리 아래에 있는 루트의 .bashrc을 읽어들인다.
