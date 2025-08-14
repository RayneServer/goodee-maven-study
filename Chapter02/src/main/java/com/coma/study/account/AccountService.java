package com.coma.study.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coma.study.member.MemberDTO;

@Service
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;

	public int createAccount(Long productNum, MemberDTO memberDTO) throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNum(generateAccountNumber());
		accountDTO.setMemberId(memberDTO.getMemberId());
		accountDTO.setProductNum(productNum);
		
		int result = accountDAO.insertAccount(accountDTO);
		
		return result;
	}

	public int createAccountList(Long[] cartCheck, MemberDTO memberDTO) throws Exception {
		List<AccountDTO> list = new ArrayList<>();
		
		for (Long productNum : cartCheck) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setAccountNum(generateAccountNumber());
			accountDTO.setMemberId(memberDTO.getMemberId());
			accountDTO.setProductNum(productNum);
			
			list.add(accountDTO);
			
			Thread.sleep(50);
		}
		
		int result = accountDAO.insertAccountList(list);
		
		return result;
	}
	
	private String generateAccountNumber() {
		return String.valueOf(System.currentTimeMillis());
	}
}
