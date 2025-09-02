package dev.coma.study.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.coma.study.member.MemberDAO;
import dev.coma.study.member.MemberDTO;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountService {
	@Autowired
	private AccountDAO accountDAO;
	@Autowired // Collab
	private MemberDAO memberDAO;

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
		if (result > 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("memberId", memberDTO.getMemberId());
			map.put("productNums", cartCheck);
			
			memberDAO.deleteCartList(map);
		}
		
		return result;
	}
	
	private String generateAccountNumber() {
		return String.valueOf(System.currentTimeMillis());
	}
}
