package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVO;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public BlogVO getcontent(String id) {
		BlogVO boardVo = blogRepository.findById(id);
		return boardVo;
	}
	
	
	
	
}
