package com.coma.study.common.schedule;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.coma.study.member.MemberDAO;

@Component
public class TestScheduler {
	@Autowired
	private MemberDAO memberDAO;
	
	// @Scheduled(fixedRate = 1000, initialDelay = 5000)
	public void method1() throws Exception {
		System.out.println(LocalTime.now());
		System.out.println("===== method1 =====");
		Thread.sleep(3000);
		System.out.println(LocalTime.now());
	}
	
	// @Scheduled(fixedDelay = 1000, initialDelay = 2000)
	public void method2() throws Exception {
		System.out.println(LocalTime.now());
		System.out.println("===== method2 =====");
		Thread.sleep(3000);
		System.out.println(LocalTime.now());
	}
	
	@Scheduled(cron = "3 * * * * *")
	public void method3() throws Exception {
		System.out.println(LocalTime.now());
		System.out.println("===== method3 =====");
	}
}
