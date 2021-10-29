package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVO;

@RestController("userApiController")	//@Controller + @ResponseBody = @RestController
@RequestMapping("/user/api")
public class UserControllerApi {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/checkid")
	public JsonResult checkid(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVO userVO = userService.getUser(id);
		
		return JsonResult.success(userVO != null);
	}
	

}
