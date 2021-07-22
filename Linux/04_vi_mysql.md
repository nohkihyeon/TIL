# MySql lib를 리눅스 C에서 사용하기

## 콘솔에서 mysql 접속방법
- 로콜 서버에 접속
  - 명령어 : `mysql -u 계정명 -p`
    - ex) `mysql -u root -p` <br>
    ![image](https://user-images.githubusercontent.com/65120581/126608222-1aa7fd36-0982-492e-b1fe-cf595a56591c.png)

- 원격지에 접속
  - 명령어 : `mysql -h 호스트주소(ip) -P 포트번호 -u 계정명 -p`
    - ex) mysql -h 192.168.2.201 -P 22 -u root -p

- 원격지의 특정 database에 접속
  - 명령어 : `mysql -h 호스트주소(ip) - P 포트번호 -u 계정명 -p 디비이름`
    - ex) mysql -h 192.168.2.201 -P 22 -u root -p userDB 

## mysql.c


## 참조
> ## [나긋한 개발자](https://sacstory.tistory.com/entry/mysql-lib%EB%A5%BC-c%EC%97%90%EC%84%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)
> ## [코드공장](https://code-factory.tistory.com/44)
