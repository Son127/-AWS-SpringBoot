package com.web.study.service;

import java.util.List;

import com.web.study.dto.request.lecturer.InstructorReqDto;
import com.web.study.dto.response.InstructorRespDto;

public interface InstructorService {

	public void registInstructor(InstructorReqDto instructorReqDto);
	public List<InstructorRespDto> getInstructorAll();
	public InstructorRespDto findinstructorById(int id);
}
