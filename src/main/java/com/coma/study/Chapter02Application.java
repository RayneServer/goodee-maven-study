package com.coma.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Chapter02Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter02Application.class, args);
	}

}
