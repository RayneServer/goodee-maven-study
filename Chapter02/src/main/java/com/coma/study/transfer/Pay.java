package com.coma.study.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.coma.study.member.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Pay {
	
	@Around("execution (* com.coma.study.transfer.Transfers.takeSubway(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Before Card Check");
		log.info("Args {}", joinPoint.getArgs());
		log.info("target {}", joinPoint.getTarget());
		log.info("{}", joinPoint.getKind());
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId("1234");
		
		MemberDTO[] memberDTOs = new MemberDTO[1];
		memberDTOs[0] = memberDTO;
		
		Object obj = joinPoint.proceed(memberDTOs);
		log.info("return: {}", obj);
		
		System.out.println("After Card Check");
		
		return obj;
	}
}
