# 컨트롤러 <->  JSP 



### Controller
- 로그인 시 id, password를 session에 저장 후
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
- 회원 조회 페이지에 뿌려줄 때는 model에 LoginMember를 등록해서 사용
```java

@RequestMapping(value="/employeeMgmt")
	public String employeeMgmt(Model model, HttpSession session) throws Exception {
		LoginMember loginMember = new LoginMember((String)session.getAttribute("sessionLogin_id"), (String)session.getAttribute("sessionPassword"));
		Member member = memberService.loginUser(loginMember);
		model.addAttribute("LoginMember",member);
		return "employeeMgmt";
	}
```


### employeMgmt.jsp
- 화면 단에서 `${LoginMember. }`으로 
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.pion.domain.Member"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<title>파이언넷</title>
<script type="text/JavaScript" src="/js/jquery-1.8.3.min.js"></script>
<script type="text/JavaScript" src="/js/openwin.js"></script>
<script type="text/JavaScript" src="/js/text.js"></script>
<script type="text/JavaScript" src="/js/validate.js"></script>
<script type="text/JavaScript" src="/js/number.js"></script>

<div style="display:none">
<iframe src="" name="action_frame" width="0" height="0"></iframe>
</div>
<script>
	
	//수정
	function fn_update(){
	
		var f = document.formEmployee;
		
		
		//패스워드 chk
		if( isEmpty( f.password, "패스워드를 입력하세요.")) return;
	
		//패스워드 재확인
		if( isEmpty( f.password_chk, "패스워드를 다시 확인해 주세요." )) return;
	
		//패스워드 친거랑 패스워드 재확인이 틀릴경우 ckh
		if( f.password.value !=  f.password_chk.value){
			alert("패스워드가 일치하지 않습니다. 다시 한번 확인 해 주시기 바랍니다.");
			f.password.focus();
			return;
		}

		//이름 chk
		if( isEmpty( f.emp_name, "이름을 입력하세오")) return;
	
		f.resd_no.value = f.resd_no_1.value + "" + f.resd_no_2.value;
		
		//본인생일
		if(isEmpty(f.my_birthday, "생일을 입력하세요")) return;
		else{
			var regex = /[^0-9]/g;
			let target = document.getElementById('my_birthday').value;
			let result = target.replace(regex, "");
			target.value = result;
		}
		
		if( f.my_birthday.value == '' ){
			
			alert("생일을 형식에 맞게 입력 하여 주십시오");
			f.my_birthday.focus();
			return;
		
		}
		
		
		if( f.married_anniver_date.value != '' ){
		
			if( !isDate(f.married_anniver_date.value) ){
			
				alert("결혼기념일을 형식에 맞게 입력 하여 주십시오");
				f.married_anniver_date.focus();
				return;
			}
		}
		
		//결혼기념일
		if( f.married_anniver_date.value != '' ){
		
			f.married_yn.value = 'Y';
			
		}else{
		
			f.married_yn.value = 'N';
		}
		
		//사내커플
		if( !f.couple_yn.checked ){
		
			f.comp_couple_yn.value = 'N';
		}else{
		
			f.comp_couple_yn.value = 'Y';
		}
		
		//입사일 chk
		if(isEmpty(f.enter_date, "입사일을 입력하세요")) return;
		else {
			var regex = /[^0-9]/g;
			let target = document.getElementById('enter_date').value;
			let result = target.replace(regex, "");
			target.value = result;
		}

		if( f.before_career_year.value == '' ){
			
			f.before_career_year.value = '00';
		}
		
		if( f.before_career_month.value == '' ){
			
			
			f.before_career_month.value = '00';
		}
		
		if(	f.before_career_year.value == '' &&  f.before_career_month.value == ''){
			
			f.before_career_year.value = '00';
			f.before_career_month.value ='00';
		}
		
		if( f.company_career_year.value == '' ){
			f.company_career_year.value = '00';	
		}
		
		if( f.company_career_month.value != '' ){
			
			if( f.company_career_month.value > 11 ){
				alert("당사 경력(월)을 형식에 맞게 입력해 주십시오.");
				f.company_career_month.value = '';
				f.company_career_month.focus();
				return;
			}
			
			if( f.company_career_year.value == '' ){
				f.company_career_year.value = '00';
			}
			
			if( f.company_career_month.value.length < 2 ){
				f.company_career_month.value =  '0' + f.company_career_month.value;
			}else{
				f.company_career_month.value;
			}
		}else{
			
			f.company_career_month.value = '00';
		}
		
		if(	f.company_career_year.value == '' && f.company_career_year.value == ''){
			
			f.company_career_year.value = '00' ;
			f.company_career_month.value ='00';
		}
		
		f.before_career_cnt.value = f.before_career_year.value + f.before_career_month.value;
		f.company_career_cnt.value = f.company_career_year.value + f.company_career_month.value;
			
		//집주소 chk
		if( f.home_zipcode.value == ''){
		
			alert("집 우편번호를 입력하세요");
			f.home_zipcode.focus();
			return;
		}	
	
		if(isEmpty(f.home_detail_addr, "집주소를 입력하세요")) return;
	
		//집연락처 chk
		if(isEmpty(f.home_tel_no, "집연락처를  입력하세요")) return;
	
		//현거주지 chk
		if( f.now_zipcode.value == ''){
		
			alert("현거주지 우편번호를 입력하세요");
			f.now_zipcode.focus();
			return;
		}
		if(isEmpty(f.now_detail_addr, "현거주지 주소를 입력하세요")) return;
	
		//현거주지 연락처 chk
		if(isEmpty(f.tel_no, "현거주지 연락처를  입력하세요")) return;
	
		//핸드폰 chk
		if(isEmpty(f.hp_tel_no, "핸드폰번호를 입력하세요")) return;


		//양력여부
		if( !f.check_yn.checked ){
		
			f.solar_yn.value = 'N';
			
		}else{
		
			f.solar_yn.value = 'Y';
		}
		
		f.action = "/updateMember";
		
		//저장
		f.method.value ="insertNewEmployee";
		
		f.target = "action_frame";
		f.encoding="multipart/form-data";
		f.submit();
		
	}
	
	//집우편번호 셋팅
	function fn_setZipCodeHome(zipCode, base_addr){
		var f = document.formEmployee;
		
		f.home_zipcode.value = zipCode;
		f.home_addr.value = base_addr;
	}
	
	//현거주지 우편번호 셋팅
	function fn_setZipCodeNow(zipCode, base_addr){
	
		var f = document.formEmployee;
		
		f.now_zipcode.value = zipCode;
		f.now_addr.value = base_addr;
	}
	
	//취소하기
	function fn_cancel(){

		window.close();
	
	}
	
	//중복 아이디 찾기 팝업
	function fn_loginIdCheck(){
	
	var f = document.formEmployee;
	
	if( f.login_id.value == '' ) {

			alert("아이디를 입력해 주세요.");
			f.login_id.focus();
			return;

		}
	}
	//아이디 중복 체크 후 콜백되는 평션 타기
	function fn_loginIdChk(flag_value, login_id) {

		var f = document.formEmployee;

		f.login_id.value = login_id;

		// 아이디 중복체크 플레그 변경
		f.login_id_flag.value = flag_value;
		return;

	}
	
	//집주소와 동일(체크박스를 클릭했을 경우)
	function fn_addr_equal(){
	
		var f = document.formEmployee;
		
		if( f.home_addr_equal.checked == true ){
		
			f.now_zipcode.value = f.home_zipcode.value;
			f.now_addr.value = f.home_addr.value;
			f.now_detail_addr.value = f.home_detail_addr.value;
			f.tel_no.value = f.home_tel_no.value;
		}
	}
	
	
	
    window.onload = function() {
			document
					.getElementById('password_chk')
					.addEventListener(
							'blur',
							function() {
								var password = document
										.getElementById('password').value;
								if (password === this.value) {
									document.getElementById('pwd_span').innerHTML = '비밀번호가 일치합니다.';
									document.getElementById("pwd_span").style.color = 'blue';
								} else {
									document.getElementById('pwd_span').innerHTML = '비밀번호가 일치하지 않습니다.';
									document.getElementById("pwd_span").style.color = 'red';
								}
							});
			
			// 주민번호 파싱하기
			document.getElementById('resd_no_1').value = fn_getResd_no1(${LoginMember.resd_no});
			document.getElementById('resd_no_2').value = fn_getResd_no2(${LoginMember.resd_no});
    }

