package com.web.study.service;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Lecture;
import com.web.study.dto.request.lecture.LectureReqDto;
import com.web.study.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LectureServiceImpl implements LectureService{
	
	private final LectureRepository lectureRepository;
	
	@Override
	public void registeLecture(LectureReqDto lectureReqDto) {
		// dto= entity 변환 
		Lecture lecture = lectureReqDto.toEntity();
		System.out.println(lecture);
		lectureRepository.registe(lecture);
	}

}