# Database

## Key
### 1. Candidate Key
- Tuple을 유일하 식별하 위해 사용한 속성들으 부분집하 (기본키로 사용할 수 있느 속성들)
- 2가지 만족 조건
  - 유일성 : Key로 하나의 Tuple을 유일하게 식별할 수 있음
  - 최소성 : 꼭 필요한 속성으로만 구성
### 2. Primary key
- 후보키 중 선택한 Main Key
  - Null 값을 가질 수 없음
  - 동일한 값이 중복될 수 없음
### 3. Alternate Key
- 후보키 중 기본키를 제외한 나머지 키 = 보조키
### 4. Super Key
- 유일성은 만족하지만, 최소성은 만족하지 못하는 키
### 5. Foreign Key
- 다른 릴레이션의 기본키를 그대로 참조하는 속성의 집합

## SQL JOIN
- 조인이란?
  - 두개 이상의 테이블이나 데이터베이스를 연결하여 데이터를 검색하는 방법
  - 테이블을 연결하려면 적어도 하나의 칼럼을 서로 공유하고 있어야 하므로 이를 이용하여 데이터 검색에 활용한다.

- 조인의 종류
  - INNER JOIN
    - 교집합으로 기준 테이블과 join 테이블의 중복된 값을 반환
    ```sql
    Select
    A.name, B.age
    from table A
    innter join join_table B on a.NO_EMP = B.NO_EMP
    ```
  - LEFT OUTER JOIN
    - 기존 테이블값과 조인테이블과 중복된 값을 보여준다. 왼쪽 테이블을 기준으로 join
    ```sql
    Select
    A.name, B.age
    from table A
    left outer join join_table B on a.NO_EMP = B.NO_EMP
    ```
  - RIGHT OUTER JOIN
    - left와 반대로 오른쪽 테이블 기준으로 join
    ```sql
    Select
    A.name, B.age
    from table A
    right outer join join_table B on a.NO_EMP = B.NO_EMP
    ```
  - FULL OUTER JOIN
    - 합집합을 의미 A, B 테이블의 모든 데이터가 검색
    ```sql
    Select
    A.name, B.age
    from table A
    full outer join join_table B on a.NO_EMP = B.NO_EMP
    ```
  - CROSS JOIN
    - 모든 경우의 수를 전부 
    ```sql
    Select
    A.name, B.age
    from table A
    cross join join_table B on a.NO_EMP = B.NO_EMP
    ```
  - SELF JOIN
    - 자기 자신과 자기 자신을 조인하는 것이다. 하나의 테이블을 여러번 복사해서 조인한다
    - 자신이 갖고 있는 칼럼을 다양하게 변형시켜 활용할 때 자주 사용한다.
    ```sql
    Select
    A.name, B.age
    from table A,  join_table B
    ```
  
  
## SQL Injection
- 해커에 의해 조작된 SQL 쿼리문이 데이터베이스에 그대로 전달되어 비정상적 명령을 실행시키는 공격 기법
- 
  
  
