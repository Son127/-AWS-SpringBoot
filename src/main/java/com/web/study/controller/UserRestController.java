package com.web.study.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.UserAdditionDto;

class UserStore{
	public static Map<Integer, UserAdditionDto> userMap = new HashMap<>();
}


@RestController
public class UserRestController {
	
	@PostMapping("/api/user/addition")
	public ResponseEntity<? extends ResponseDto> api(@RequestBody UserAdditionDto userAdditionDto){
		
		Map<Integer, UserAdditionDto> userMap = UserStore.userMap;
		int maxKey = 0;
		if(!userMap.keySet().isEmpty()) {//.isEmpty 비어있으면
			maxKey = Collections.max(userMap.keySet());
		}
		userMap.put(maxKey + 1, userAdditionDto);
		System.out.println(userMap);
		
		return ResponseEntity.created(null).body(DataResponseDto.of(userAdditionDto));
	}	// created () 는 url을 넣어줄수있다. 어디로 넘어갈지 정보(Url)을 넣어서 사용함 

	/**
	 * userMap에서  해당 id를 가진 객체를 응답 
	 * 	- 만약에 해당 id가 존재하지 않으면 ErrorResponse. errorMessage는 존재하지않는 id 입니다
	 */
	@GetMapping("/api/user/{id}")
	public ResponseEntity<? extends ResponseDto> getUser(@PathVariable int id){
		Map<Integer, UserAdditionDto> userMap = UserStore.userMap;
		UserAdditionDto userAdditionDto = userMap.get(id);
		
		try {
			if(userAdditionDto == null) {
				throw new RuntimeException("존재하지 않는 id 입니다.");
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e));
		}
			
		return  ResponseEntity.ok(DataResponseDto.of(userAdditionDto));
	}
	
	@GetMapping("/api/users")
	public ResponseEntity<? extends ResponseDto> getUsers(){
		
		return ResponseEntity.ok().body(DataResponseDto.of(UserStore.userMap.values()));
	}

}
