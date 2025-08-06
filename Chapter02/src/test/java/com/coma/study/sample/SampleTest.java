package com.coma.study.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleTest {

	@Test
	void test() {
		int n = 9;
		String str = "";
		
		while (n != 0) {
			str = String.valueOf((n % 4)) + str;
			n /= 4;
		}
		
		int result = Integer.parseInt(str);
		System.out.println(result);
	}

	@Test
	void test2() {
		int price = 34500;
		int money = 50000;
		int back = money - price;
		
		int manWon = back / 10000;
		back %= 10000;
		
		int cheonWon = back / 1000;
		back %= 1000;
		
		int baekWon = back / 100;
		System.out.println("만원 " + manWon + "장");
		System.out.println("천원 " + cheonWon + "장");
		System.out.println("백원 " + baekWon + "개");
	}
}
