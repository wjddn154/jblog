package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		//2. casting
		HandlerMethod handlerMehtod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMehtod.getMethodAnnotation(Auth.class);
		
		//4. Handler Mehtod에 @Auth가 없으면 Type에 있는지 확인
		if(auth == null) {
			//과제
			auth = handlerMehtod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		//5. Type과 Method에 @Auth가 적용이 안되어 있는 경우
		if(auth == null) {
			
			return true;
		}
		
		
		//6. @Auth가 적용이 되어있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		//7. 권한(Authorzation) 체크를 위해서 @Auth의 role 가져오기("USER", "ADMIN")
		/*
		 * String role = auth.role();
		 * 
		 * //8. 권한 체크 // 과제 if("USER".equals(role)) { return true; }
		 * 
		 * //9.@Auth의 role이 "ADMIN" 인 경우, authUser의 role은 "ADMIN" 이어야 한다.
		 * if("ADMIN".equals(authUser.getRole()) == false) {
		 * 
		 * response.sendRedirect(request.getContextPath()); return false; }
		 */

		// 옳은 관리자 권한
		// @Auth의 role: "ADMIN"
		// authUser의 role: "ADMIN"
		return true;
	}

}
