# SQL의 종류
- SQL은 관계형 데이터베이스에 대해서 데이터의 구조를 정의, 데이터 조작, 데이터 제어 등을 할 수 있는 절차형 언어
## DDL
### 관계형 데이터베이스 테이블을 생성, 변경, 삭제하는 것으로 데이터를 저장할 구조를 정의하는 언어
- CREATE
- ALTER
- DROP
- TRUNCATE

## DML
### 데이터 구조가 DDL로 정의되면 테이블에 데이터를 입력, 수정, 삭제, 조회한다.
- SELECT
- INSERT
- UPDATE
- DELETE

## DCL
### 데이터베이스 사용자에게 권한을 부여하거나 회수
- GRANT
- REVOKE

## TCL
### 트랜잭션을 제어하는 명령어
- COMMIT
- ROLLBACK

# MySQL

- MYSQL은 전세계적으로 가장 널리 사용되고 있는 오픈 소스 데이터베이스
- 표준 데이터베이스 질의언어 SQL을 사용하는 개방 소스의 관계형 데이터베이스 관리 시스템
- RDBMS, 매우 빠르고 유연하며 사용하기 쉬움
- 다중 사용자, 다중 쓰레드를 지원 C, C++, Eiffel, 자바, 펄, PHP, Pyton 스크립트 등을 위한 응용프로그램 인터페이스(API)를 제공
- 유닉스 리눅스, Windows 운영체제 사용 가능

# MySQL과 mariaDB 차이점

## mariaDB
![image](https://user-images.githubusercontent.com/65120581/128284078-68485ddd-b815-4830-88f2-d90f86dd4425.png)
- mysql을 기반으로 fork하여 자체적으로 개발하는 서비스

## mariaDB MySQL 공통점
- MariaDB의 실행 프로그램들과 유틸리티는 모두 MySQL과 이름이 동일하며 호환
- MySQL 5.x의 데이터 파일과 테이블 정의 파일(.FRM)은 MariaDB 5.x와 호환
- MySQL Connector(Java 및 C 클라이언트 라이브러리 등)는 모두 MariaDB에서 변경없이 사용 가능
- MySQL 클라이언트 프로그램은 그대로 MariaDB 서버의 연결에 사용할 수 있다.

## 
