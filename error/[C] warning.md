# warning :  implicit declaration of function ‘close’ 
- close(c_socket);
## 해결방법
```c
#include <unistd>  // for close
```
- #include <unistd> 추가해준다.

 # warning : control reaches end of non-void function
  - 반환형이 int이지만 return이 없으면 나오는 warning
  ```c
  int main(void){
     ...
     return 0;    // 추가 
  }
  ```
  - return 0 또는
  - return NULL 추가
  
  
  #  warning: implicit declaration of function ‘inet_addr’
  - 헤더에 추가
  ```c
  #include <arpa/inet.h>           // inet_addr
  ```
