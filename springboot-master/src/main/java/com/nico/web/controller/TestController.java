package com.nico.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(method={RequestMethod.GET})
	public Object get(){
		
		return "is get method";
	}
	
	@RequestMapping(method={RequestMethod.POST})
	public Object post(){
		
		
		return "is post method";
	}
}
