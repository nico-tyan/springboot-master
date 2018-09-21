package com.nico.web.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.nico.web.mybatis.entity.SysUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 
 * @since 2018-06-22
 */
public interface SysUserService extends IService<SysUser> {
	
	public List<SysUser> findByName(String name);
	
	public List<SysUser> findByNames(String... names);
	
	public Page<SysUser> selectListPage(SysUser sysUser);

	public List<Map> selectAll();
}