</script>

</head>
<body>
<% 
Member member = (Member)request.getAttribute("LoginMember"); 
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
        
<form name="formEmployee" method="post">
<input type="hidden" name="method">
<input type="hidden" name="resd_no" value="">
<input type="hidden" name="before_career_cnt" value="">
<input type="hidden" name="company_career_cnt" value="">
<input type="hidden" name="retire_date" value="">
<input type="hidden" name="team_leader_yn" value="">
<input type="hidden" name="obj_team_id" value="4">
<input type="hidden" name="login_id_flag" value="N">
<input type="hidden" name="solar_yn" value="">		<!-- 양력여부 -->
<input type="hidden" name="married_yn" value="">	<!-- 결혼여부 -->
<input type="hidden" name="enter_yn" value="Y">		<!-- 입사처리 여부 -->
<input type="hidden" name="career_history_yn" value="">	<!-- 기술경력 여부 -->
<input type="hidden" name="obj_login_id" value="loki">		<!-- 수정시 기존에 가지고 있던 로그인 아이디 -->
<input type="hidden" name="comp_couple_yn" value=""><!-- 사내커플 여부 -->
<input type="hidden" name="year" value="2022"><!-- 복지년도 -->
<input type="hidden" name="walfare_kind_code" value=""><!-- 복지종류코드 -->
<input type="hidden" name="walfare_gubun_code" value=""><!-- 복지구분코드 -->
<input type="hidden" name="pay_yn" value="N"><!-- 지급여부 -->
<input type="hidden" name="job_code" value="">
<input type="hidden" name="auth_gubun_code" value="10">
<input type="hidden" name="insert_id" value="">
<input type="hidden" name="modify_id" value="">

	<tr>
		<td height="35"><img src="/images/icon_title.gif" width="19" height="19" align="absmiddle"><span class="title_tt">개인정보 수정</span></td>
	</tr>
	<tr>
		<td height="1" bgcolor="#CCCCCC"></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
    <tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
                	<td valign="top"> 
                  	<!--신입&경력사원 등록 테이블 -->
						<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#CCCCCC">
  							<tr bgcolor="#FFFFFF"> 
    							<td width="10%" height="23" bgcolor="#eeeeee" ><span class="red_n">* </span>아이디 </td>
    							<td colspan="3" >
    								<input name="login_id" type="text" class="input01" size="20" onKeyUp="javascript:fn_loginIdChk('N',login_id.value)" value="${LoginMember.login_id }" >
    							</td>
    							<%--
    							<td rowspan="5" width="20%" align="center">
    								<img name="emp_base_image" src="/upload/employee/emp_image/20220112\202201120.JPG" border="0" height="150" onerror="this.src ='/image/noimg/noimg_150.gif';">
    							</td>
    							 --%>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>  패스워드</td>
								<td colspan="3" bgcolor="#FFFFFF"><input name="password" id="password" type="password" class="input00" size="20" value="${LoginMember.password}" > 
							    	<span class="c_txt"></span>
							    </td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>   패스워드 확인</td>
								<td colspan="3" bgcolor="#FFFFFF">
									<input name="password_chk" id="password_chk" type="password" class="input00" size="20" value="${LoginMember.password}"> 
							    	<span class="c_txt" id="pwd_span">다시한번 입력해 주세요.</span>
							    </td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span> 사번</td>
								<td colspan="3" bgcolor="#FFFFFF"><input name="emp_no" id="emp_no" type="text" class="input03" size="20" readonly  value="${LoginMember.emp_no}" ></td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>  이름</td>
								<td colspan="3" bgcolor="#FFFFFF"><input name="emp_name" id="emp_name" type="text" class="input00" size="20" value="${LoginMember.emp_name }"></td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span> 주민번호</td>
								<td colspan="3" bgcolor="#FFFFFF">
  

									<input name="resd_no_1" id="resd_no_1" type="text" class="input00" size="12" maxlength="6" value="">
									- <input name="resd_no_2" id="resd_no_2" type="text" class="input00" size="12" maxlength="7" value="">

		
    
    							</td>
    							<td bgcolor="#eeeeee" align="center">
    							<%-- 
    								<a href="javascript:fn_base_image(20220112);">메인사진 등록</a>
    								
    								<a href="javascript:fn_udt_image(20220112);">사진첩 관리</a>
    								--%>
    							</td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>본인생일</td>
								<td bgcolor="#FFFFFF">
							  		<input name="my_birthday" id="my_birthday" type="text" class="input00" size="12" value="${LoginMember.my_birthday }" maxLength="8">
							  		<span class="c_txt">예) 19000101(년월일)</span>
							  	</td>
								<td bgcolor="#eeeeee"><font color="#eeeeee">*</font>  양력여부 </td>
								<td bgcolor="#FFFFFF" colspan="2">
									<input name="check_yn" id="check_yn" type="checkbox" class="input00" size="12" value="Y"  checked> 
							    </td>
							</tr>
							
							
							<tr> 
								<td bgcolor="#eeeeee"><font color="#eeeeee">*</font>  결혼기념일 </td>
								<td bgcolor="#FFFFFF">
							  		<input name="married_anniver_date" id="married_anniver_date" type="text" class="input00" size="12" value="${LoginMember.married_anniver_date }" maxLength="8"> 
							  		<span class="c_txt">예) 19000101(년월일)</span>
							  	</td>
							  	<td bgcolor="#eeeeee"><font color="#eeeeee">*</font>  사내커플여부 </td>
								<td bgcolor="#FFFFFF" colspan="2">
									<input type="checkbox" name="couple_yn" id="couple_yn" class="input00" value=""   onClick="fn_coupleYn()">
								</td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>입사일</td>
								<td bgcolor="#FFFFFF" colspan="4">
							  		<input name="enter_date" id="enter_date" type="text" class="input00" size="12" value="${LoginMember.enter_date}"  readonly> 
								</td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span>직급</td>
								<td bgcolor="#FFFFFF">
							  		<select name="job_code" disabled>
										<option	value="10"selected>사원</option>
                                        <option	value="20">대리</option>
                                        <option	value="30">과장</option>
                                        <option	value="40">차장</option>
                                        <option	value="50">부장</option>
                                        <option	value="60">이사</option>
                                        <option	value="70">상무</option>
                                        <option	value="80">사장</option>

									</select>
								</td>
								<td bgcolor="#eeeeee"><span class="red_n">*</span>권한구분</td>
								<td bgcolor="#FFFFFF" colspan="2">
									<select name="auth_gubun_code" disabled>
										<option	value="10"selected>일반</option>
                                        <option	value="20">승인자</option>
                                        <option	value="30">관리자</option>
                                        <option	value="40">슈퍼관리자</option>

  									</select>
  								</td>
							</tr>
							<tr> 
  								<td bgcolor="#eeeeee"><span class="red_n">*</span>기본부서ID</td>
  								<td bgcolor="#FFFFFF">
								  	<input name="base_team_id" id="base_team_id" type="hidden" value="4">
								  	<input name="base_team_name" id="base_team_name" type="text" class="input03" size="12" value="경영지원부" readOnly>
								<td bgcolor="#eeeeee"><span class="red_n">*</span>업무그룹ID</td>
								<td bgcolor="#FFFFFF" colspan="2">
									<input name="biz_grp_id" id="biz_grp_id" type="hidden" value="9" >
									<input name="biz_group_name" id="biz_group_name" type="text" class="input03" size="12" value="일반" readOnly>
								</td>
							</tr>
							<tr>
								<td bgcolor="#eeeeee"><font color="#eeeeee">*</font>  부서장여부 </td>
								<td bgcolor="#FFFFFF">
  									<input name="check" id="check" type="checkbox" class="input00" size="12" value="N"   disabled>
 								</td>
 								<td bgcolor="#eeeeee"><font color="#eeeeee">*</font>  E-MAIL </td>
								<td bgcolor="#FFFFFF" colspan="2">
  									<input type="text" name="email" id="email" class="input00" size="30" value="${LoginMember.email }"> 
									<span class="c_txt">@pionnet.co.kr</span>
 								</td>
							</tr>
							<tr> 
								<td width="10%" bgcolor="#eeeeee"><font color="#eeeeee">*</font>입사전경력</td>
								<td bgcolor="#FFFFFF"><a href="#"></a>

									<input name="before_career_year" id="before_career_year" type="text" class="input00" size="4" maxLength="2" value="00">년&nbsp;&nbsp;
									<input name="before_career_month" id="before_career_month" type="text" class="input00" size="4" maxLength="2" value="00">월 

									<span class="c_txt">예) 02년 03월 /두자리씩 입력해 주세요.</span>
								</td>
								<td width="10%" bgcolor="#eeeeee"><font color="#eeeeee">*</font>당사경력</td>
								<td bgcolor="#FFFFFF" colspan="2">

									<input name="company_career_year" id="company_career_year" type="text" class="input00" size="4" maxLength="2" value="00">년&nbsp;&nbsp;
									<input name="company_career_month" id="company_career_month" type="text" class="input00" size="4" maxLength="2" value="00">월


  									<span class="c_txt">예) 02년 03월 /두자리씩 입력해 주세요.</span>
  								</td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span> 집주소</td>
								<td colspan="4" bgcolor="#FFFFFF">
							 		<table width="100%" border="0" cellspacing="2" cellpadding="0">
										<tr> 
							         		<td>
												<input name="home_zipcode" id="home_zipcode" type="text" class="input00" size="8" value="${LoginMember.home_zipcode }">
										</tr>
										<tr> 
											<td height="22">
											  	<input name="home_addr" type="text" class="input01" size="40"  value="${LoginMember.home_addr }"> 
											    <input name="home_detail_addr" id="home_detail_addr" type="text" class="input00" size="40" value="${LoginMember.home_detail_addr }">
											</td>
										</tr>
										<tr> 
											  <td><span class="red_n">*</span> 연락처 
											    <input name="home_tel_no" id="home_tel_no" type="text" class="input00" size="20" value="${LoginMember.home_tel_no }"> 
											           <span class="c_txt">예) 02-111-1111</span>
											  </td>
										</tr>
									</table>
								</td>
							</tr>
							<tr> 
								<td bgcolor="#eeeeee"><span class="red_n">*</span> 현거주지</td>
								<td colspan="4" bgcolor="#FFFFFF">
									<table width="100%" border="0" cellspacing="2" cellpadding="0">
										<tr> 
											<td>
											  	<input name="now_zipcode" id="now_zipcode" type="text" class="input00" size="8"  value="${LoginMember.now_zipcode }">
	    									</td>
	    									<td><font color="#eeeeee">*</font> 집주소와 동일
											  	<input name="home_addr_equal" id="home_addr_equal" type="checkbox" class="input00" size="8" value="" onClick="fn_addr_equal()">
	    									</td>
										</tr>
										<tr> 
											<td height="22" colspan="2">
										  		<input name="now_addr" id="now_addr" type="text" class="input01" size="40"  value="${LoginMember.now_addr }"> 
										    	<input name="now_detail_addr" id="now_detail_addr" type="text" class="input00" size="40" value="${LoginMember.now_detail_addr }">
											</td>
										</tr>
										<tr> 
											<td><span class="red_n">*</span> 연락처 
										    	<input name="tel_no" id="tel_no" type="text" class="input00" size="20" value="${LoginMember.tel_no }"> 
										        <span class="c_txt">예) 031-111-1111</span>
										    </td>
										</tr>
									</table>
								</td>
							</tr>
							<tr> 
								<td width="10%" bgcolor="#eeeeee"><span class="red_n">*</span>핸드폰</td>
								<td colspan="4" bgcolor="#FFFFFF">
							    	<input name="hp_tel_no" id="hp_tel_no" type="text" class="input00" size="20" value="${LoginMember.hp_tel_no }"> 
							      	<span class="c_txt">예) 011-111-1111</span>
							    </td>
							</tr>
							
						</table>
						<!--신입&경력사원 등록 테이블 끝-->
					</td>
				</tr>
				<tr> 
	  				<td height="10"></td>
				</tr>
				<tr> 
	  				<td align="center"> 
    			<!--버튼-->
						<a href="javascript:fn_update();"><img src="/images/btn_b_edit.gif" width="60" height="30" border="0"></a> 
      					<a href="javascript:fn_cancel();"><img src="/images/btn_b_cancel.gif" width="60" height="30" border="0"></a> 
	   				</td>
	  			</tr>
   	</form>
	</table>



</body>
</html>
```
