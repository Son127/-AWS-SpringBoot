package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.study.iocAndDi.TestC;


@Configuration //빈을 등록해주는..역할?
public class BinConfig {

	@Bean //라이브러리에서 가지고 와서 그 기능을 사용 하려면 빈을 사용하여 등록한다
	public TestC testC() {
		return new TestC();
	}
}
