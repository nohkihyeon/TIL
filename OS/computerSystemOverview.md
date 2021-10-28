<img width="1058" alt="스크린샷 2021-10-29 오전 12 40 16" src="https://user-images.githubusercontent.com/65120581/139289540-74426a79-8bbf-45ae-848b-eb123ecf55b4.png">

# 컴퓨터 시스템 개요

## 프로세서
- 컴퓨터의 두뇌(중앙처리장치)
- 컴퓨터으 모드 장치의 동작 제어
<img width="930" alt="스크린샷 2021-10-29 오전 12 41 11" src="https://user-images.githubusercontent.com/65120581/139289739-501aae38-73af-44c0-9d12-46c43f5f3982.png">

## 레지스터
- 프로세서 내부에 있는 메모리
  - 프로세석 사용할 데잍 저장
  - 컴퓨턴에 가장 빠른 메모리
- 레지스터의 종류
  - 용도 따른 분류
    - 전용 / 범용
  - 사용자가 정보 변경 가능 여붕 따른 분류
    - 사용자 가시 / 사용자 불가시
  - 저장 하는 정보의 종류 따른 분류
    - 데이터 / 주소 / 상태

## 프로세서의 동작
<img width="795" alt="스크린샷 2021-10-29 오전 12 43 23" src="https://user-images.githubusercontent.com/65120581/139290103-c404d887-4800-40ff-a48e-13cd25d96e4b.png">


## 메모리
- 데이터를 저장하느 장치 (기억장치)
- 메모리의 종류
<img width="1105" alt="스크린샷 2021-10-29 오전 12 44 04" src="https://user-images.githubusercontent.com/65120581/139290208-f497d6b4-527b-4b9b-9ec2-c7a6d762e749.png">
  - 주기억장치(Main memory) : 프로세서가 수행할 프로그램고 데이터 저장 / 디스크 입출력 병목현상(I/O bottleneck) 해소

<img width="1060" alt="스크린샷 2021-10-29 오전 12 45 04" src="https://user-images.githubusercontent.com/65120581/139290397-3a306091-f050-48a5-9c72-3896aa4014c4.png">


## 메모리의 종류

- 캐시 : 프로세서 내부에 있는 메모리 (L1, L2 캐시 등)
  - 속독 빠르고 가격이 비쌈
- 메인 메모리의 입출려 병목현상 해소

## 캐시의 동작
- 일반적으로 HW적으로 관리 됨
- 캐시 히트 : 필요한 데이터 블록이 캐시 존재
- 캐시 미스 : 필요한 데이터 블록이 없는 경우
<img width="418" alt="스크린샷 2021-10-29 오전 12 50 10" src="https://user-images.githubusercontent.com/65120581/139291212-ab40ee0b-4707-4821-b2cc-4a8067855058.png">

## 지역성 (Locality)
- 공간적 지역성 (Spatial locality)
  - 참조한 주소와 인접한 주소를 참조하는 특성
  - 예) 순차적 프로그램 수행
- 시간적 지역성 (Temporal locality)
  - 한 번 참조한 주소를 곧 다시 참조하는 특성
  - 예) For문 등의 순환 문
- 지역성은 캐시 적중률과 밀접
  - 알고리즘 성능 향상 위한 중요한 요소 중 하나

<img width="1076" alt="스크린샷 2021-10-29 오전 12 52 50" src="https://user-images.githubusercontent.com/65120581/139291625-6af6ee1c-dd36-492b-9289-a0fe1008ac7c.png">

## 메모리의 종류
- 보조기억 장치
  - 프로그램과 데이터를 저장
  - 프로세서가 직접 접근할 수없음
  - ㅛㅇ량이 크고 가격이 저렴


## 메모리와 운영체제
- 메모리 할당 및 관리
  - 프로그램의 요청에 따른 메모리 할당 및 회수
  - 할당된 메모리 관리
```c
int main(void)
{
char *string;
// Allocate space for a path name
string = malloc( _MAX_PATH);
...
}
```

- 가상메모리 관리
  - 가상 메모리 생성 및 관리
  - 논리주소 -> 물리주소 변환


## System Bus
- 하드웨어들이 데이터 및 신호를 주고 받는 물리적인 통로
 <img width="462" alt="스크린샷 2021-10-29 오전 12 55 56" src="https://user-images.githubusercontent.com/65120581/139292135-6868fbdd-7de9-40d0-afc1-8208ad47ebbf.png">
<img width="819" alt="스크린샷 2021-10-29 오전 12 56 13" src="https://user-images.githubusercontent.com/65120581/139292165-22658ce5-11d0-4037-aa99-68d54eff18dc.png">


## 주변 장치
- 프로세서와 메모리를 제외 하드웨어들
  - 입력 장치
  - 출력 장치
  - 저장 장치

## 주변장치와 운영체제
- 장치 드라이버 관리  
  - 주변 장치 사용을 위한 인터페이스 제공
- 인터럽트 처리
  - 주변 장치의 요청 처리
- 파일 디스크 관리
  - 파일 생성 및 삭제
  - 디스크 공간 관리 


  

