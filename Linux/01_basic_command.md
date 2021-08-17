# 리눅스 기본 명령어
- [ls](#01-lslist-segments) - 현재 위치의 파일 목록을 조회하는 명령어
- [cd](#02-cdchange-directory) - 디렉토리를 이동하는 명령어
- [touch](#03-touch) - 파일의 용량이 0인 파일을 생성, 날짜 변경하는 명령어
- [mkdir](#04-mkdirmake-directory) - 디렉토리를 생성하는 명령어
- [cp](#05-cpcopy) - 파일을 복사하는 명령어
- [mv](#06-mvmove) - 파일을 이동시키는 명렁어
- [rm](#07-rmremove) - 파일을 제거하는 명령어
- [cat](#08-catcatenate) - 파일의 내용을 화면에 출력하거나 파일을 만드는 명령어
- [redirection](#09-redirection-) - 화면에 출력되는 결과를 파일로 저장하는 명령어
- [alias](#10-alias) - 자주 수행하는 명령어들을 쉽게 사용할 수 있도록 설정하는 명령어

  >## putty 실행 
  >![image](https://user-images.githubusercontent.com/65120581/126269047-a03a7bbd-6404-428b-b0b5-99614e1860bb.png)
  >## 접속 장면
  >![image](https://user-images.githubusercontent.com/65120581/126269287-a7ff6acc-d5b0-459a-8044-471c36a08c7a.png)
  >## Id Password 입력
  >![image](https://user-images.githubusercontent.com/65120581/126269412-770eb192-9a58-4043-bd49-169b22237b2d.png)

## 01. ls(list segments)
- ls는 현재 위치의 파일 목록을 조회하는 명령어
  - ls -l : 파일들의 상세정보를 출력
  - ls -a : 숨어있는 파일들도 표시
  - ls -t : 파일들을 생성된 시간별료 표시
  - ls -rt : 파일들을 오래된 시간부터 표시
  - ls -F : 파일을 표시할 때 마지막에 유형을 나타내는 파일명을 끝에 표시
      - 각 옵션들은 자유자재로 합성해서 사용 가능
      - cf) ls -lrt : 파일들의 상세정보를 나타내며, 오래된 것부터 표시


![image](https://user-images.githubusercontent.com/65120581/126273288-b0aadb47-f405-4d7e-8f72-1afd22a58d6c.png)
![image](https://user-images.githubusercontent.com/65120581/126276441-db39e3b9-900e-4e5d-bdc4-7fd83ae5cca3.png)



## 02. cd(change directory)
- cd는 경로를 이동할 때 사용하는 명령어
  - cd ~ : 어느 곳에든지 홈 디렉토리로 바로 이동
  - cd.. : 상위 디렉토리로 이동
  - cd/dir : 절대경로 dir로 이동
  - cd - : 이동하기 바로 전의 디렉토리로 이동
![image](https://user-images.githubusercontent.com/65120581/126276560-77c0935d-894a-4be6-8863-081699fdba71.png)

## 03. touch
- touch는 파일의 용량이 0인 파일을 생성, 날짜 변경하는 명령어
   - touch filename : filename의 파일을 생성
   - touch -c filename : filename의 시간을 현재시간으로 변경
   - touch -t 202107201500 filename : filename의 시간을 날짜정보(YYYYMMDDhhmm)로 변경
   - touch -r filename1 filename2 : filename2의 날짜 정보를 filename1의 날짜 정보와 같게 변경


## 04. mkdir(make directory)
- mkdir은 새로운 디렉토리를 만들 때 사용하는 명령어
    - mkdir dirname : dirname의 디렉토리를 생성
    - mkdir -p dirname/subdname : 존재하지 않는 디렉토리의 하위디렉토리까지 생성 
    - mk -m 644 dirname : 특정 퍼미션을 갖는 디렉토리를 생성
```linux
mkdir directory2
```
![image](https://user-images.githubusercontent.com/65120581/126273687-839ac642-131e-41eb-a9da-3e86296683bc.png)

## 05. cp(copy)
- cp는 파일을 복사하는 명령어
    - cp file cfile : file을 cfile이라는 이름으로 복사합니다.
    - cp -f file cfile : 복사할 때 복사대상이 있으면 지우고 강제로 복사
    - cp -R dir cdir : 디렉토리 복사할 때 사용, 폴더안의 모든 하위경로와 파일들을 모두 복사
```vi 
$ cp -f directory2 directoryCopy
```

## 06. mv(move)
-  mv는 파일을 이동하는 명령어
    - mv fname mfname : fname의 파일을 mfname의 이름으로 이동/변경
    - mv -b fname mfname : mfname의 파일이 존재하면 mfname을 백업한 뒤에 이동
    - mv -f fname mfname : mfname의 파일이 존재하면 백업 없이 덮어씀
![image](https://user-images.githubusercontent.com/65120581/126274097-bd0f7e9b-7de2-4b9e-81ee-444e277a5bd0.png)

## 07. rm(remove)
- rm은 파일이나 디렉토리를 삭제할 때 사용하는 명령어
    - rm fname : fname을 삭제
    - rm -f fname : fname을 묻지 않고 삭제cat
    - rm -r dir : dir을 삭제
        - 디렉토리는 -r옵션으로 삭제 불가!


## 08. cat(catenate)
- cat fname : fname의 내용을 출력
- cat fname1 fname2 : fname1과 fname2의 내용을 이어서 출력
- cat fname1 fname2 | more : fname1과 fname2를 출력하는데 페이지별로 출력
- cat fname1 fname2 | head : fname1과 fname2를 출력하는데 처음부터 10번째까지만 출력
- cat fname1 fname2 | tail : fname1과 fname2를 출력하는데 끝에서부터 10번째까지만 출력


## 09. redirection('>', '>>')
- redirection은 리눅스 스트림의 방향을 조정하는 명령어
    - 명령 > 파일 : 명령의 결과를 파일로 저장
        - cat fname1 fname2 > fname3 : fname1, fname2를 출력하고 fname3이라는 파일에 저장
    - 명령 >> 파일 : 명령의 결과를 파일에 추가
        - cat fname4 >> fname3 : fnmae3에 fname4의 내용을 추가
    - 명령 < 파일 : 파일의 데이터를 명령에 입력
        - cat < fname1 : fnmae1의 내용을 출력
    - cf) cat < fname1 > fname2 : fname1의 내용을 출력하는 결과물을 fname2에 저장
  
## 10. alias
- alias는 자주 사용하는 명령어를 간단한 명령어로 설정하는 명령어
- 해제 방법 : unalias
    - alias new = 'command' : command를 실행하는 새 명령어 new를 만든다.
        - ex) alias ls = 'ls -l' : ls를 실행하면 -l 옵션을 갖는 ls를 실행
    - alias : 현재 alias 목록을 출력
    - unalias new : new라는 alias를 

![image](https://user-images.githubusercontent.com/65120581/126274636-66594a06-dcad-4a8d-bf4d-b70941c77733.png)

```Linux
$ unalias new
```
