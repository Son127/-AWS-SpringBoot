package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.UserDto;

@RestController
public class BasicRestController2 {
	
	@GetMapping("/test")
	public ResponseEntity<? extends ResponseDto> test(UserDto user){
		
		String s  = user.getUsername() + user.getPhoneNumber(); 
		
		return ResponseEntity.ok().body(DataResponseDto.of(s));
	}
	
	
	
	@GetMapping("/test2/{id}")
	public ResponseEntity<? extends ResponseDto> test2(@PathVariable int id){
		
		Map<Integer,String> map = new HashMap<>();
		map.put(1, "1");
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(map.get(id)));
	}
	
	@PostMapping("/test3") //post
	public ResponseEntity<? extends ResponseDto> test3(UserDto user){
		
		return ResponseEntity.created(null).body(DataResponseDto.of(user));
	}
	
}  