package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BasicTestDto;

import lombok.Data;

@Data
class Params{
	private int age;
	private String name;
}

@RestController
public class BasicRestController {
	
	
	//get 요청의 param을 처리하는 방법 
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto) {
		
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));		
	}
	
	
	@GetMapping("/read2/{id}")						// @PathVariable(key)은 생략 불가, key값은 생략 가능 
	public ResponseEntity<? extends ResponseDto> read2(@PathVariable int id){
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "일");
		userMap.put(2, "이");
		userMap.put(3, "삼");
		userMap.put(4, "사");
		userMap.put(5, "오");
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(id)));
	}
	
	// post요청의 데이터 처리
	@PostMapping("/create")								//Json 데이터를 받을경우 앞에 @RequestBody 붙여야함
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto) {
		System.out.println("데이터 추가");
																	//
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

