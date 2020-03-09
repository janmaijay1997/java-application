package com.springMvc.operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springMvc.model.User;
import com.springMvc.service.UserService;

@Component
@Transactional(propagation=Propagation.REQUIRED)
public class UserOperarionImpl implements UserOperation {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	@Autowired
	public UserOperarionImpl( UserService userService) {
		this.userService=userService;
	}

	@Override
	public User save(User user) {
		
		return userService.save(user);
	}

	@Override
	public List<User> search(User user) {
		return userService.search(user);
	}

}
