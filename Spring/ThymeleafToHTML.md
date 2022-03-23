# Spring Boot에서 Thymeleaf를 사용한 HTMLf로 값 전달


## Thymeleaf를 사용할 때 Spring MVC에서 로직을 처리, html로 값을 전달

### Controller
```java
@RequestMapping("/login.do")
private ModelAndView doLogin(@Valid LoginVO loginVO,  
        HttpServletRequest request) throws Exception {

    /*
     * 로그인 처리 SVC 호출
     * */
    String loginRslt = loginSvc.doLogin(loginVO);

    /*
     * 분기
     * Login성공
     * */
    if(loginRslt.equals(StaticStringUtil.USER_LOGIN_SUCCESS)){
        return new ModelAndView("redirect:/moveMain.do");
    }else{
        return new ModelAndView("login/login", "loginErr", loginRslt);
    }

}
```
### 1. input + jquery
```jsp
<input th:value="${loginErr}" type="text" id="loginRslt" hidden="true"/>
```
- javascript
```javascript
$(document).ready(function () {

    if($("#loginRslt").val() == "NE"){
        alert("없는 이메일");
    }else if($("#loginRslt").val() == "PE"){
        alert("틀린 비밀번호");
    }

});
```

### 2. thymeleafdml th:if 속성
```html
<div th:if="${loginErr} == 'NE'"  class="alert alert-danger alert-dismissable text-left" id="emailErr">
   <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
  This email is not registered.
</div>
<div th:if="${loginErr} == 'PE'" class="alert alert-danger alert-dismissable text-left" id="pwdErr">
   <button aria-hidden="true" data-dismiss="alert" class="close" type="button">×</button>
  Invalid password.
</div>
```

### 3. Ajax
```javascript
function dataSend() {
    var data=$("#input").val();
    var messageDTO={
        result:data
    };
    $.ajax({
        url: "/dataSend",
        data: messageDTO,
        type: "post"
    }).done(function(fragment) {
        $("#resultDiv").replaceWith(fragment);
    });
}
```
```java
@Controller
public class MainController {
    @RequestMapping(value = "/dataSend",method = RequestMethod.POST)
    public String dataSend(Model model, MessageDTO dto){
        model.addAttribute("msg",dto.getResult()+"/ this is the value sent by the server ");
        return "index :: #resultDiv";
    }
}

//

@Data
public class MessageDTO {
    private String msg;
    private String Result;
}
```
