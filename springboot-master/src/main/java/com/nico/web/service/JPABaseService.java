package com.nico.web.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JPABaseService<ID,T> {
	T insert(T entity);
	
	void updateById(T entity);
	
	void update(T entity);
	
	T findById(ID id);
	
	List<T> find(T entity);
	
	List<T> findByMap(Map<String, Object> searchParams);
	
	Page<T> findPage(Pageable page,Map<String, Object> searchParams);
}
