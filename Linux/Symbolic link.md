# Symbolic link
링크를 연결하여 원본 파일을 직접 사용하는 것과 같은 효과를 내는 링크이다. 운영체제 윈도우 바로가기와 비슷한 개념
특정 폴더에 링크를 걸어 NAS, library 원본 파일을 사용하기 위해 심볼릭 링크를 사용

## Symbolic link 사용법
심볼릭 링크 확인을 위해 test3 디렉토리에 echo_server.c 파일을 echo_server_copy이름으로 심볼릭링크를 연결
1. 심볼릭 링크 설정 : ln -s[원본 파일 이름][만들 파일 이름]
- ->는 링크 대상의 원본 파일을 뜻한다.
![image](https://user-images.githubusercontent.com/65120581/131468686-41d9164b-5174-4ce2-a7e7-0d26750e1584.png)

2. 심볼릭 링크 해제 : rm[링크파일 이름]
![image](https://user-images.githubusercontent.com/65120581/131469179-033d39fb-dd93-43eb-923b-f923a0280122.png)

# Hard link
원본 파일과 동일한 inode를 가진다. 원본파일이 삭제 되더라도 원본 파일의 inode를 갖고 있는 링크 파일은 여전히 사용 가능하다. 

![image](https://user-images.githubusercontent.com/65120581/131470403-0cad51cc-b168-42ea-b0a6-d5c91bf01e62.png)

## 하드링크 예
.는 현재의 디렉토리를 나타내고, ..는 모 디렉토리를 나타낸다. .,.. 모두 디렉토리 엔트리에 대한 하드링크이다.


## 하드링크와 심볼릭링크 차이점
하드링크는 두 파일 자체가 동일 다른파일에 영향을 주지않고 한 파일을 삭제 가능하다.
시스템은 한 파일이름에 대한 디렉토리 엔트리를 삭제하고 데이터 블럭은 그대로 둔다. rm 명령이 하는 일은 하드링크에 대한 링크 갯수만 하나 감소시키는 것이다.
심볼릭링크엣서 두 파일은 다른 파일이다. link를 지우면 원래의 파일은 아무런 영향을 받지 않는다.