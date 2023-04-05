package com.web.study.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;

//@Controller // 무조건 뷰를 리턴
@RestController // 메소드에 @ResponseBody를 항상 붙이고 있는거다
public class BasicController {
	
//	@ResponseBody
	@GetMapping("/view/test") //<? extends ResponseDto> ResponseDto 상속받을경우 다 괜찮다 
	public ResponseEntity<? extends ResponseDto> view() { //응답이 String일 경우에만 text

		List<String> strList = new ArrayList<>();
		strList.add("a");
		strList.add("b");
		strList.add("d");
		strList.add("c");
		strList.add("e");
		
		if(strList.contains("e")) {
			try {
				throw new RuntimeException("오류남");
			}catch(Exception e) {//internalServerError 500에러
				return ResponseEntity.internalServerError().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST,e));
			}
		}
		
		return ResponseEntity.ok(DataResponseDto.of(strList)); // 응답인터페이스
		
		
	}
}
