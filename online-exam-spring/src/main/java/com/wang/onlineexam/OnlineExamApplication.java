package com.wang.onlineexam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineExamApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(OnlineExamApplication.class);

	@Autowired
	private DataInitialization dataInitialization;

	public static void main(String[] args) {
		logger.info("before SpringApplication.run");
		SpringApplication.run(OnlineExamApplication.class, args);
		logger.info("after SpringApplication.run");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("before data init");
		// even when doing unit test, this method can be called, so the data here can be used in unit test
		dataInitialization.init();
		logger.info("after data init");
	}
}
