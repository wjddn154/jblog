<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
//id 변경시 가입하기 버튼 비활성화
$(function(){
	$("#btn-checkeId").click(function() {
		var id = $("#id").val();
		if(id == '') {
			return false;
		}
		
		$.ajax({
			url: "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e) {
				console.log(status, e);
			},
			success: function(response) {
				console.log(response);
				if(response.result != "success") {
					console.error(response.message);
					return;
				}
				
				if(response.data) {
					alert("존재하는 아이디입니다. 다른 아이디을 사용하세요.");
					$("#id")
						.val("")
						.focus();
					return;
				} else {
					//가입하기 버튼 활성화
					$('#register-button').prop('disabled', false);
				}
				
				$("#btn-checkeId").hide();
				$("#img-checkId").show();

			}
		});		
	});	
});

$(function () {
   var inputElement = $("#id");
   inputElement.focus(function() {
	 	$('#register-button').prop('disabled', true);
	 	$("#btn-checkeId").show();
	 	$("#img-checkId").hide();
	})
});

</script>

</head>
<body>
	<div class="center-content">
		<!-- include 처리 -->
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		
		
		<form:form 
			modelAttribute="userVO" 
			class="join-form"
			id="join-form" 
			name="join-Form" 
			method="post" 
			action="${pageContext.request.contextPath}/user/join">
			
			<label class="block-label" for="name">이름</label>
<!-- 			<input id="name"name="name" type="text" value=""> -->
			<form:input path="name" />
			<p style="text-align:left; padding-left:0; color: #f00">
				<spring:hasBindErrors name="userVO">
					<c:if test="${errors.hasFieldErrors('name') }">
						<spring:message code="${errors.getFieldError('name').codes[0] }" />
					</c:if>
				</spring:hasBindErrors>
			</p>
					
			<label class="block-label" for="id">아이디</label>
<!-- 			<input id="blog-id" name="id" type="text">  -->
			<form:input path="id" />
			<input id="btn-checkeId" type="button" value="id 중복체크">
			<img id="img-checkId" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png" style='width:16px; display: none' />
			<p style="text-align:left; padding-left:0; color: #f00">
				<form:errors path="id" />
			</p>



			<label class="block-label" for="password">패스워드</label>
<!-- 			<input id="password" name="password" type="password" /> -->
			<form:password path="password" />
			<p style="text-align:left; padding-left:0; color: #f00">
				<form:errors path="password" />
			</p>



			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" id="register-button" value="가입하기" disabled>

		</form:form>
	</div>
</body>
</html>
