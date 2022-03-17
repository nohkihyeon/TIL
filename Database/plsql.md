```sql
CREATE OR REPLACE TYPE order_object AS OBJECT
(
      p_emp_identity ACADEMY4.pmember.emp_identity%TYPE,
      p_goods_no ACADEMY4.goods.goods_no%TYPE,
      p_quantity ACADEMY4.goods.goods_quantity%TYPE
      
construct (p_emp_identity ACADEMY4.pmember.emp_identity%TYPE,
                        p_goods_no ACADEMY4.goods.goods_no%TYPE,
                        p_quantity ACADEMY4.goods.goods_quantity%TYPE) RETURN r_order_type 
    IS 
        c r_order_type; 
 
    BEGIN 
        c.p_emp_identity := p_emp_identity; 
        c.p_goods_no := p_goods_no; 
        c.p_quantity := p_quantity; 
 
        RETURN c; 
     
    END construct;
);
```


```sql
create or replace noneditionable function fn_get_emp_info_pipe_alias(
   arg_emp_no  in employee.emp_no%TYPE
) return TBL_EMP_INFO_ALIAS pipelined
IS
  v_emp_info_record   TY_EMP_INFO_ALIAS  ;
BEGIN

  v_emp_info_record := TY_EMP_INFO_ALIAS(null);

 /*
    위(2.1) 아래(2.2) 두 가지 방식으로 다 v_emp_info_record에 담을 수 있음.
 /* 2.1 */
 /*
    select  a.emp_no
           ,a.emp_name
           ,a.login_id
     into   v_emp_info_record.emp_no
           ,v_emp_info_record.emp_name
           ,v_emp_info_record.login_id
           ,v_emp_info_record.team_name
     from  employee a,  org_mst b
    where  a.emp_no =  arg_emp_no
       and a.base_team_id = b.team_id ;
    */

 /* 2.2 */
  select  TY_EMP_INFO_ALIAS(emp_no =>  a.emp_no
                      ,emp_name =>     a.emp_name
                      ,login_id =>     a.login_id
                      ,team_name =>    b.team_id
                      )
            /*  아래와 같이 해도 됨.
               TY_EMP_INFO_alias( a.emp_no
                      , a.emp_name
                      ,  a.login_id
                      , b.team_id
                      )*/
     into  v_emp_info_record
     from  employee a,  org_mst b
    where  a.emp_no =  arg_emp_no
      and  a.base_team_id = b.team_id ;

  PIPE ROW(v_emp_info_record);
  return ;

END ;
```
