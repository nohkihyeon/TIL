# html form 요소
- 모드 html form 요소는
```html
<form action="/doSomething.action" method="post">
</form>
```

```html
<form action="/doSomething.action" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" />
    </div>
    <div>
        <label for="mail">E-mail:</label>
        <input type="email" id="mail" />
    </div>
    <div>
        <label for="msg">Message:</label>
        <textarea id="msg"></textarea>
    </div>
</form>
```

- div 요소는 코드르 편리하게 구성하고 스타일링 쉽게 만들어줌


```html
<form action="/doSomething.action" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" />
    </div>
    <div>
        <label for="mail">E-mail:</label>
        <input type="email" id="mail" />
    </div>
    <div>
        <label for="msg">Message:</label>
        <textarea id="msg"></textarea>
    </div>

    <div class="button">
        <button type="submit">Send your message</button>
    </div>
</form>
```
- input 요소 : text마 전송
- button 요소 : 전체 html 컨텐츠를 전송
