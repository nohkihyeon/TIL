## ctags
- 프로그래밍 소스코드의 태그 (전역변수 선언, 함수 정의, 매크로 선언)들의 Database(tags file)를 생성하는 Unix 명령어
- 함수 및 변수의 위치를 쉽게 인식할 수 있는 인덱스를 만드는 유틸리티
- ctags를 이용하면 소스코드 내에서 함수나 변수가 선언된 곳으로 이동할 수 있기 때문에 Vim 및 emacs와 같은 에디터에서 특정 심버을 찾고자 할 때 주로 사용

## ctags의 장점
- ctags를 사용하면 소스 코드를 분석하기가 용이해 지기 때문에, 커널과 같은 큰 프로젝트의 소스를 분석할 때 아주 유용하다.

## ctags 설치
- `$ ctags -help`로 현재 설치 되어있는지 확인 가능 <br>
![image](https://user-images.githubusercontent.com/65120581/126603602-93323701-bd43-4818-9bb1-4ba35bd7c70b.png)
- `$ sudo apt-get install ctags` 로 설치가능

## ctags 생성
- `$ ctags 파일이름` (여러개의 파일 지정도 가능) ex) `$ ctags filename1 filename2 filename3`
- `$ ctags -R` 명령어로 현재 디렉토리의 모든 파일과, 하위 디렉토리의 모든 파일까지 tag를 생성한다.

## ctags 사용법
```
$ vi tags
```

## 단축키를 사용한 이동
- 선언으로 이동
  - Ctrl + ]
  - `:tj` <br>
![Ctrl+](https://user-images.githubusercontent.com/65120581/126605447-98f194f7-9b4d-414d-a02a-c0d8bdc5b877.gif)

- 이전상태로 이동
  - Ctrl + t
  - `:po` <br>
![Ctrl+t](https://user-images.githubusercontent.com/65120581/126605459-a4bb442e-de25-4e5e-a211-81c83466b878.gif)


> ## 출처 [[멍멍멍 블로그]](https://bowbowbow.tistory.com/15#ctags-란) 
