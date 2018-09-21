package com.nico.web.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nico.web.mybatis.entity.SysUser;
import com.nico.web.service.SysUserService;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2018-06-22
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	@Resource
	private SysUserService sysUserService;

	@RequestMapping("/get")
	@ResponseBody
	public Object get(String id) {

		return sysUserService.selectById(id);
	}
	
	@RequestMapping("/cache")
	@ResponseBody
	public Object cache(String name) {

		return sysUserService.findByName(name);
	}
	
	@RequestMapping("/nocache")
	@ResponseBody
	public Object nocache(String name) {

		return sysUserService.findByNames(name);
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Object page(SysUser sysUser) {
		System.out.println(sysUser.getUserName());
		return sysUserService.selectListPage(sysUser);
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public Object all(SysUser sysUser) {
		return sysUserService.selectAll();
	}
}

