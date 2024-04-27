package com.rum.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TradeServiceApplicationTests {

	@Test
	void contextLoads() {
		TradeServiceApplication.main(new String[]{});
	}

}
