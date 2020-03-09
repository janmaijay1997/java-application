package com.springMvc.operation;

import java.io.Serializable;
import java.util.List;

import com.springMvc.model.User;


public interface UserOperation extends Serializable {

	public  User  save (User user);
	//public  User  delete ();
	public  List<User> search(User user);
}
