package com.coma.study.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import dev.coma.study.account.AccountDAO;
import dev.coma.study.account.AccountDTO;

@SpringBootTest
@Transactional
class AccountDAOTest {
	@Autowired
	private AccountDAO accountDAO;
	
	@Test
	@DisplayName("Insert Account Test")
	void testInsertAccount() {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNum("1");
		accountDTO.setMemberId("admin");
		accountDTO.setProductNum(1L);
	}

	@Test
	@DisplayName("Insert Account List Test")
	void testInsertAccountList() throws Exception {
		List<AccountDTO> list = new ArrayList<>();
		
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountNum("1");
		accountDTO.setMemberId("admin");
		accountDTO.setProductNum(1L);
		list.add(accountDTO);
		
		accountDTO = new AccountDTO();
		accountDTO.setAccountNum("2");
		accountDTO.setMemberId("admin");
		accountDTO.setProductNum(2L);
		list.add(accountDTO);
		
		int result = accountDAO.insertAccountList(list);
		
		assertNotEquals("0", result);
	}

}
