# vi/vim  편집기 명령어 정리

> ## [vi 시작 명령어 ](#vi-시작-명령어)
> ## [vi 커서 이동 ](#vi-커서-이동)
> ## [문자, 행, 삽입 명령어 ](#문자-행-삽입-명령어)
> ## [텍스트 변경 명령어 ](#텍스트-변경-명령어)
> ## [텍스트 삭제 명령어 ](#텍스트-삭제-명령어)
> ## [복사 및 이동 명령어 ](#복사-및-이동-명령어)
> ## [행 번호 설정 명령어 ](#행-번호-설정-명령어)
> ## [보관 및 종료 명령어 ](#보관-및-종료-명령어)


## vi 시작 명령어
- vi {파일명}
  - 예시 : `$ vi test.c` 입력한 파일명이 실제 존재 하지 않을 경우 파일을 생성 <br>
![vitest](https://user-images.githubusercontent.com/65120581/126420508-80a04c94-ef4c-47f9-bb1d-4c2892ac0d8d.gif)
- vi + {행번호}{파일명}
  - 예시 : `$ vi -10 test.c`
- vi + /"{검색 문자열}"{파일명}
  - 예시 : `$ vi-/"abc" test.c` 
- vi -r{파일명}
  - 예시 : `$ vi -r test.c` <br>
  ![-r test](https://user-images.githubusercontent.com/65120581/126421548-d939e046-abc1-4f88-9643-82edb0090b8c.gif)

- view {파일명}
  - 예시 : `$ view test.c` <br>
  ![view](https://user-images.githubusercontent.com/65120581/126429240-a7784e80-3f30-4fb4-a2fd-5677317541e6.gif)

## vi 커서 이동
- h (←) : 왼쪽으로 커서 이동
- j (↓) : 아래로 커서 이동
- k (↑) : 위로 커서 이동
- l (→) : 오른쪽으로 커서 이동
- w : 오른쪽 한 단어의 끝부분으로 커서 이동 <br>
 ![w push](https://user-images.githubusercontent.com/65120581/126423520-d182b4fd-4172-469b-a385-6c3cb2665c7c.gif)

- e : 오른쪽 한 단어의 앞 부분으로 커서 이동 <br>
  ![e push](https://user-images.githubusercontent.com/65120581/126423674-622b1020-9e3a-4c3c-bf07-5603e19e47f9.gif)

- b : 왼쪽 한 단어의 앞 부분으로 커서 이동<br>
  ![b push](https://user-images.githubusercontent.com/65120581/126423698-59893aac-6970-4c18-ac93-4dcafd14f281.gif)

- Enter : 한 행 아래로 커서 이동
- Back space : 한 문자 왼쪽으로 커서 이동
- Space bar : 한 문자 오른쪽으로 커서 이동
- ^ : 행의 맨 왼쪽으로 커서 이동
- $ : 행의 맨 오른쪽으로 커서 이동
- H : 화면의 맨 위로 이동
- M : 화면의 중간으로 이동
- L : 화면의 맨 아래로 이동
- 숫자G : 숫자의 line number로 이동 ex) `5G 10G 20G 30G` <br>
![5G10G20G30G](https://user-images.githubusercontent.com/65120581/126429851-32e2bd55-344a-4499-883c-cbeb7bd4a65a.gif)

## 문자, 행, 삽입 명령어
- a : 커서 오른쪽에 문자 삽입
- A : 커서 오른쪽, 행의 끝에 문자 삽입
- i : 커서 왼쪽에 문자 삽입
- I : 커서 왼쪽, 행의 처음에 문자 삽입
- o : 커서 아래에 행 삽입
- O : 커서 위에 행 삽입
- ESC : 종료

## 텍스트 변경 명령어
- cw : 단어 변경
- cc : 행 변경
- C : 커서 오른쪽의 행 변경
- s : 커서가 위치한 문자열 대체
- S : 커서가 위치한 라인의 문자열 대체
- r : 커서 위치 문자를 다른 문자로 대체
- r-Enter : 행 분리
- J : 현재 행과 아래 행 결합
- xp : 커서 위치 문자와 오른쪽 문자 교환
- ~ : 문자형(대, 소문자) 변경
- u : 이전 명령 취소
- U : 행 변경 사항 취소, 이전의 최종 행 취소
- . : 이전 최종 명령 반복

## 텍스트 삭제 명령어
- x : 커서가 있는 문자 삭제 <br>
![xxxx push](https://user-images.githubusercontent.com/65120581/126436482-0dcb4137-02ac-475f-a97e-26d54737c430.gif)

- nx : 커서가 있는 위치부터 n개의 문자를 삭제 ex) `8x` <br>
![8x push](https://user-images.githubusercontent.com/65120581/126436518-97a8dff9-b52c-4d11-9fe4-73030a7ca163.gif)

- dw : 현재 커서에 있는 한 단어 삭제 <br>
![dw push](https://user-images.githubusercontent.com/65120581/126436538-81cba770-04fe-49dc-9784-447590b427c0.gif)

- dd : 커서가 있는 라인 삭제
- ndd : 커서가 있는 라인부터 n개의 라인 삭제 `10dd` <br>
![10dd push](https://user-images.githubusercontent.com/65120581/126436566-d6523a42-dc62-4297-837b-78be16ebb6ef.gif)

- db : 커서의 위치에서 거꾸로 한 단어 삭제 <br>
![db push](https://user-images.githubusercontent.com/65120581/126436593-23344a8d-1c58-4898-83c9-e48435ee451b.gif)

- D : 커서 오른쪽 행 삭제 <br>
![D push](https://user-images.githubusercontent.com/65120581/126436610-18883ba6-3eca-469f-a8e4-ea5c5af09730.gif)

- `:5,10d` : 5~10번째 행 삭제 <br>
![5,10d](https://user-images.githubusercontent.com/65120581/126436776-bf4f32c1-b6f2-486f-82bc-46c253b795c1.gif)


## 복사 및 이동 명령어
- yy : 커서가 위치한 줄 복사
- y : 행 yank 또는 복사
- yh : 커서의 왼쪽 문자 복사
- yl : 커서에 위치한 문자 복사
- yi : 커서가 위치한 줄과 그 아랫줄 복사
- yk : 커서가 위치한 줄과 그 윗줄 복사
- p :  yank 되거나 삭제된 행 현재 행 위로 삽입
- `:1,2 co 3` : 1~2행을 3행 다음으로 복사
- `:4,5 m 6` : 4~5행을 6행 위로 이동

## 행 번호 설정 명령어
- `:set nu` 또는 :set number
- `:set nonu`

## 보관 및 종료 명령어
- `:w` : 변경사항 저장
- `:w{파일명}` : 변경사항 입력한 파일명으로 저장
- `:wq` : 변경사항 보관 후 vi 종료. ZZ명령과 동일
- ZZ : 변경사항 보관후 vi 종료
- `:q!` : 변경사항 보관하지 않고 종료
- q : 수정한 파일을 저장하지 않고 vi 종료
- e! : 수정한 것을 무시하고 다시 

- 한글 키보드 단축키
![image](https://user-images.githubusercontent.com/65120581/126417613-ae184270-b729-499c-82fc-dc4523c25d05.png)

- 이동에 편리한 단축키
![image](https://user-images.githubusercontent.com/65120581/126417495-5da5a26e-45a5-410c-af6a-09325fc53233.png)
