package com.coma.study.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.coma.study.member.MemberDAO;
import dev.coma.study.member.MemberDTO;

@SpringBootTest
class MemberDAOTest {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void test() throws Exception {
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId("admin");
		memberDTO.setMemberPw(passwordEncoder.encode("0000"));
		
		int result = memberDAO.updatePassword(memberDTO);
		
		assertEquals(1, result);
	}

}
