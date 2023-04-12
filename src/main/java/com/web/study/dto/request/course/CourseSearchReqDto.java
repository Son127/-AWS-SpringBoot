package com.web.study.dto.request.course;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CourseSearchReqDto {
	
	@Min(value = 1) //최소값
	@Max(value = 3) //최대값
	private int type;

	@NotBlank(message = "검색 내용을 입력해주세요") //message값이 bindingResult에 들어간다.
	private String searchValue;
}
