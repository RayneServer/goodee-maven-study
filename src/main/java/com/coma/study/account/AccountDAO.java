package com.coma.study.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	int insertAccount(AccountDTO accountDTO) throws Exception;
	int insertAccountList(List<AccountDTO> list) throws Exception;

}
