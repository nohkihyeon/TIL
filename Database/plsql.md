```
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
