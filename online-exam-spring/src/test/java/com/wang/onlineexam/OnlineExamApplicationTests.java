package com.wang.onlineexam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OnlineExamApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1 == 1).isTrue();
	}

}
