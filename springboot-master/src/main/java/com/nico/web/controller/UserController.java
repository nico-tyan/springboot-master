package com.nico.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nico.web.hibernate.entity.User;
import com.nico.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/get")
	@ResponseBody
	public Object get(Long id) {

		return userService.findById(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object cache(User entity) {
		userService.update(entity);
		return "OK";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Object add(User entity) {

		return userService.insert(entity);
	}
	
	@RequestMapping("/find")
	@ResponseBody
	public Object find(User entity) {

		return userService.find(entity);
	}
	
	@RequestMapping("/findPage")
	@ResponseBody
	public Object findPage(int pageNum,int pageSize,User entity) {
		Map<String,Object> map=new HashMap();
		map.put("loginName", entity.getLoginName());
		map.put("username", entity.getUsername());
		Pageable pageable = new PageRequest(pageNum-1, pageSize, new Sort(Sort.Direction.ASC,"userId"));
		
		return userService.findPage(pageable, map);
	}
	
	@RequestMapping("/hql")
	@ResponseBody
	public Object hql(User entity) {
		String hql="select _u from User _u where loginName= :loginName ";
		return userService.findHqlByUser(hql, entity);
	}
	
}
