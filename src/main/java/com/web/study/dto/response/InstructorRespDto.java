package com.web.study.dto.response;

import java.time.LocalDate;

import com.web.study.domain.entity.Instructor;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InstructorRespDto {
	private int id;
	private String name;
	private LocalDate birthDate;
	private Instructor instructor;
	
}
