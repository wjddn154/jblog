package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVO;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	//pathvariable로 작업해야함
	@RequestMapping({"","/main"})
	public String main(@RequestParam(value="id", required=true, defaultValue="") String id, Model model) {
		BlogVO blogVO = blogService.getcontent(id);
		model.addAttribute("blogVO", blogVO);
		
		return "blog/blog-main";
	}
	
	//블로그 관리 페이지
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage() {
		return "blog/blog-admin-basic";
	}
	
	
	//카테고리 페이지
	
	
	
	
	
	
}
