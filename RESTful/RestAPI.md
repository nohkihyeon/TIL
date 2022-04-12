# RestAPI

- 하나하나의 글을 topic = Resource
- Collection : http://example.com/topics
- Element : http://example.com/topics/1  http://example.com/topics/rest

## Resource
Create      post
Rread       get
Update      put | patch
Delet       delete
=> method

## 웹브라우저(웹서버, 모바일 앱) ---> 웹서버
![image](https://user-images.githubusercontent.com/65120581/130011356-747224d1-ab45-4e7e-afe8-f82f9eee2631.png)

## Restful
- 설계 규칙을 잘 지켜서 설계된 API를 RESTful한 api
-  https://school.com/grade
```json
{
    "results": [
        {
            "idx": 1,
            "name": "1학년"
        },
        {
            "idx": 2,
            "name": "2학년"
        },
        {
            "idx": 3,
            "name": "3학년"
        }
    ]
}

```
