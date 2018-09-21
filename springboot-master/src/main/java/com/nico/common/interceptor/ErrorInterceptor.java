package com.nico.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class ErrorInterceptor implements HandlerInterceptor {
	private Logger log=LoggerFactory.getLogger(ErrorInterceptor.class);  

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
                          String uri = httpServletRequest.getRequestURI();
                          int status = httpServletResponse.getStatus();
                          log.info("ErrorInterceptor 进入中·············");
                          log.info("uri={},status={}",uri,status);
                          
                          switch (status){
                              case 500:
                                  modelAndView.setViewName("500");
                                  break;
                              case 400:
                                  modelAndView.setViewName("400");
                                  break;
                              case 404:
                                  modelAndView.setViewName("404");
                                  break;
                              case 302:
                                  modelAndView.setViewName("302");
                                  break;
                          }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
