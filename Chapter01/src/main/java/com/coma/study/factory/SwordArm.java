package com.coma.study.factory;

import org.springframework.stereotype.Component;

// @Component
public class SwordArm implements Arm {

	@Override
	public void attack() {
		System.out.println("슉");
	}
}
