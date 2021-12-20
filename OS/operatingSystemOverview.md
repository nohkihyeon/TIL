# 운영체제 개요

## 운영체제의 역할
<img width="1130" alt="스크린샷 2021-10-31 오후 8 03 15" src="https://user-images.githubusercontent.com/65120581/139579754-fd7a97ea-a058-46dd-b6b8-617986e7205a.png">

- User Interface (편리성)
  - CUI (Character User Interface)
  - GUI (Graphical User Interface)
  - EUCI (End-User Comfortable Interface)
- Resource management (효율성)
  - HW resource (processor, memory, I/O devices)
  - SW resource (file, application, message, signal)
- Process and Thread management 

- System management (시스템 보호)

## 컴퓨터 시스템의 구성 
<img width="983" alt="스크린샷 2021-10-31 오후 8 05 48" src="https://user-images.githubusercontent.com/65120581/139579859-d1772753-05c1-4c5a-aaec-34c4351f116e.png">
<img width="989" alt="스크린샷 2021-10-31 오후 8 06 35" src="https://user-images.githubusercontent.com/65120581/139579903-04fcfbe2-f37b-4e93-a966-c1e1d8238459.png">


## 운영체제의 구분
- 동시 사용자 수
  - Single-user system
  - Multi-user system
- 동시 실행 프로세스 수
  - Single-tasking system
  - Multi-tasking system(Multiprogramming system)
- 작업 수행 방식
  - Batch Processing system
  - Time-sharing system
  - Distributed processing system
  - Real-time system

## 동시 사용자 수
- Single-user system
  - 한 명의 사용자만 시스템 사용 가능
    - 한 명의 사용자가 모든 시스템 자원 독점
    - 자원 관리 및 시스템 보호 방식이 간단함
  - 개인용 장비(PC, mobile) 등에 사용
- Multi-user system
  - 동시에 여러 사용자들이 시스템 사용
    - 각종 시스템 자원들에 대한 소유 권한 관리 필요
    - 기본적으로 Multi-tasking 기능 필요
    - os의 기능 및 구조가 복잡
  - 서버, 클러스터 장비 등에 사용
    - Unix, Linux, Windows server 등

## 동시 실행 프로세서 수
- 단일 작업 (Single-tasking system)
  - 시스템 내에 하나의 작업만 존재
    - 하나의 프로그램 실행을 마친 뒤에 다른프로그램의 실행
  - 운영체제의 구조가 간단
  - MS-DOS
- 다중 작업 (Multi-tasking system)
  - 동시에 여러 작업(프로세스)의 수행 가능
    - 작업들 사이의 동시 수행, 동기화 등을 관리해야 함
  - 운영체제의 기능 및 구조가 복잡
  - Unix/Linux, Windows 등

## 작업 수행 방식
- Batch processing system
  - 일괄처리 시스템
- Time-sharing system
  - 시분할 시스템
- Distributed processing system
  - 분산처리 시스템
- Real-time system
  - 실시간 시스템

## 순차 처리 (No OS ~ 1940s)
- 운영체제 개념 존재하지 않음
  - 사용자가 기계어로 직접 프로그램 작성
  - 컴퓨터에 필요한 모든 작업 프로그램에 포함
    - 프로세서에는 명령어 저장 방법, 계산 대상, 결과 저장 위치와 방법, 출력 시점, 위치 등
  - 실행하는 작업 별 순차 처리
    - 각각의 작업에 대한 준비 시간이 소요


## Batch System(1950s~1960s)
- 모든 시스템을 중앙(전자계산소 등)에서 관리 및 운영
- 사용자의 요청 작업(천공카드 등)을 일정 시간 모아 두었다가 한번에 처리
<img width="945" alt="스크린샷 2021-10-31 오후 8 17 02" src="https://user-images.githubusercontent.com/65120581/139580377-9273a1c2-4d63-42fb-be52-42032a2aa73b.png">
- 시스템 지향적
- 장점
  - 많은 사용자가 시스템 자원 공유
  - 처리 효율(throughput) 향상
