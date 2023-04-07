package com.web.study.iocAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IocTest2 {
	
	@Qualifier("testC")
	@Autowired
	private Test test;

//	public IocTest(Test test) { //DI : 외부에서 주입
//		this.test = test;
//	}
	
	public void run() {
		test.printTest();
		System.out.println("IocTest2 ㅊㄹ");
	}
}
