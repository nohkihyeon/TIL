## 1. 각자 academy 계정에서 Employee , code_dtl table을 이용하여 전체 직원에 대하여 아래와 같이 조회될수 있게 sql문을 작성하세요. ( 직급코드 com_code_id = ‘E001’)

---

사번     이름       직급   직급내사번순위

---

11      강길동     사원        1

22      강길동2     사원        2

33      홍길동      대리       1

55      홍길동2     대리      3

44      홍길동3     대리      2

```sql
select e.emp_no "사번",
       e.emp_name "이름",
       position.code_name "직급",
       row_number() over(partition by e.job_code order by e.emp_no) "직급내사번순위"
  from employee e,
       (select * from code_dtl a where a.comm_code_id = 'E001') position
 where position.code_id = e.job_code
 order by position.code_id, e.emp_name
```

## 2. 각자 academy 계정에서 Employee, org_mst , code_dtl table을 이용하여 전체 직원에 대하여 아래와 같이 조회될수 있게 sql문을 작성하세요.

사번     이름       부서                직급            직급내사번순위

---

11      강길동      CF사업부          사원                  1

22      강길동2     CF사업부          사원                 2

33      홍길동      SC사업부          사원                  1

55      홍길동2     SC사업부          대리                  2

44      홍길동3     SC사업부          대리                 1

```sql
select e.emp_no "사번",
       e.emp_name "이름",
       o.team_name "부서",
       position.code_name "직급",
       row_number() over(partition by e.job_code, o.team_name order by e.emp_no) "직급내사번순위"
  from employee e,
       org_mst o,
       (select * from code_dtl a where a.comm_code_id = 'E001') position
 where position.code_id = e.job_code
   and o.team_id = e.base_team_id
 order by o.team_name, position.code_id, e.emp_name
```

## 3. **다음은 최근 5일간 주문 건수 이다.**

<img width="676" alt="스크린샷 2022-02-26 오후 6 27 23" src="https://user-images.githubusercontent.com/65120581/155837861-b29c0ad6-0ae3-4d57-9d94-281fd35f22a3.png">


- **증감표시 문자 ▲ ▼**
- **참고 SQL**

```sql
with ord_view as (
select '2022-02-01' dt, 1000 ord_cnt from dual union
select '2022-02-02' dt, 966  ord_cnt from dual union
select '2022-02-03' dt, 600  ord_cnt from dual union
select '2022-02-04' dt, 600  ord_cnt from dual union
select '2022-02-05' dt, 1005 ord_cnt from dual
)
select dt,
       ord_cnt,
       case
         when ord_cnt - lag(ord_cnt, 1) over(order by dt) > 0 then
          '▲'
         when ord_cnt - lag(ord_cnt, 1) over(order by dt) < 0 then
          '▼'
         else
          '-'
       end as "증감",
       rank() over(order by ord_cnt desc) "순위",
       sum(ord_cnt) over(order by dt) "누적주문수"
  from ord_view
 order by dt
```
