package com.nico.web.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAOP {
	/**
	 * 方法执行前
	 * @Title: 
	 * @Description: 
	 * @date 2018年7月9日          
	 * @throws
	 */
	@Before(value = "execution(* com.nico..*.*(..))")
	public void before(){
		System.out.println("AOP:before       --------------------");
	}
	
	/**
	 * 方法返回前
	 * @Title: 
	 * @Description: 
	 * @date 2018年7月9日  
	 * @param rdata        
	 * @throws
	 */
	@AfterReturning(returning="rdata",pointcut="execution(* com.nico..*.*(..))")
	public void doAfter(Object rdata){
		System.out.println("AOP:after       --------------------");
		if(rdata!=null){
			System.out.println(rdata.getClass());
		}
		System.out.println(rdata);
	}
	
}
