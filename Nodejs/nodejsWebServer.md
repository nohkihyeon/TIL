# Node.js로 웹서버 만들기

<img width="1103" alt="스크린샷 2021-11-25 오후 8 33 52" src="https://user-images.githubusercontent.com/65120581/143434391-697a1fcd-bae9-4152-a2a0-90e1f3568653.png">

- apache와 마찬가지로 Node.js도 이용 가능

```javascript
var http = require('http');
var fs = require('fs');
var app = http.createServer(function(request,response){
    var url = request.url;
    if(request.url == '/'){
      url = '/index.html';
    }
    if(request.url == '/favicon.ico'){
      return response.writeHead(404);
    }
    response.writeHead(200);
    console.log(__dirname + url);
    response.end(fs.readFileSync(__dirname + url));
 
});
app.listen(3000);
```

## console
<img width="442" alt="스크린샷 2021-11-25 오후 8 39 56" src="https://user-images.githubusercontent.com/65120581/143435136-98a761fb-5cad-41a2-b17e-fd4e7c7eab8e.png">

- 사용자에게 전달하는 메세지가 달라지는 것을 알 수 있다.
