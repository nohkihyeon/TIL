# fecch
- 네트워크 요청을 보내고 새로운 정보르 받아오는 일을 할 수 있다.
- 주문전송, 사용자정보읽기, 서버에 최신 변경분 가져오기

```javascript
let promise = fetch(url, [options])
```
- url : 접근하고자 하는 URL
- options : 선택 매개변수, Method, header 등으 지정할 수 있음

```html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <script>
        async function getUsers(names) {
            let jobs = [];

            for (let name of names) {
                let job = fetch(`https://github.com/nohkihyeon/${name}`).then(
                    successResponse => {
                        if (successResponse.status != 200) {
                            return null;
                        } else {
                            return successResponse.json();
                        }
                    },
                    failResponse => {
                        return null;
                    }
                );
                jobs.push(job);
            }

            let results = await Promise.all(jobs);

            return results;
        }

        alert(getUsers('nohkihyeon'));
    </script>
</body>

</html>
```
