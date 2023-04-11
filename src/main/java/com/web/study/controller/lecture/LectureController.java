package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {
	
	private final LectureService lectureService;

	//	  Create
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto){
		
		lectureService.registeLecture(lectureReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@GetMapping("/search/lecture")
	public ResponseEntity<? extends ResponseDto> searchValue(int type, String value){
		
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.searchValue(type, value)));
	}
	
	//	  Read
	public ResponseEntity<? extends ResponseDto> get(){

		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	//	update
	public ResponseEntity<? extends ResponseDto> modify(){

		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	//	  Delete
	public ResponseEntity<? extends ResponseDto> remove(){

		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
}
