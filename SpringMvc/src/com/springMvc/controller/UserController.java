package com.springMvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springMvc.model.User;

public interface UserController {
	
	@RequestMapping(value="/save", method=RequestMethod.POST,consumes="application/json")
	@ResponseBody
	public User save(User user);
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	@ResponseBody
	public List<User> search(User user);

}
