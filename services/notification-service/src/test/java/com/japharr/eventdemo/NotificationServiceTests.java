package com.japharr.eventdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ("spring.cloud.discovery.enabled = false"))
class NotificationServiceTests {

	@Test
	void contextLoads() {
	}

}
