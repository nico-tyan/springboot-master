package com.nico.web.service;

import java.util.List;

import com.nico.web.hibernate.entity.User;

public interface UserService extends JPABaseService<Long,User> {
	
	/**
	 * 用代码构建SQL查询
	 * @Title: 
	 * @Description: 
	 * @date 2018年7月19日  
	 * @param user
	 * @return        
	 */
	List<User> findByUser(User user);
	
	/**
	 * 用HQL查询--原生SQL查询这里不就示范了，自己注入一个jdbaTempalte进行查询
	 * @Title: 
	 * @Description: 
	 * @date 2018年7月19日  
	 * @param user
	 * @return        
	 */
	List<User> findHqlByUser(String hql,User user);
	
}
