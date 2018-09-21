package com.nico.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.nico.web.hibernate.dao.UserDao;
import com.nico.web.hibernate.entity.User;
import com.nico.web.service.UserService;

@Service
public class UserServiceImpl extends JPABaseServiceImpl<Long,UserDao, User>  implements UserService {

	@Override
	public List<User> findByUser(User user) {
		// 创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching(); // 构建对象
		matcher.withStringMatcher(StringMatcher.DEFAULT)// 改变默认字符串匹配方式
				.withIgnorePaths("focus") // 忽略属性：是否关注。因为是基本类型，需要忽略掉
				.withIgnoreCase(true); // 改变默认大小写忽略方式：忽略大小写
		// StringMatcher.DEFAULT 默认查询 不忽略大小写 field = ? 忽略大小写
		// LOWER(field)=LOWER(?)
		// StringMatcher.EXACT 普通查询-比较 (同默认) 不忽略大小写 field = ? 忽略大小写
		// LOWER(field)=LOWER(?)
		// StringMatcher.CONTAINING ：模糊查询-包含 不忽略大小写 field like '%'+?+'%' 忽略大小写
		// LOWER(field) like '%'+LOWER(?)+'%'

		// 通过反射设置需要匹配的field
		Field[] declaredFields = user.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				if (field.get(user) != null) {
					// 假设我用户姓名采用“开始匹配”的方式查询
					matcher.withMatcher("userName", GenericPropertyMatchers.startsWith());
					// 假设我登录账号采用“包含匹配”的方式查询
					matcher.withMatcher("logiName", GenericPropertyMatchers.contains());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		// 创建查询实列
		Example<User> ex = Example.of(user, matcher);
		return baseDao.findAll(ex);
	}

	@Override
	public List<User> findHqlByUser(String hql,User user) {
		Query createQuery = entityManager.createQuery(hql);
		Field[] declaredFields = user.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			try {
				field.setAccessible(true);
				if (field.get(user) != null) {
					createQuery.setParameter(field.getName(), field.get(user));
					System.out.println(field.getName()+"   "+field.get(user));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return createQuery.getResultList();
	}

}
