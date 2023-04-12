package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.course.CourseReqDto;
import com.web.study.dto.response.CourseRespDto;
import com.web.study.repository.CourseRespository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{

	private final CourseRespository courseRespository;
	
	@Override
	public void registeCourse(CourseReqDto courseReqDto) {
		courseRespository.saveCourse(courseReqDto.toEntity());
	}
	
	@Override
	public List<CourseRespDto> getcourseAll() {
		List<CourseRespDto> dtos = new ArrayList<>();
		courseRespository.getCourseAll().forEach(entity ->{
			dtos.add(entity.toDto());
		});
		return dtos;
	}
	
	@Override
	public List<CourseRespDto> searchCourse(int type,String searchValue) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("type", type);
		parameterMap.put("searchValue", searchValue);
		
		List<CourseRespDto> dtos = new ArrayList<>();
		courseRespository.searchCourse(parameterMap).forEach(entity ->{
			dtos.add(entity.toDto());
		});
		
		return dtos;
	}
}