- 단점
  - 생산성(productivity) 저하
    - 같은 유형의 작업들이 모이기를 기다려야 함
  - 긴 응답시간 (turnaround time)
    - 약 6시간 (작업 제출에서 결과 출력까지의 시간)

## Time Sharing System(1960s~1970s)
<img width="775" alt="스크린샷 2021-10-31 오후 8 19 25" src="https://user-images.githubusercontent.com/65120581/139580448-939e6b5b-b99c-4550-a568-72210f7dc574.png">

- 여러 사용자가 자원을 동시에 사용
  - os가 파일 시스템 및 가상 메모리 관리
- 사용자 지향적
  - 대화형 시스템
  - 단말기 사용
<img width="1082" alt="스크린샷 2021-10-31 오후 8 19 46" src="https://user-images.githubusercontent.com/65120581/139580454-48dd00e2-5d1b-436e-8401-49cd17000e95.png"><br>

- 장점
  - 응답시간(response time) 단축
  - 생산성(productivity) 향상
    - 프로세서 유휴 시간 감소
- 단점
  - 통신 비용 증가
    - 통신선 비용, 보안 문제 등
  - 개인 사용자 체감 속도 저하
    - 동시 사용자수 증가 -> 시스템 부하 증가 -> 느려짐(개인관점)


## Personal Computing
- 개인이 시스템 전체 독점
- CPU 활용률(utilization)이 고려의 대상이 아님
- OS가 상대적으로 단순함
  - 다양한 사용자 지원 기능 지원
- 장점
  - 빠른 응답시간
- 단점
  - 성능이 낮음

## Parallel Processing System
- 단일 시스템 내에서 둘 이상의 프로세서 사용
  - 동시에 둘 이상의 프로세스 지원
- 메모리 등의 자원 공유
- 사용 목적
  - 성능 향상
  - 신뢰성 향상 (하나가 고장나도 정상 동작 가능)
- 프로세서 간 관계 및 역할 관리 필요
![스크린샷 2021-10-31 오후 8 30 08](https://user-images.githubusercontent.com/65120581/139580888-e3bce792-a3d9-4a56-928a-4df219ac835c.png)


## Distributed Processing System
- 네트워크를 기반으로 구축된 병렬처리 시스템 (Loosely-coupled system)
<img width="864" alt="스크린샷 2021-10-31 오후 8 32 01" src="https://user-images.githubusercontent.com/65120581/139580987-3f7beceb-9f7f-4cee-a598-95f29229c3fc.png">
- 물리적인 분산, 통신망 이용한 상호 연결
- 각각 운영체제 탑재한 다수의 범용 시스템으로 구성
- 사용자는 분산운영체제를 통해 하나의 프록르ㅐㅁ, 자원처럼 사용 가능(은폐성, transparency)
- 각 구성 요소들간의 독립성유지, 공동 작업 가능
- Cluster system, client-server system. P2P 등
- 장점
  - 자원 공유를 통한 높은 성능
  - 고신뢰성, 높은 확장성
- 단점
  - 구축 및 관리가 어려움


## Real-time Systems
- 작업 처리에 제한 시간(deadline)을 갖는 시스템
  - 제한 시간 내에 서비스를 제공하는 것이 자원 활용 효율보다 중요
- 작업의 종류
  - Hard real-time task
    - 시간 제약을 지키지 못하는 경우 시스템에 치명적 영향
    - 예) 발전소 제어, 무기 제어 등
  - Soft real-time task
    - 동영상 재생 등
  - Non real-time task


# 운영체제의 구조

- 커널(Kernel)
  - OS의 핵심 부분(메모리 상주)
    - 가장 빈번하게 사용되는 기능들 담당
    - 시스템 관리(processor, memory) 등
  - 동의어
    - 핵, 관리자 프로그램, 상주 프로그램, 제어 프로그램 등
- 유틸리티(Utility)
  - 비상주 프로그램
  - UI등 서비스 프로그램
