package com.coma.study.transaction;

import org.springframework.stereotype.Component;

@Component
public class Transaction {
	
	public void closeAutoCommit() {
		System.out.println("Session: false");
		
	}
	
	public void commit() {
		System.out.println("Commit");
	}
}
