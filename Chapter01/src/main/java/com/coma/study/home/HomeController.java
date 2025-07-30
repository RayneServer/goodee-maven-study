package com.coma.study.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coma.study.factory.Robot;

// Annotation = 설명과 실행 기능
@Controller
public class HomeController {
	@Autowired
	private Robot robot;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("home");
		
		// robot.getSwordArm().attack();
		// robot.getRifleArm().attack();
		
		return "index";
	}
}
