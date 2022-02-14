 # Connection Pool
 ## DBCP

- 웹 컨테이너(WAS)가 실행되면서 DB와 미리 connection을 해놓은 객체들을 pool저장
- 클라이언트 요청 시 connection을 빌려주고, 처리가 끝나면 다시 connection을 반납, pool에 다시 저장하는 방식
    
    ![cp-s1.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e0c56e9a-8779-4455-8c69-66620ce6dd43/cp-s1.png)
    

```java
Connection conn = null;
PreparedStatement  pstmt = null;
ResultSet rs = null;

try {
    sql = "SELECT * FROM T_BOARD"

    // 1. 드라이버 연결 DB 커넥션 객체를 얻음
    connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);

    // 2. 쿼리 수행을 위한 PreparedStatement 객체 생성
    pstmt = conn.createStatement();

    // 3. executeQuery: 쿼리 실행 후
    // ResultSet: DB 레코드 ResultSet에 객체에 담김
    rs = pstmt.executeQuery(sql);
    } catch (Exception e) {
    } finally {
        conn.close();
        pstmt.close();
        rs.close();
    }
}
```

- Java에서 DB에 직접 연결해서 처리하는 경우 JDBC 드라이버를 로드하고 connection 객체를 받아 옴
- 매번 사용자가 요청을 할 때마다 드라이버를 로드하고 커넥션 객체를 생성하여 연결하고 종료하기 때문에 비효율
- 이런 문제를 해결하는 것이 DBCO(Database Connection Pool)

## 커넥션 풀 특징

- WAS가 실행되면서 connection 객체를 미리 pool에 생성
- HTTP 요청에 따라 pool에서 connection 객체를 가져다 쓰고 반환
- 위의 방식은 물리적인 DB connection 부하를 줄이고 연결 관리
- pool에 미리 connection이 생성되어 있기 때문에 connection을 생성하는 데 드는 요청 마다 연결 시간 절약

## JDK 버전과 Commons DBCP 버전

- JDK 버전에 따라서 Connection이나 Statement 같은 JDBC의 인터페이스가 조금씩 다르므로 사용하는 JDK의 버전에 맞게 Commons DBCP 버전을 선택

| Commons DBCP 버전 | JDK 버전 | JDBC 버전 |
| --- | --- | --- |
| Commons DBCP 2 | JDK 7 | JDBC 4.1 |
| Commons DBCP 1.4 | JDK 6 | JDBC 4 |
| Commons DBCP 1.3 | JDK 1.4~1.5 | JDBC 3 |
- Commons DBCP 1.x BasicDataSource 클래스 설정

```java
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"  
    destroy-method="close"
    p:driverClassName="${db.driverClassName }"
    p:url="${db.url}"
    p:username="${db.username}"
    p:password="${db.password}"
    p:maxActive="${db.maxActive}"
    p:maxIdle="${db.maxIdle}"
    p:maxWait="${db.maxWait}"
/>
```

- Commons DBCP 2.x BasicDataSource 클래스 설정

```java
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource"  
    destroy-method="close"
    p:driverClassName="${db.driverClassName }"
    p:url="${db.url}"
    p:username="${db.username}"
    p:password="${db.password}"
    p:maxTotal="${db.maxTotal}"
    p:maxIdle="${db.maxIdle}"
    p:maxWaitMillis="${db.maxWaitMills}""
/>
```

## 커넥션의 개수

### 커넥션 풀의 저장 구조

![helloworld-201508-CommonsDBCP-------1.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9e615e10-d92b-4544-a472-357b8205ee71/helloworld-201508-CommonsDBCP-------1.png)

- 커넥션 생성은 Commons DBCP에서 이루어진다.
- PoolableConnection 타입의 커넥션을 생성하고 생성한 커넥션에 ConnectionEventListener를 등록
- ConnectionEventListener에는 애플리케이션이 사용한 커넥션을 풀로 반환하기 위해 JDBC 드라이버가 호출할 수 있는 콜백 메서드가 존재
- 이렇게 생성된 커넥션은 commons-pool의 addObject() 메서드로 커넥션 풀에 추가
- 내부적으로 현재 시간을 담고 있는 타임스태프와 추가된 커넥션의 레퍼런스를 한쌍으로 하는 ObjectTimestampPair라는 자료구조를 생성
- LIFO 형태의 CursorableLinkedList로 관리

![helloworld-201508-CommonsDBCP-------2.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/23b4e446-41f6-4aea-98c1-d0642adf0308/helloworld-201508-CommonsDBCP-------2.png)

### 커넥션 개수와 관련된 속성은 다음과 같은 조건을 만족시켜야 한다.

- maxActive ≥ initialSize
- maxIdle ≥ minIdle
- maxActive = maxIdle

---

> 참조
> 

[Commons DBCP 이해하기](https://d2.naver.com/helloworld/5102792)

**[[Spring] 커넥션 풀(Connection pool)이란?](https://linked2ev.github.io/spring/2019/08/14/Spring-3-%EC%BB%A4%EB%84%A5%EC%85%98-%ED%92%80%EC%9D%B4%EB%9E%80/)**

[DB Connection Pool에 대한 이야기](https://www.holaxprogramming.com/2013/01/10/devops-how-to-manage-dbcp/)
