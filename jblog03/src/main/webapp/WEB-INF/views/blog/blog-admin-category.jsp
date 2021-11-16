<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="${pageContext.request.contextPath }/assets/js/jquery/ejs.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
//카테고리 추가
var listItemEJS = new EJS({
	url: '${pageContext.request.contextPath }/assets/js/jquery/listitem-template.ejs'
	
});
$(function() {
	document.getElementById('addButton').onclick = function() {
	event.preventDefault();
	
	categoryvo = {
			'name': $("#name").val(),
			'desc': $("#desc").val()
	};
	console.log(categoryvo);
	
	if(categoryvo.name == '' || categoryvo.desc == '') {
		return;
	}

		$.ajax({
			url: "${pageContext.request.contextPath }/${authUser.id}/categoryAdd",
			type: "post",
			dataType: "json",
			data: categoryvo,
			success: function(data) {
				console.log("카테고리가 생성되었습니다 data : " + JSON.stringify(data.data));
				$("#name").val('');
				$("#desc").val('');
				data.data.count = parseInt($('#clistLength').val())+1;
				console.log("카테고리가 생성되었습니다 data11231 : " + JSON.stringify(data.data));

				console.log("count : " + data.count);
				//카테고리 테이블에 데이터 추가
				var html = listItemEJS.render(data.data);
				console.log(html);
				$('#admin-cat > tbody:last').append(html);

			},
			error: function(xhr, status, error) {
				console.error(status + " : " + error);	
			}
		});
    };
});


//카테고리 삭제
$(function(){
	
	// 삭제 다이알로그 객체 만들기
	var dialogDelete = $('#dialog-delete-form').dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			"삭제": function() {
				// ajax 삭제....
				var no = $('#hidden-no').val();
				var url = '${pageContext.request.contextPath }/${authUser.id}/categoryDelete/' + no;
				
				$.ajax({
					url: url,
					type: 'post',
					dataType: 'json',
					success: function(response) {
						
						if(response.data == -1) {
							$('.validateTips.error').show();
							return;
						}
						// 삭제가 된 경우
						$('#admin-cat tr img[data-no=' + response.data + ']').parent().parent().remove();
						dialogDelete.dialog('close');
					}
				});
			},
			"취소": function() {
				$(this).dialog('close');
			}
		}
	});
	
	
	
	
	// 글 삭제 버튼 (Live Event)
	$(document).on('click', '#admin-cat tr td img', function(event){
		event.preventDefault();

		var no = $(this).data('no');
		console.log("no : " + no);
// 		$(this).parent().parent().remove();
		$("#hidden-no").val(no);
// 		$('#admin-cat tr').remove();
// 		$('#admin-cat tr[data-no=59]').remove();
// 		$('#admin-cat tr img[data-no=62].parent()').remove();
// 		$('#admin-cat tr img[data-no=63]').remove();
		
		
		dialogDelete.dialog('open');
	});
	
});

//카테고리 삭제

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
		      	<table class="admin-cat" id="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var='count' value='${fn:length(clist) }' />
					<c:forEach items='${clist }' var='cdto' varStatus='status'>
						<tr>
							<td>${status.index+1 }</td>
							<td>${cdto.name }</td>
							<td>${cdto.countPost }</td>
							<td>${cdto.desc }</td>
							<td><img data-no='${cdto.no }' src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
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
		      	<!-- 삭제 다이어로그 창 -->
	      		<div id="dialog-delete-form" title="카테고리 삭제" style="display:none">
			  		<p class="validateTips normal">정말로 카테고리를 삭제하시겠습니까?</p>
			  		<p class="validateTips error" style="display:none">에러가 발생했습니다.</p>
			  		<form>
						<input type="hidden" id="hidden-no" value="">
						<input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
					</form>
				</div>	
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/include/footer.jsp" />
	</div>
</body>
</html>