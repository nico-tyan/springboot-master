package com.nico.web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.nico.web.hibernate.dao.BaseJpaDao;
import com.nico.web.service.JPABaseService;

/**
 * 
 * @Title: 
 * @Package com.nico.web.service.impl  
 * @Description: 
 * @author fangshu  
 * @date 2018年7月19日  
 * @version
 * @param <T>
 */
public class JPABaseServiceImpl<ID extends Serializable,D extends BaseJpaDao<T,ID>, T> implements JPABaseService<ID,T> {
	@Autowired
	protected D baseDao;
	@Autowired
	protected EntityManager entityManager;

	@Override
	public T insert(T entity) {
		return baseDao.save(entity);
	}

	@Override
	public void updateById(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.save(entity);
	}

	@Override
	public T findById(ID id) {
		return baseDao.getOne(id);
	}

	@Override
	public List<T> find(T t) {
		// 创建匹配器，即如何使用查询条件
		ExampleMatcher matcher = ExampleMatcher.matching(); // 构建对象
		matcher
		.withStringMatcher(StringMatcher.DEFAULT)// 改变默认字符串匹配方式
		.withIgnorePaths("focus") //忽略属性：是否关注。因为是基本类型，需要忽略掉
		.withIgnoreCase(true); // 改变默认大小写忽略方式：忽略大小写
		//StringMatcher.DEFAULT 默认查询 不忽略大小写   field = ?   忽略大小写  LOWER(field)=LOWER(?)
		//StringMatcher.EXACT  普通查询-比较 (同默认) 不忽略大小写   field = ?   忽略大小写  LOWER(field)=LOWER(?)
		//StringMatcher.CONTAINING ：模糊查询-包含  不忽略大小写   field like '%'+?+'%'   忽略大小写  LOWER(field) like '%'+LOWER(?)+'%'
		
		//创建查询实列
		Example<T> ex = Example.of(t, matcher); 
		
		return baseDao.findAll(ex);
	}
	
	@Override
	public List<T> findByMap(Map<String, Object> searchParams) {
		Specification<T> spec = buildSpecification(new HashMap<>());
		return baseDao.findAll(spec);
	}

	@Override
	public Page<T> findPage(Pageable page,Map<String, Object> searchParams) {
		//Pageable  pageRequest = new PageRequest(page.getNumber() - 1, page.getSize(), page.getSort());
		Specification<T> spec = buildSpecification(new HashMap<>());
		return baseDao.findAll(spec, page);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<T> buildSpecification(final Map<String, Object> searchParams) {
		
		Specification<T> spec = new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> list = new ArrayList<Predicate>();
				for (String column : searchParams.keySet()) {
					list.add(criteriaBuilder.equal(root.get(column).as(String.class), searchParams.get(column)));
				}
				Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
			}
		};
		return spec;
	}

}
