# INDEX 생성

## 인덱스(index)
- 인덱스는 테이블에서 원하는 데이터를 쉽고 빠르게 찾기 위해서 사용
- 자주 사용되는 필드 값으로 만들어진 원본 테이블의 사본이라고 생각 할 수 있다.
- MySQL은 데이터를 검색할 때 첫 번째 필드부터 차례대로 테이블 전체를 검색한다.
- 테이블이 크다면 검색하는 시간이 증가
- 인덱스를 사용하면 전체를 읽지 않아도 되므로, 검색과 질의에 대한 처리가 빠르게 이루어질 수도 있다.
- 하지만 데이터의 삽입, 삭제, 수정이 원본 테이블에서 이루어질 경우 인덱스도 함께 수정되어야 한다. 따라서 인덱스가 설정된 테이블의 처리 속도가 느려질 수 있다.
- 따라서 인덱스는 수정보다는 검색이 자주 사용되는 테이블에서 사용하는 것이 좋다.

## 인덱스 생성
- CREATE 문을 사용하면 인덱스를 생성할 수 있다.
```MYSQL
CREATE INDEX 인덱스이름
ON 테이블이름 (필드이름1, 필드이름2, ...)
```
- topic 테이블 예제 <br>
![image](https://user-images.githubusercontent.com/65120581/128304561-1f479a38-697f-48ec-8249-bbf8d843eabe.png)
```MYSQL
CREATE INDEX TitleIdx
ON topic(title);
```
![image](https://user-images.githubusercontent.com/65120581/128305451-ae057e09-5348-4843-b86e-e91776bb1b3c.png)

- 인덱스 정보보기
```mysql
SHOW INDEX
FROM 테이블이름
```
![image](https://user-images.githubusercontent.com/65120581/128305506-56f7e59e-942c-4d4b-b966-aff16bfaa8fd.png)
1. Table : 테이블의 이름을 표시함
2. Non_unique : 인덱스가 중복된 값을 저장할 수 있으면 1, 저장할 수 없으면 0을 표시함
3. Key_name : 인덱스의 이름을 표시하며, 인덱스가 해당 테이블의 기본 키라면 PRIMARY로 표시함
4. Seq_in_index : 인덱스에서의 해당 필드의 순서를 표시함
5. Column_name : 해당 필드의 이름을 표시함
6. Collation : 인덱스에서 해당 필드가 정렬되는 방법을 표시함
7. Cardinality : 인덱스에 저장된 유일한 값들의 수를 표시함
8. Sub_part : 인덱스 접두어를 표시함
9. Packed : 키가 압축되는(packed) 방법을 표시함
10. Null : 해당 필드가 NULL을 저장할 수 있으면 YES를 표시하고, 저장할 수 없으면 ''를 표시함
11. Index_type : 인덱스에 사용되는 메소드(method)를 표시함
12. Comment : 해당 필드를 설명하는 것이 아닌 인덱스에 관한 기타 정보를 표시함
13. Index_comment : 인덱스에 관한 모든 기타 정보를 표시함
- UNIQUE INDEX 생성
```MYSQL
CREATE UNIQUE INDEX 인덱스이름
ON 테이블이름(필드이름1, 필드이름2, ...)
```
- 인덱스 정렬
```MYSQL
CREATE INDEX 인덱스이름
ON 테이블이름 (필드이름 DESC)
```
OR
```MYSQL
CREATE INDEX 인덱스이름
ON 테이블이름 (필드이름 ASC)
```
- 인덱스 삭제
```MYSQL
DROP INDEX 인덱스이름
ON 테이블이름
```
![image](https://user-images.githubusercontent.com/65120581/128305661-34468d0b-d6d8-45ac-a104-ab20d314c2a9.png)

