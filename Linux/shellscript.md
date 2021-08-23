# 1. shellscript
쉘 스크립트는 쉘에게 무슨 명령들을 실행할지 알려주는 스크립트 파일이다.
- bash 쉘을 사용하는 스크립트
```shell
#!/bin/bash    
echo "hello, world"
printf "hello, world"
```
# 2. #!/bin/sh 의미
- #!/bin/sh 스크립트 파일을 bash쉘로 실행
- #!/bin/bash 같은 의미
- 명령어 해석기가 bash shell임을 미리 알려주는 것
- 리눅스 환경에서는 보통 `#!/bin/sh`를 사용
```vi
ls -al /bin/sh
```
![image](https://user-images.githubusercontent.com/65120581/130381085-808cce19-fee8-4a0f-8162-7a58fcf8486b.png)


# 3. shell script를 사용하는 이유
- 반복되는 작업을 자동화하기 위해 스크립트 형태로 미리 틀을 만들어 놓고 실행
- 스케줄링을 돌려서 파일만 실행하도록 할수도 있으며, 스크립트의 내용을 몰라도 누구나 실행 가능 <BR>
![image](https://user-images.githubusercontent.com/65120581/130382196-29b8f73c-d4ba-4b81-b76a-240d3676889e.png)

## .cfg
.cfg 또는 .config 파일은 다양한 프로그램에서 각자의 소프트웨어에 특정한 설정을 저장하는 데 사용되는 구성 파일
## .dat
.dat 파일 확장자를 가진 파일은 파일을 만든 프로그램과 관련된 특정 정보를 저장하는 일반 데이터 파일


```shell
#!/bin/sh
echo "hello World"

```

# 4. 간단한 쉘 스크립트 실행
- `$ ./shell.sh`실행 시 <br>
![image](https://user-images.githubusercontent.com/65120581/130388871-0a31bc04-539b-4c55-876b-6534f972839f.png)
- 실행 권한이 없기 때문에 허가 거부 <br>
![image](https://user-images.githubusercontent.com/65120581/130388949-5fa1bf1d-a0cd-4dff-a431-47e5e53ef650.png)
- `$ chmod 755 shell.sh` 실행권한을 부여 <br>
![image](https://user-images.githubusercontent.com/65120581/130389019-c5be063a-84df-4bc0-8e5b-fd2917a5f69f.png)
- 실행 명령어
  - ./shell.sh
  - sh shell.sh
  - bash shell.sh  <br>
![image](https://user-images.githubusercontent.com/65120581/130390197-319ae40d-6bb9-413b-a85d-645ae7998d1f.png)


