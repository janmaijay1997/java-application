package com.springMvc.service;

import java.io.Serializable;
import java.util.List;

import com.springMvc.model.User;


public interface UserService extends Serializable {
	
	
public User save(User user);
public List<User> search(User user);



}
