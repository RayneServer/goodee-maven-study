package com.coma.study.account;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDTO {
	private String accountNum;
	private String memberId;
	private Long productNum;
	private LocalDate accountDate;
	private Long accountBalance;
}
