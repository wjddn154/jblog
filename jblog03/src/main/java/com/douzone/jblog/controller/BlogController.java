package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryDTO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;

@Controller
//@RequestMapping("/blog")
@RequestMapping("/{id:(?!^assets$|^upload$).*}")
//@RequestMapping(value={"/{id:(?!assets).*}","/{id:(?!upload).*}"})
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
		
		PostVO recentPostVO = blogService.getRecentPost(blogid);
		
		System.out.println(blogVO);
		System.out.println(clist);
		System.out.println(plist);

		model.addAttribute("blogVO", blogVO);
		model.addAttribute("clist", clist);
		model.addAttribute("plist", plist);
		model.addAttribute("recentPostVO", recentPostVO);
		
		return "blog/blog-main";
	}
	
	//블로그 관리 페이지
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String blogid, Model model) {
		BlogVO blogVO = blogService.getBlogContent(blogid);
		model.addAttribute("blogVO", blogVO);

		return "blog/blog-admin-basic";
	}
	
	
	@Auth
	@RequestMapping( value="/admin/modify", method=RequestMethod.POST )	
	public String modify(
			@PathVariable("id") String id,
			@RequestParam( value="title", required=true, defaultValue="default title") String title,
			@RequestParam("logo") MultipartFile logo,
//			BlogVO blogVO,
			Model model) {

		//VO로 받아보자
		BlogVO blogVO = new BlogVO();                                                                                                                                                                                                                                                             
		blogVO.setId(id);
		blogVO.setTitle(title);
		try {
			blogVO.setLogo(blogService.saveImage(logo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(blogVO.getLogo() == null) {
			blogVO.setLogo("/assets/images/spring-logo.jpg");
		}
		
		System.out.println(blogVO);
		blogService.modifyBlogContent(blogVO);


		return "redirect:/" + id;
	}

	
	
	//카테고리 페이지
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		System.out.println("adminCategory id : " + id);
		
		
		BlogVO blogVO = blogService.getBlogContent(id);
		model.addAttribute("blogVO", blogVO);
		
		List<CategoryDTO> clist = blogService.getCategoryContentWithCount(id);
		model.addAttribute("clist", clist);
		System.out.println(clist);
		
		return "blog/blog-admin-category";
	}
	
	//쓰기 페이지
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model) {
		System.out.println("adminWrite id : " + id);
		
		BlogVO blogVO = blogService.getBlogContent(id);
		model.addAttribute("blogVO", blogVO);
		
		List<CategoryVO> clist = blogService.getCategoryContent(id);
		model.addAttribute("clist", clist);
		
		return "blog/blog-admin-write";
	}
	
	//post 추가
	@RequestMapping(value="/postadd", method=RequestMethod.POST)
	public String postAdd(@PathVariable("id") String id, PostVO postVO, @RequestParam(value="category", required=true, defaultValue= "1") int no) {
		
		postVO.setCategoryNo(no);
		System.out.println("postVO : "  + postVO);
		blogService.addCategoryPost(postVO);
		
		return "redirect:/" + id;
	}
	
	//post 추가
	@RequestMapping(value="/categoryadd", method=RequestMethod.POST)
	public String categoryAdd(@PathVariable("id") String id, CategoryVO categoryVO) {
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		postVO.setCategoryNo(no);
//		System.out.println("postVO : "  + postVO);
//		blogService.addCategoryPost(postVO);
		
		return "redirect:/" + id;
	}
	
}
