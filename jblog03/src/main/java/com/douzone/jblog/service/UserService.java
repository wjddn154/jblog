package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public void join(UserVO vo) {
		userRepository.insert(vo);
	}

	public UserVO getUser(String email) {
		return 	userRepository.findById(email);
	}

	public UserVO getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

}
