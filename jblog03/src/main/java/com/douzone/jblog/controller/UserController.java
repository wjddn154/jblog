package com.douzone.jblog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//회원가입 페이지
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVO userVO) {
		return "user/join";
	}
	
	
	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@Valid UserVO vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	//회원가입 성공 페이지
	@RequestMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	//로그인 페이지
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	
}
