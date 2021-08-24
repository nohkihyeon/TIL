#  omitting directory
- `$ cp test1 test1_copy`시 오류 발생 <Br>
![image](https://user-images.githubusercontent.com/65120581/130541999-6cb66730-8ea0-4827-b234-ba2d37792c06.png)
  
# 해결 방법
  
- `$ cp -r test1 test1_copy` <br>
![image](https://user-images.githubusercontent.com/65120581/130542297-b1fb3d0a-21d3-42ba-8ee4-2a83fbfc1e73.png)

  
- `$ cp --help`로 -r 의미 검색 <br>
![image](https://user-images.githubusercontent.com/65120581/130542482-2e200b05-dd7d-4c35-8d52-65f4dab99394.png)
- -r --recursive copy directories recursively

