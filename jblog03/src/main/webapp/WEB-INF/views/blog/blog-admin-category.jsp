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
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
window.onload = function() {
	var count = document.getElementById("clistLength").value;
// 	count = parseInt(count)+1;
// 	console.log(count);
	
	var deleteCategory = function() {
		console.log("!!");
	}
	
	
    document.getElementById('addButton').onclick = function() {
// 	count = parseInt(count)+1;

	var categoryvo = {
			'name': $("#name").val(),
			'desc': $("#desc").val()
	};
	
		$.ajax({
			url: "${pageContext.request.contextPath }/${authUser.id}/categoryAdd",
			type: "post",
			dataType: "json",
			data: categoryvo,
			error: function(xhr, status, e) {
				console.log(status, e);
			},
			success: function(data) {
				console.log("카테고리가 생성되었습니다 data : " + data);
				$("#name").val('');
				$("#desc").val('');
				data.count = parseInt(count)+1;;
				refreshTable(data);
			}
		});
	
    };
    
    
    document.getElementById('deleteButton').onclick = function() {
		console.log(this);
    };
    
    
    
    
    
}

//카테고리 테이블에 데이터 추가
function refreshTable(data) {
	console.log(data);
	var html = "<tr>"
			 + "<td>" + data.count + "</td>"
			 + "<td>" + data.name + "</td>"
			 + "<td>" + data.countPost + "</td>"
			 + "<td>" + data.desc + "</td>"
			 + "<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></td>"
			 + "</tr>";
	$(".admin-cat").append(html);	
}



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
		      		<c:set var='count' value='${fn:length(clist) }' />
					<c:forEach items='${clist }' var='cdto' varStatus='status'>
	      			<input type="hidden" id="categoryNo" value="${cdto.no}" />
						<tr>
							<td>${status.index+1 }</td>
							<td>${cdto.name }</td>
							<td>${cdto.countPost }</td>
							<td>${cdto.desc }</td>
							<td><img id="deleteButton" value="${cdto.no }" src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
						</tr>  
      				</c:forEach>
				</table>
      			<input type="hidden" id="clistLength" value="${fn:length(clist)}" />
      			
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
		      			<td>
		      				<input type="submit" id="addButton" value="카테고리 추가">
		      			</td>
		      		</tr>
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/include/footer.jsp" />
	</div>
</body>
</html>