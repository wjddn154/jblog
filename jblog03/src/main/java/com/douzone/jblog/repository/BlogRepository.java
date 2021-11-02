package com.douzone.jblog.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;

	public BlogVO blogFindById(String id) {
		return sqlSession.selectOne( "blog.findById", id);
	}

	public List<CategoryVO> categoryFind(String id) {
		return sqlSession.selectList("category.findAll");
	}
	
	public List<PostVO> postFind(String id) {
		return sqlSession.selectList("post.findAll");
	}

	public CategoryVO categoryFindByNo(Optional<Integer> categoryNo) {
		return sqlSession.selectOne( "category.findByNo", categoryNo);
	}

	
	
}
