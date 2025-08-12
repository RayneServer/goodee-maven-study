package com.coma.study.transfer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coma.study.member.MemberDTO;

@SpringBootTest
class TransferTest {
	@Autowired
	private Transfers transfers;
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		transfers.takeBus("111");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId("user");
		transfers.takeSubway(memberDTO);
	}

}
