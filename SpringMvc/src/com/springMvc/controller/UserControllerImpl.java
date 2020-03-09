package com.springMvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springMvc.model.User;
import com.springMvc.operation.UserOperation;

@Controller
@RequestMapping(value="/user")
public class UserControllerImpl implements UserController {

	private UserOperation userOperation;
	
	@Autowired
	public UserControllerImpl( UserOperation userOperation) {
		this.userOperation=userOperation;
	}
	
	@Override
	public User save( @RequestBody User user) {
		System.out.println("sd");

		return userOperation.save(user);
		
	}

	@Override
	public List<User> search( @RequestBody User user) {
		return userOperation.search(user);
	}
	

}
