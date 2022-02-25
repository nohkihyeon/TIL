1.  상품가격테이블과  상품 재고수량 체크하고 등등  테이블에 반영하여   현재 시점의 가격으로  **특정회원** 이 **특정상품** 을 **원하는 수량** 으로  주문될 수 있도록

주문 생성 Procedure를  생성하시기 바랍니다.

2.  주문시에   재고수량이 부족한 경우에는   주문이 발생할 수 없도록  Exception처리 해주세요.  주문완료시에는 해당 수량만큼  해당상품의 재고를 차감되어 있어야 합니다.

3.  주문번호는 yyyymmdd0001 부터 하루 9999개 주문을 받을수 있는 주문번호를 채번하시기 바랍니다.

4.    바로주문에 의한 한가지 상품주문과  , 장바구니 주문에 의한 N가지 종류의 상품을 다 처리 할 수있는 프로시져를  완성하고,

해당  Procedure를 이용하여   바로주문 호출,  장바구니 주문을  호출 각각 한번씩 호출 하는   껍대기  프로시져를  작성하고, 껍대기 프로시져를 호출하여,   주문이 이루어 질수 있도록  적성해 주시기 바랍니다.

<img width="1000" alt="스크린샷 2022-02-21 오후 10 48 51" src="https://user-images.githubusercontent.com/65120581/154967551-098958fd-3754-491f-9523-9c1537e01c0d.png">


```sql


CREATE OR REPLACE NONEDITIONABLE PROCEDURE ORDERING_PROC
(   ORDER_ARRAY IN TBL_ORDER_INFO_ALIAS
)

IS
ORDER_EX1 TBL_ORDER_INFO_ALIAS;

cnt NUMBER := 0;
isMember NUMBER := 0;
leftQuantity NUMBER := 0;
orderNumber NUMBER;
goodName VARCHAR2(100);
goodPrice NUMBER;
NOT_FOUND_ERROR EXCEPTION;
NOT_ENOUGH EXCEPTION;
NOT_FOUND_IDENTITY EXCEPTION;

BEGIN
  orderNumber := to_char(SYSDATE,'yyyymmdd') || LPAD(ORDER_NUM_SEQ.nextval, 4,'0');
  

  FOR i IN ORDER_ARRAY.FIRST..ORDER_ARRAY.LAST LOOP 
 
     /* 
     DBMS_OUTPUT.PUT_LINE('Emp id: '||ORDER_ARRAY(i).emp_identity|| 
                         ' item: '||ORDER_ARRAY(i).goods_no|| 
                         ' qauntity: '||ORDER_ARRAY(i).quantity);  
                         */
  BEGIN
  SELECT COUNT(*) INTO cnt FROM goods g WHERE g.goods_no = ORDER_ARRAY(i).goods_no;
  select count(*) into isMember from pmember where emp_identity = ORDER_ARRAY(i).emp_identity;
  SELECT goods_quantity INTO leftQuantity FROM goods WHERE goods_no = ORDER_ARRAY(i).goods_no;
  END;

  -- 주문번호가 없으면 Exception
  IF cnt = 0 THEN RAISE NOT_FOUND_ERROR; EXIT; END IF;
  -- 회원 아이디 없으면 Exception
  IF isMember = 0 THEN RAISE NOT_FOUND_IDENTITY; EXIT; END IF;
  -- 주문수량이 부족하면 Exception
  IF ORDER_ARRAY(i).quantity > leftQuantity THEN RAISE NOT_ENOUGH; EXIT;
  ELSE
    UPDATE goods SET goods_quantity = goods_quantity-ORDER_ARRAY(i).quantity WHERE goods_no = ORDER_ARRAY(i).goods_no;
    BEGIN  
      SELECT goods_name INTO goodName FROM goods WHERE goods_no = ORDER_ARRAY(i).goods_no;
      SELECT goods_price INTO goodPrice FROM price p WHERE p.goods_no = ORDER_ARRAY(i).goods_no AND SYSDATE BETWEEN CAST(st_dt AS DATE) AND CAST(ed_dt AS DATE);
    END;
    insert into orderlist(order_no, order_index, goods_no, quantity, goods_price, goods_name, total_cost) VALUES(orderNumber, Order_Idx_Seq.Nextval, ORDER_ARRAY(i).goods_no, ORDER_ARRAY(i).quantity, goodPrice, goodName, ORDER_ARRAY(i).quantity*goodPrice);
    COMMIT;
  END IF;
                         
    END LOOP;
  insert into orders (order_no, emp_identity, or_dt) values(orderNumber, ORDER_ARRAY(1).emp_identity, SYSDATE);
  COMMIT; 
  -- 동일한 goods_no가 있는지 확인
  

  EXCEPTION
    WHEN NOT_FOUND_ERROR THEN
      dbms_output.put_line('상품번호가 없음');
      ROLLBACK;
    WHEN NOT_ENOUGH THEN
      dbms_output.put_line('수량 부족');
      ROLLBACK;
    WHEN NOT_FOUND_IDENTITY THEN
      dbms_output.put_line('회원 아님');
      ROLLBACK;
    WHEN OTHERS THEN
      dbms_output.put_line('예외발생');
      Dbms_Output.put_line ( DBMS_UTILITY.FORMAT_ERROR_STACK() );
      Dbms_Output.put_line ( DBMS_UTILITY.FORMAT_ERROR_BACKTRACE() );
      ROLLBACK;

END;

COMMIT;

```


