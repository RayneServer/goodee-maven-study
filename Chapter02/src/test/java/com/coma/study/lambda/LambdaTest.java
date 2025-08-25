package com.coma.study.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LambdaTest {

	@Test
	void test() {
		int n1 = 10;
		int n2 = 10;
		
		TestFunction testFunction = (a, b) -> a + b;
		int result = testFunction.f1(n1, n2);
		
		// 내장된 함수형 인터페이스
		Consumer<Integer> consumer = (n) -> System.out.println(n);
		consumer.accept(3);
		
		
		System.out.println(result);
	}

}