<img width="655" alt="스크린샷 2021-10-31 오후 8 37 10" src="https://user-images.githubusercontent.com/65120581/139581171-4c36d6ad-36d0-4aaa-9b1f-acc678c0e6fd.png">

- Major functions of Kernel
  - Resource Mgmt
    - Hardware resource management
      - Processor, memory, I/O devices, etc.
    - software resource management
      - files, messages, processes, etc.

## 단일 구조
<img width="1111" alt="스크린샷 2021-10-31 오후 8 38 41" src="https://user-images.githubusercontent.com/65120581/139581212-0bcecf23-8d90-4cd3-b3a8-7fc610e51c50.png">

- 장점
  - 커널 내 모듈간 직접 통신
    - 효율적 자원 관리 및 사용
- 단점
  - 커널의 거대화
    - 오류 및 버그, 추가 기능 구현 등 유지보수가 어려움
    - 동일 메모리에 모든 기능이 있어, 한 모듈의 문제가 전체 시스템에 영향(예 : 악성코드)
<img width="703" alt="스크린샷 2021-10-31 오후 9 05 22" src="https://user-images.githubusercontent.com/65120581/139582248-3cf4155e-643b-4f53-b9eb-7172fd9422bc.png">

## 마이크로 커널 구조
- 커널의 크기 최소화
  - 필수 기능만 포함
  - 기타 기능은 사용자 영역에서 수행
<img width="782" alt="스크린샷 2021-10-31 오후 9 06 06" src="https://user-images.githubusercontent.com/65120581/139582284-b8aefc50-faa7-43b8-ba37-89ad6b5d022e.png">


# 운영체제의 기능
## Process Management
- 프로세스 (Process)
  - 커널에 등록된 실행 단위 (실행 중인 프로그램)
  - 사용자 요청/프로그램의 수행 주체(entity)
- OS의 프로세스 관리 기능
  - 생성/삭제, 상태관리
  - 자원 할당
  - 프로세스 간 통신 및 동기화(synchronization)
  - 교착 상태(deadlock) 해결
- 프로세스 정보 관리
  - PCB(Process Control Block)

## Processor Management
- 중앙 처리 장치 (CPU)
  - 프로그램을 실행하는 핵심 자원
- 프로세스 스케줄링 (Scheduling)
  - 시스템 내의 프로세스 처리 순서 결정
- 프로세서 할당 관리
  - 프로세서들에 대한 프로세서 할당
    - 한 번에 하나의 프로세스만 사용 가능


## Memory Management
- 주기억장치
  - 작업을 위한 프로그램 및 데이터를 올려 놓는 공간
- Multi-user, Multi-tasking 시스템
  - 프로세스에 대한 메모리 할당 및 회수
  - 메모리 여유공간 관리
  - 각 프로세스의 할당 메모리 영역 접근 보호
- 메모리 할당 방법(Scheme)
  - 전체 적재
    - 장점 : 구현이 간단 / 단점 : 제한적 공간
  - 일부 적재 (virtual memory concept)
    - 프로그램 및 데이터의 일부만 적재
    - 장점 : 메모리의 효율적 활용 / 단점 : 보조기억 장치 접근 필요

## File Management
- 파일 : 논리적 데이터 저장 단위
- 사용자 및 시스템의 파일 관리
- 디렉토리(directory) 구조 지원
- 파일 관리 기능
  - 파일 및 디렉토리 생성/삭제
  - 파일 접근 및 조작
  - 파일을 물리적 저장 곤간으로 사상(mapping)
  - 백업 등

## I/O Management
- 입출력(I/O) 과정
  - os를 반드시 거쳐야 함
<img width="817" alt="스크린샷 2021-10-31 오후 9 14 59" src="https://user-images.githubusercontent.com/65120581/139582747-0bfa1f29-68ef-4812-8e63-e3ea6e757daf.png">
<img width="949" alt="스크린샷 2021-10-31 오후 9 15 09" src="https://user-images.githubusercontent.com/65120581/139582763-a7041a25-f13a-495c-88e7-e1925b5a57d5.png">



