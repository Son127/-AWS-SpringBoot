package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
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

}