package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.lecturer.InstructorReqDto;
import com.web.study.dto.response.InstructorRespDto;
import com.web.study.repository.InstructorRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService  {
	
	private final InstructorRespository instructorRespository;
	
	@Override
	public void registInstructor(InstructorReqDto instructorReqDto) {
		System.out.println(instructorReqDto);
		instructorRespository.saveInstructor(instructorReqDto.toEntity());
	}
	@Override
	public List<InstructorRespDto> getInstructorAll() {
		List<InstructorRespDto> dtos = new ArrayList<>();
		
		instructorRespository.getInstructorAll().forEach(entity -> {
			dtos.add(entity.toDto());
		});
		return dtos;
	}
	@Override
	public InstructorRespDto findinstructorById(int id) {
		return instructorRespository.findInstructorById(id).toDto();
	}
	
}
