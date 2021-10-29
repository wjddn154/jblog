package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVO;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public BlogVO findById(String id) {
		return sqlSession.selectOne( "blog.findById", id);
	}
	
}
