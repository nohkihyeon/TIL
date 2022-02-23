1.  상품가격테이블과  상품 재고수량 체크하고 등등  테이블에 반영하여   현재 시점의 가격으로  **특정회원** 이 **특정상품** 을 **원하는 수량** 으로  주문될 수 있도록

주문 생성 Procedure를  생성하시기 바랍니다.

2.  주문시에   재고수량이 부족한 경우에는   주문이 발생할 수 없도록  Exception처리 해주세요.  주문완료시에는 해당 수량만큼  해당상품의 재고를 차감되어 있어야 합니다.

3.  주문번호는 yyyymmdd0001 부터 하루 9999개 주문을 받을수 있는 주문번호를 채번하시기 바랍니다.

4.    바로주문에 의한 한가지 상품주문과  , 장바구니 주문에 의한 N가지 종류의 상품을 다 처리 할 수있는 프로시져를  완성하고,

해당  Procedure를 이용하여   바로주문 호출,  장바구니 주문을  호출 각각 한번씩 호출 하는   껍대기  프로시져를  작성하고, 껍대기 프로시져를 호출하여,   주문이 이루어 질수 있도록  적성해 주시기 바랍니다.

<img width="1000" alt="스크린샷 2022-02-21 오후 10 48 51" src="https://user-images.githubusercontent.com/65120581/154967551-098958fd-3754-491f-9523-9c1537e01c0d.png">


```sql
create or replace PROCEDURE ORDERING_PROC
( p_emp_identity IN pmember.emp_identity%TYPE,
  p_goods_no IN goods.goods_no%TYPE,
  p_quantity IN goods.goods_quantity%TYPE
) 
IS 
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
  -- 동일한 goods_no가 있는지 확인
  BEGIN
  SELECT COUNT(*) INTO cnt FROM goods g WHERE g.goods_no = p_goods_no;
  select count(*) into isMember from pmember where emp_identity = p_emp_identity;
  SELECT goods_quantity INTO leftQuantity FROM goods WHERE goods_no = p_goods_no;
  END;

  -- 주문번호가 없으면 Exception
  IF cnt = 0 THEN RAISE NOT_FOUND_ERROR; END IF;
  -- 회원 아이디 없으면 Exception
  IF isMember = 0 THEN RAISE NOT_FOUND_IDENTITY; END IF;
  -- 주문수량이 부족하면 Exception
  IF p_quantity > leftQuantity THEN RAISE NOT_ENOUGH;
  ELSE
    UPDATE goods SET goods_quantity = goods_quantity-p_quantity WHERE goods_no = p_goods_no;
    BEGIN
      orderNumber := to_char(SYSDATE,'yyyymmdd') || LPAD(ORDER_NUM_SEQ.nextval, 4,'0');
      SELECT goods_name INTO goodName FROM goods WHERE goods_no = p_goods_no;
      SELECT goods_price INTO goodPrice FROM price p WHERE p.goods_no = p_goods_no AND SYSDATE BETWEEN CAST(st_dt AS DATE) AND CAST(ed_dt AS DATE);
    END;
    insert into orders (order_no, emp_identity, or_dt) values(orderNumber, p_emp_identity, SYSDATE);
    insert into orderlist(order_no, order_index, goods_no, quantity, goods_price, goods_name, total_cost) VALUES(orderNumber, Order_Idx_Seq.Nextval, p_goods_no, p_quantity, goodPrice, goodName, p_quantity*goodPrice);
    
  END IF;
    
  EXCEPTION
    WHEN NOT_FOUND_ERROR THEN
      dbms_output.put_line('상품번호가 없음');
    WHEN NOT_ENOUGH THEN
      dbms_output.put_line('수량 부족');
		WHEN NOT_FOUND_IDENTITY THEN
			dbms_output.put_line('회원 아님');
    WHEN OTHERS THEN
      dbms_output.put_line('예외발생');
    
  
END;
```


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
