package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.jblog.vo.UserVO;

public class AuthUserHandlerMethodArgumentResolver extends HandlerMethodArgumentResolverComposite {

	@Override
	public Object resolveArgument(
			MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		System.out.println(session);
		if(session == null) {
		
			return null;
		}
		System.out.println(session.getAttribute("authUser"));
		return session.getAttribute("authUser");
	}
	
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser가 안 붙어 있음
		if(authUser == null) {
			return false;
		}
		
		//파라미터 타입이 UserVO가 아님
		if(!parameter.getParameterType().equals(UserVO.class)) {
			return false;
		}
		
		return true;
	}

}
