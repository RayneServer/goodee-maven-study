package com.coma.study.factory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// @Component // Ctrl, Serv, DAO가 아니면서 객체가 필요한 경우
// @Primary // Autowired로 찾는 대상이 2개 이상인 경우 이 객체가 우선적으로 들어감
public class GunArm implements Arm {

	@Override
	public void attack() {
		System.out.println("탕");
	}
}
