package com.coma.study.transfer;

import org.springframework.stereotype.Component;

import com.coma.study.member.MemberDTO;

@Component
public class Transfers {
	
	public void takeBus(String num) {
		System.out.println("Bus");
	}
	
	public MemberDTO takeSubway(MemberDTO memberDTO) {
		System.out.println("Subway");
		// memberDTO = new MemberDTO();
		// memberDTO.setMemberId("test");
		
		return memberDTO;
	}
	
	public void getTaxi() {
		System.out.println("Taxi");
	}
}
