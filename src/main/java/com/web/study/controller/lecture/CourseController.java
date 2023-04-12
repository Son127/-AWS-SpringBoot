package com.web.study.controller.lecture;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.aop.annotation.CheckNameAspect;
import com.web.study.aop.annotation.ValidAspect;
import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.course.CourseReqDto;
import com.web.study.dto.request.course.CourseSearchReqDto;
import com.web.study.exception.CustomException;
import com.web.study.service.CourseService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CourseController {

	private final CourseService courseService;
	
	@PostMapping("/course")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody CourseReqDto  courseReqDto){
			
		courseService.registeCourse(courseReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofDefault());
	}
	
	@CheckNameAspect
	@GetMapping("/course/all")
	public ResponseEntity<? extends ResponseDto> getCourseAll() {
		
		return ResponseEntity.ok().body(DataResponseDto.of(courseService.getcourseAll()));
	}
	
	@ValidAspect
	@GetMapping("/search/course")
	public ResponseEntity<? extends ResponseDto> searchCourse (@Valid CourseSearchReqDto searchReqDto, BindingResult bindingResult) throws CustomException{
		
		return ResponseEntity.ok().body(DataResponseDto.of(courseService.searchCourse(searchReqDto.getType(),searchReqDto.getSearchValue())));
	}

}