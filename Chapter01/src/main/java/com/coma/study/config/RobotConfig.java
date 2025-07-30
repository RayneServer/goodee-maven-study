package com.coma.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coma.study.factory.Arm;
import com.coma.study.factory.GunArm;

@Configuration // 설정 클래스: 스프링 컨테이너에 Bean을 정의할 수 있음
public class RobotConfig {
	
	@Bean // 스프링이 자동으로 생성하는 대신 개발자가 수동으로 Bean을 등록
	Arm getGunArm() {
		return new GunArm();
	}
	
}