```sql
CREATE OR REPLACE NONEDITIONABLE PROCEDURE ORDERING_DATA_PROC
IS
  ORDER_EX1 TBL_ORDER_INFO_ALIAS;
  ORDER_EX2 TBL_ORDER_INFO_ALIAS;

BEGIN
  ORDER_EX1 := TBL_ORDER_INFO_ALIAS();
  ORDER_EX2 := TBL_ORDER_INFO_ALIAS();
  
  DBMS_OUTPUT.put_line('1:' || ORDER_EX1.last);
  ORDER_EX1.extend;
  DBMS_OUTPUT.put_line('2:' || ORDER_EX1.last); /* v_emp_info_lst.last : index  */
  ORDER_EX1(1) := TY_ORDER_INFO_ALIAS(NULL);
  ORDER_EX1(1).emp_identity := 'Jac014';
  ORDER_EX1(1).goods_no := 12;
  ORDER_EX1(1).quantity := 3;

  DBMS_OUTPUT.put_line('3:' || ORDER_EX1.last);
  ORDER_EX1.extend;
  DBMS_OUTPUT.put_line('4???:' || ORDER_EX1.last);
  ORDER_EX1(2) := TY_ORDER_INFO_ALIAS(NULL);
  ORDER_EX1(2).emp_identity := 'Jac014';
  ORDER_EX1(2).goods_no := 13;
  ORDER_EX1(2).quantity := 1;
  
  ORDER_EX2.extend;
  ORDER_EX2(1) := TY_ORDER_INFO_ALIAS(NULL);
  ORDER_EX2(1).emp_identity := 'Geo032';
  ORDER_EX2(1).goods_no := 35;
  ORDER_EX2(1).quantity := 1;
  
    BEGIN
    ORDERING_PROC(ORDER_EX1);
    ORDERING_PROC(ORDER_EX2);
    END; 
  FOR i IN ORDER_EX1.FIRST..ORDER_EX1.LAST LOOP 
 
     DBMS_OUTPUT.PUT_LINE('Emp id: '||ORDER_EX1(i).emp_identity|| 
                         ' item: '||ORDER_EX1(i).goods_no|| 
                         ' qauntity: '||ORDER_EX1(i).quantity);  
                    
    END LOOP; 
END;


BEGIN
  ORDERING_DATA_PROC;
  END;

COMMIT;
ROLLBACK;

```

### 객체 테이블

- 주문 객체

```sql
CREATE OR REPLACE NONEDITIONABLE TYPE TY_ORDER_INFO_ALIAS FORCE AS OBJECT
(
  emp_identity VARCHAR2(100),
  goods_no     NUMBER(10),
  quantity     NUMBER(10),

  CONSTRUCTOR FUNCTION TY_ORDER_INFO_ALIAS(arg_emp_identity VARCHAR2 DEFAULT NULL,
                                           arg_goods_no     NUMBER DEFAULT NULL,
                                           arg_quantity     NUMBER DEFAULT NULL)
    RETURN SELF AS RESULT
)
```

- 테이블

```sql
CREATE OR REPLACE NONEDITIONABLE TYPE TBL_ORDER_INFO_ALIAS
   IS TABLE OF TY_ORDER_INFO_ALIAS
```

- 객체 생성자

```sql
CREATE OR REPLACE NONEDITIONABLE TYPE BODY TY_ORDER_INFO_ALIAS  IS

    CONSTRUCTOR FUNCTION TY_ORDER_INFO_ALIAS(arg_emp_identity VARCHAR2 DEFAULT NULL,
                                           arg_goods_no     NUMBER DEFAULT NULL,
                                           arg_quantity     NUMBER DEFAULT NULL)
    RETURN SELF AS RESULT AS
    BEGIN
        SELF.emp_identity := arg_emp_identity;
        SELF.goods_no := arg_goods_no;
        SELF.quantity := arg_quantity;
        RETURN;
    END;
END;
```
```
