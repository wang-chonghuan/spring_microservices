package com.wang.exammsv;

import com.wang.exammsv.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ExammsvApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepository questionRepository;

	public static void main(String[] args) {
		log.info("before ExammsvApplication.run");
		SpringApplication.run(ExammsvApplication.class, args);
		log.info("after ExammsvApplication.run");
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("before data init");
		// even when doing unit test, this method can be called,
		// so the data here can be used in unit test
		questionRepository.init();
		log.info("after data init");
	}
}
