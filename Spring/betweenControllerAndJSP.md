# 컨트롤러 <->  JSP 



### Controller
```java
@RequestMapping("/common.do")
	public String 로그인_검증_세션_등록(HttpServletRequest request, Model model, HttpSession session) throws Exception {
		
		String id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String path = "";
		
		LoginMember loginMember = new LoginMember(id, password);
		Member member = memberService.loginUser(loginMember);
		
		
		if(member != null) {
			log.info("name: " + member.getEmp_name() + " Result HP : " + member.getHp_tel_no() + " getEnter_date" + member.getEnter_date() + " getHome_zipcode"+ member.getHome_zipcode());
			model.addAttribute("name", member.getEmp_name());
			model.addAttribute("LoginMember", member);
			session.setAttribute("sessionLogin_id", loginMember.getLogin_id());
			path = "redirect:/main9";
		}else {
			path = "/login";
		}
		return path;
		
	}
```
jsp에서 session만 읽을 수 있다.
