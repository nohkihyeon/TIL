# 인터셉터와 필터 차이


## 인터셉터?
- Spring 프레임워크 웹 어플리케이션을 특정 요구사항을 만족하기 위해 수행
  - url 진입 시 로그인 된 사용자가 접근을 해야함
  - url 진입 시 jwt와 같은 토큰을 검사해야 함
  - url의 경우 계저으이 권한에 따라 접근을 막아야 함
- 이런 작업을 하는 기술 : Servlet Filter, Interceptor, AOP

![img_01](https://user-images.githubusercontent.com/65120581/157226779-a3f38397-5979-4c2e-8301-742c4fe18c8d.png)

## Spring Boot에서 Interceptor 적용하기
1. HandlerInterceptorAdapter를 상속받은 클래스 구현
  - PreHandle
  - PostHandle
  - afterComplete
  - afterConcurrentHandlingStarted
  ```java
  public class MyInterceptor implements HandlerInterceptor{
	// controller로 보내기 전에 처리하는 인터셉터
	// 반환이 false라면 controller로 요청을 안함
	// 매개변수 Object는 핸들러 정보를 의미한다. ( RequestMapping , DefaultServletHandler ) 
	@Override
	public boolean preHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		
		System.out.println("MyInterCeptor - preHandle");
		return false;
	}

	// controller의 handler가 끝나면 처리됨
	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mav)
			throws Exception {
	}

	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response,
			Object obj, Exception e)
			throws Exception {
	}
}
  ```
2. WebMvcConfigurerAdapter를 상속받은 설정 클래스 구현
  - addInterceptors는 등록할 인터셉터를 설정, addPathPatterns는 적용할 url 패턴을 설정
  - excludePathPatterns의 경우 인터셉터를 제외할 url 패턴을 등록하는 메서드로써 해당 url로 접근시에는 인터셉터를 적용하지 않게 된다.
