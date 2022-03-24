# ResultMap
- mybatis에서 가장 중요하고 강력한 요소
- DB에서 데이터를 가져올 때 작성되는 JDBC 코드의 대부분을 줄여주는 역할

```jsp
<select id="selectUsers" resultMap="UserMap">
  select user_id, user_name, user_pw
  from users
  where id=#{id}
</select>

```

## 일반적인 Mybatis의 매핑 구문
```jsp
<select id="selectUsers" resultType="map">
  select id, username, hashedPassword
  from some_table
  where id = #{id}
</select>
```
- resultType이 map -> {key, value} 매핑
  - {"id", id값}
  - {"username", username값}
  - {"hashedPassword", hashedPassword값}

## resultType을 User 객체로 바꾸어주기
```java
@Getter
@Setter
public class User {
  private int id;
  private String username;
  private String hashedPassword;
}
```
```jsp
<select>
  select id, username, hashedPassword
  from some_table
  where id = #{id}
</select>
```
- User 객체의 필드명과 selectUsers의 조회 칼럼명이 일치하므로 쉽게 매핑

## Result Map 적용
```jsp
<resultMap id="testMap" type="User객체경로.User"> 
  <result column="user_id" property="id" jdbcType="NVARCHAR" javaType="String"/> 
  <result column="user_name" property="username" jdbcType="NVARCHAR" javaType="String"/> 
  <result column="hashed_password" property="hashedPassword" jdbcType="NVARCHAR" javaType="String"/>
  </resultMap> 
  
<select id="selectUsers" resultType="testMap"> 
  select user_id, user_name, hashed_password 
  from some_table where id = #{id} 
</select>

```
