package com.web.study.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.dto.response.LectureRespDto;
import com.web.study.repository.LectureRespository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LectureServiceImpl implements LectureService{
	
	// final -> 상수 : 초기화가 무조건 일어나야한다(초기화가 안일어나면 사용 못함)
	private final LectureRespository lectureRepository;
	
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		// dto= entity 변환 
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println(lecture);
		lectureRepository.registe(lecture);
	}
	
	@Override
	public List<LectureRespDto> searchValue(int type, String searchValue) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("searchValue", searchValue);
		
		List<LectureRespDto> dtos = new ArrayList<>();
		lectureRepository.searchLecture(map).forEach(entity -> {
			dtos.add(entity.toDto());
		});
		return dtos;
	}	

}