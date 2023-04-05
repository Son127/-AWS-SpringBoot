package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello") //
	public Map<String, String> hello(String name) {
		
		Map<String, String> testMap = new HashMap<>();
			testMap.put("name", name);
			testMap.put("age", "54613");
			testMap.put("address", "?");
				
		return testMap ;
	}
}
