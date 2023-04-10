package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.course.CourseReqDto;
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
}
