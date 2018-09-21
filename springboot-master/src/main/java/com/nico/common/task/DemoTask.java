package com.nico.common.task;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nico.web.mybatis.entity.SysNotes;
import com.nico.web.service.SysNotesService;

@Component
public class DemoTask {
	@Autowired
	private SysNotesService sysNotesService;
	
	@Scheduled(cron="0/30 * * * * ?")   //每30秒执行一次    
	@PostConstruct
	public void run(){
		System.out.println("任务开始中·············");
		SysNotes sysNotes=new SysNotes();
		sysNotes.setTitile("测试通知");
		sysNotes.setUrl("测试地址-"+RandomUtils.nextInt());
		sysNotes.setContent("测试任务-"+RandomUtils.nextInt());
		sysNotesService.insert(sysNotes);
	}
}
