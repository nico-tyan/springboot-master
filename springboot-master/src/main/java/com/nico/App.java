package com.nico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 项目启动入口
 * @Title: 
 * @Package com.nico.web  
 * @Description: 
 * @author fangshu  
 * @date 2018年6月22日  
 * @version
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableCaching//开启缓存注解---注解启动了Spring的缓存机制，它会使应用检测所有缓存相关的注解并开始工作，同时还会创建一个CacheManager的bean,可以被我们的应用注入使用。
public class App {
	protected final static Logger logger = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
       // app.setBannerMode(Banner.Mode.LOG);4564565465464564
        app.run(args);
        logger.info("Application is success start!");
        System.err.println("集成了jpa_hibernate");
        System.err.println("集成了mybatis_plus");
        System.err.println("集成了ehcache缓存!");
        System.err.println("sample started. http://localhost:8020/sysUser/get?id=1");
    }
}
