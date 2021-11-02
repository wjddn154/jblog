package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;
import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.vo.BoardVO;
import com.douzone.mysite.vo.UserVO;
import com.douzone.web.util.WebUtil;

@Controller
//@RequestMapping("/blog")
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"","/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String main(@PathVariable("id") String blogid,
					   @PathVariable("categoryNo") Optional<Integer> categoryNo,
					   @PathVariable("postNo") Optional<Integer> postNo,
					   Model model) {
		if(categoryNo.isPresent()) {
//			if(postNo.isPresent()) {
//				return "blog/blog-main/categoryNo/postNo";
//			}
			
			CategoryVO categoryVO = blogService.getCategoryContent(categoryNo);
			model.addAttribute("categoryVO", categoryVO);

			
			return "blog/blog-admin-category";
		}
		
		BlogVO blogVO = blogService.getBlogContent(blogid);
		List<CategoryVO> clist = blogService.getCategoryContent(blogid);
		List<PostVO> plist = blogService.getPostContent(blogid);
		
		
		System.out.println(blogVO);
		System.out.println(clist);
		System.out.println(plist);
		model.addAttribute("blogVO", blogVO);
		model.addAttribute("clist", clist);
		model.addAttribute("plist", plist);
		
		return "blog/blog-main";
	}
	
	//블로그 관리 페이지
//	@Auth 작업 추가해야함
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogid, Model model) {
		BlogVO blogVO = blogService.getBlogContent(blogid);
		model.addAttribute("blogVO", blogVO);

		return "blog/blog-admin-basic";
	}
	
	
//	@Auth
	@RequestMapping( value="/admin/modify", method=RequestMethod.POST )	
	public String modify(
			@PathVariable("id") String id,
			@PathVariable("title") String title,
			@PathVariable("logo") String logo) {

		
		BlogVO blogVO = blogService.modifyBlogContent(title, logo);

//		boardVO.setUserNo( authUser.getNo() );
//		System.out.println("boardVO : " + boardVO);
//		boardService.modifyContents( boardVO );

//		return "redirect:/board/view/" + boardVO.getNo() + 
//				"?p=" + page + 
//				"&kwd=" + WebUtil.encodeURL( keyword, "UTF-8" );
		return "rdirect:
	}

	
	
	//카테고리 페이지
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("blogid") String blogid) {
		System.out.println("adminCategory blogid : " + blogid);
		return "blog/blog-admin-category";
	}
	
	
	
	
	
}
