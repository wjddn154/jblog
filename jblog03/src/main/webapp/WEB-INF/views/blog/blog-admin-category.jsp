<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script>
// $(document).ready(function() {
// window.onload = function() {
	
window.onload = function() {
    document.getElementById('onclick').onclick = function() {	
    
//     $('#theForm').submit(function(event){

	alert("!!!!");
	var cname = $("#name").val();
	var cdesc = $("#desc").val();
	var categoryvo = {
			name: cname,
			desc: cdesc
	};
	
	$.ajax({
		method: "post",
		dataType: "json",
		url: "${pageContext.request.contextPath }/${authUser.id}/categoryadd",
		data: categoryvo,
		success: function(data) {
			console.log(data);
		},
		error: function() {
			alert("에러발생!");
		}
	})
	
	
};

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/blog/include/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}/admin/write">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:forEach items='${clist }' var='cdto' varStatus='status'>
						<tr>
							<td>${cdto.no }</td>
							<td>${cdto.name }</td>
							<td>${cdto.countPost }</td>
							<td>${cdto.desc }</td>
							<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						</tr>  
      				</c:forEach>
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" id="name" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" id="desc" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" id="onclick" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>