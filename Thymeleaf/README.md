# Thymeleaf 캐시 처리

- 웹 프로젝터에서 /resource/static에 파일의 대부분은 캐시의 영향을 받는다.
```java
@Controller
@RequestMapping("/test")
public class TestController {
  model.addAttribute("ts", Sysetm.currentTimeMillis());
  return "/index.html";
  }
}
```

# Thymeleaf Utility Objects
## 1. Strings
- `${#strings.toSTring(obj)}` : Object obj를 문자열로 반환 (Null-safe)
- `${#strings.isEmpty(str)}` : 문자열이 Empty String인지 체크
- `${#strings.prepend(str, prefix}` : 문자열 앞에 붙이기
## 2. Numbers
- `${#numbers.formatInteger(num,3)}` : 정수형(Integer) 타입으로 변환
## 3. Objects
- `${#objects.setNullSafe(objSet,default)}` : Set이 Null인 경우 기본값 세팅
## 4. Arrays

- `${#arrays.toArray(object)}` : Array 형태로 타입 변환
## 5. Lists

- `${#lists.toList(object)}	` : List 형태로 타입 변환
## 6.Maps
- `${maps.size(map)}` : Map의 사이즈 반환
