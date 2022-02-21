- `docker run -d -p 8080:8080 -p 1521:1521 jaspeen/oracle-xe-11g`
- `docker ps`
- `docker rename <현재 컨테이너 이름> <새 컨테이너 이름>`
- `docker rename trusting_hugle myoracle`


## 오라크 기본 아이디 비밀번호
- `docker exec -it myoracle sqlplus`
<img width="740" alt="스크린샷 2022-02-21 오후 11 07 33" src="https://user-images.githubusercontent.com/65120581/154970738-a60df0a8-8251-4563-b2e5-7af9af0fd6ac.png">

