package com.wang.mgtmsv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MgtmsvApplication implements CommandLineRunner {

	@Autowired
	private InitData initData;

	public static void main(String[] args) {
		log.info("before MgtmsvApplication.run");
		SpringApplication.run(MgtmsvApplication.class, args);
		log.info("after MgtmsvApplication.run");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("before data init");
		// even when doing unit test, this method can be called,
		// so the data here can be used in unit test
		initData.init();
		log.info("after data init");
	}
}
