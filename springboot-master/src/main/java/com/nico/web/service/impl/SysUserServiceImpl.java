package com.nico.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nico.web.mybatis.entity.SysUser;
import com.nico.web.mybatis.mapper.SysUserMapper;
import com.nico.web.service.SysUserService;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author
 * @since 2018-06-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Resource
	private SysUserMapper sysUserMapper;

	@Override
	public List<SysUser> findByName(String name) {
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
		entityWrapper.and("User_Name=?", name);

		return sysUserMapper.selectList(entityWrapper);
	}

	@Override
	public List<SysUser> findByNames(String... names) {
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
		for (String name : names) {
			entityWrapper.and("User_Name={0}", name);
		}
		return sysUserMapper.selectList(entityWrapper);
	}
	
	@Override
	@Cacheable(cacheNames = "nico",key="#sysUser.userName")
	public Page<SysUser> selectListPage(SysUser sysUser) {
		System.out.println("开始查询中················");
		Page<SysUser> page=new Page<>();
		page.setCurrent(1);
		page.setSize(2);
		EntityWrapper<SysUser> entityWrapper = new EntityWrapper<>();
		entityWrapper.setEntity(sysUser);
		entityWrapper.and("user_name like {0}", "%1");
		entityWrapper.like("user_name", "1");
		return page.setRecords(sysUserMapper.selectPage(page, entityWrapper));
		
	}

	@Override
	public List<Map> selectAll() {
		
		return sysUserMapper.selectAll();
	}

}
