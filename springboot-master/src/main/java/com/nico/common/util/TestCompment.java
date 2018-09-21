package com.nico.common.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

/**
 * 测试@Component  Init方法和Destroy
 * @Title: 
 * @Package com.nico.common.util  
 * @Description: 
 * @author fangshu  
 * @date 2018年6月25日  
 * @version
 */
@Component
public class TestCompment {
	private static int num=1;
	public static void test(){
		num++;
		System.out.println("TestCompment.num = "+num);
	}
	@PostConstruct
	public void testInit() {
		System.out.println("TestCompment 初始化中··············");
		test();
	}
	

	@PreDestroy
	public void testDestroy() {
		System.out.println("TestCompment 销毁中··············");
		test();
	}
	
	
}
