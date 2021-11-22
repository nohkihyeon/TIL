# 호스트와 컨테이너의 파일시스템 연결

<img width="1109" alt="스크린샷 2021-11-22 오후 4 39 40" src="https://user-images.githubusercontent.com/65120581/142821853-8e45cdfa-d39f-4958-888f-fa48761bd19f.png">

- 컨테이너가 사라진다면 어렵게 작업한 내용이 죽는다...


<img width="1106" alt="스크린샷 2021-11-22 오후 4 42 01" src="https://user-images.githubusercontent.com/65120581/142822105-c73cd40f-af6e-415a-8700-cb44a4fb4dea.png">

- /usr/local/apache2/htdocs/와 /Desktop/htdocs와 연결된다면 Host쪽에서 수정된 것이 Container의 File System에 반영될 수 있다면 
- 컨테이너가 날라가도 남아있기때문에 보다 안정적으로 진행가능


<img width="946" alt="스크린샷 2021-11-22 오후 4 52 15" src="https://user-images.githubusercontent.com/65120581/142823299-ad5530a0-1c85-407b-8486-6551419b899d.png">


## DESK/htdocs html 작업
<img width="1208" alt="스크린샷 2021-11-22 오후 4 54 41" src="https://user-images.githubusercontent.com/65120581/142823654-fad98bcc-91d2-4415-90dc-4ea6de24f95a.png">

<img width="1182" alt="스크린샷 2021-11-22 오후 4 57 37" src="https://user-images.githubusercontent.com/65120581/142824086-8d58bc7d-f145-41d1-9790-e70bcb658d8d.png">
