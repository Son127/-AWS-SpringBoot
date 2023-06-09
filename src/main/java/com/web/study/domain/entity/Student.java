package com.web.study.domain.entity;

import java.time.LocalDate;

import org.apache.ibatis.type.Alias;

import com.web.study.dto.response.StudentRespDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
	private int sdm_id;
	private String sdm_name;
	private LocalDate sdm_birth;
	
	public StudentRespDto toDto() {
		return StudentRespDto.builder()
				.id(sdm_id)
				.name(sdm_name)
				.birthDate(sdm_birth)
				.build();
	}
}
