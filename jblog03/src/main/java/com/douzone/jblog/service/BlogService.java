package com.douzone.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public BlogVO getBlogContent(String id) {
		BlogVO boardVO = blogRepository.blogFindById(id);
		
		return boardVO;
	}

	//전체 카테고리 리스트 호출
	public List<CategoryVO> getCategoryContent(String id) {
		
		return blogRepository.categoryFind(id);
	}
	
	//전체 포스트 리스트 호출
	public List<PostVO> getPostContent(String id) {
		return blogRepository.postFind(id);
	}

	//선택한 카테고리 내용 호출
	public CategoryVO getCategoryContent(Optional<Integer> categoryNo) {
		CategoryVO categoryVO = blogRepository.categoryFindByNo(categoryNo);
		
		return categoryVO;
	}

	
}
