<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		
		<div class="logo" onclick="location.href='${pageContext.request.contextPath }/main'" 
			style="background:url(${pageContext.request.contextPath}/assets/images/logo.jpg) 0 0 no-repeat; cursor:pointer"  >
<%-- 			<a href="${pageContext.request.contextPath }/main"> --%>
<!-- 				JBlog -->
<!-- 			</a> -->
		</div>
		
		<ul class="menu">
			<c:choose>
				<c:when test="${empty authUser }">
<%-- 					<li><a href="${pageContext.request.contextPath }/main">홈</a></li> --%>
					<li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li>
					<li><a href="${pageContext.request.contextPath }/user/join">회원가입</a></li>
<%-- 					<li><a href="${pageContext.request.contextPath }/guest">내블로그</a></li> --%>
				</c:when>
				<c:otherwise>
<%-- 					<li><a href="${pageContext.request.contextPath }/main">${authUser.name}님의 홈</a></li> --%>
					<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
					<li><a href="${pageContext.request.contextPath }/${authUser.id}">내블로그</a></li>
				</c:otherwise>
			</c:choose>
		</ul>