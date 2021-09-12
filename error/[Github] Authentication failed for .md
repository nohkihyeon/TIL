## 문제
fata authenticatio failed for github
```
remote: Invalid username or password.
fatal: Authentication failed for 'https://github.com/nohkihyeon/SingletonLab.git/'
```

## 해결방법
```
git config --system --unset credential.helper
```

## 관리자 권한 실행 
관리자 권한으로 실행하기 위해서는 Mac terminal 기준 <br>
<img width="515" alt="스크린샷 2021-09-12 오후 11 35 16" src="https://user-images.githubusercontent.com/65120581/132991815-fefad578-55a6-4790-b8dd-c317880e98df.png">
- userpassword 입력 후
- rootpassword 설정해주면 된다.
- 관리자 권한 실행 : su - 입력


```
git remote -v
git remote remove origin
git remote add origin git@github.com:user/repo.git
```
