package com.web.study.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.web.study.domain.entity.Student;
import com.web.study.dto.request.students.StudentReqDto;
import com.web.study.dto.response.StudentRespDto;
import com.web.study.repository.StudentRespository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

	private final StudentRespository studentRespository;
	
	@Override
	public void registeStudent(StudentReqDto studentReqDto) {
		System.out.println(studentReqDto);
		studentRespository.saveStudent(studentReqDto.toEntity());
	}

	@Override
	public StudentRespDto findByID(int id) {
		return studentRespository.findById(id).toDto();
	}
	
	@Override
	public List<Student> selectAll() {
		return null;
	}
}
