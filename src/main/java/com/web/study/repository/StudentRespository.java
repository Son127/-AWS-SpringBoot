package com.web.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.study.domain.entity.Student;
import com.web.study.dto.response.StudentRespDto;

@Mapper
public interface StudentRespository {
	
	public int saveStudent(Student student);
	public Student findById(int id);
	public List<Student> selectAll();
}
