package com.web.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.study.iocAndDi.IocTest;
import com.web.study.iocAndDi.TestA;
import com.web.study.iocAndDi.TestB;

@SpringBootApplication
public class StudyApplication {

	private IocTest iocTest;
	
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
//		iocAndDiTest();
	}

//	public static void iocAndDiTest() {
//		// IOC (Inversion Of Control) - 제어의 역전
//		IocTest ioc = new IocTest(new TestB()); //iocTest가 생성될때 testA를 생성하겠다.
//		ioc.run();
//	}

}
