package com.web.study.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.dto.response.LecturerRespDto;
import com.web.study.repository.LecturerRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LecturerServiceImpl implements LecturerService  {
	
	private final LecturerRespository lecturerRespository;
	
	@Override
	public void registLecturer(LecturerReqDto lecturerReqDto) {
		System.out.println(lecturerReqDto);
		lecturerRespository.saveLecturer(lecturerReqDto.toEntity());
	}
	@Override
	public List<LecturerRespDto> getLecturerAll() {
		List<LecturerRespDto> dtos = new ArrayList<>();
		
		lecturerRespository.getLecturerAll().forEach(entity -> {
			dtos.add(entity.toDto());
		});
		return dtos;
	}
	@Override
	public LecturerRespDto findlecturerById(int id) {
		return lecturerRespository.findLecturerById(id).toDto();
	}
	
}
