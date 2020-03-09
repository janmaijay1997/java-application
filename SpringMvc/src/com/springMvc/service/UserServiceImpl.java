package com.springMvc.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springMvc.model.User;


@Service
public class UserServiceImpl  implements UserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	public UserServiceImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public User save(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<User> search(User user) {
		
		Criteria criteria=sessionFactory.getCurrentSession().getSession().createCriteria(User.class,User.entityName);
		
		if(user.getId()!=null) {
			criteria.add(Restrictions.eq( User.entityName+".id", user.getId()));
		}
		if(user.getUserName()!=null && !user.getUserName().isEmpty()) {
			criteria.add(Restrictions.ilike(User.entityName+".userName", user.getUserName(),MatchMode.EXACT));
			
		}
		if(user.getAge()!=null) {
			criteria.add(Restrictions.eq(User.entityName+".age", user.getAge()));
		}
		return (List<User>)criteria.list();
	}
	
	

}
