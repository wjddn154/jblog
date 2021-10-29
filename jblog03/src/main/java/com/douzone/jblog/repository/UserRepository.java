package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.exception.UserRepositoryException;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.UserVO;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVO userVO) {
		int count1 = sqlSession.insert("user.insert", userVO);
		int count2 = 0;
		
		if(count1==1) {
			BlogVO blogVO = new BlogVO();
			blogVO.setId(userVO.getId());
			count2 = sqlSession.insert("blog.insert", blogVO);
		}
		
		return count2 == 1;
	}

	public UserVO findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}
	
	public UserVO findByIdAndPassword(String id, String password) throws UserRepositoryException {
		Map<String, String> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByIdAndPassword", map);
	}
	
	
	
}
