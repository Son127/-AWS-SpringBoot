package com.web.study.iocAndDi;

import org.springframework.stereotype.Component;

@Component("t2")
public class TestB implements Test {
	
	@Override
	public void printTest() {
		System.out.println("TestB 클래스");
	}

}
