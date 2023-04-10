package com.web.study.service;

import java.util.List;

import com.web.study.dto.request.lecturer.LecturerReqDto;
import com.web.study.dto.response.LecturerRespDto;

public interface LecturerService {

	public void registLecturer(LecturerReqDto lecturerReqDto);
	public List<LecturerRespDto> getLecturerAll();
	public LecturerRespDto findlecturerById(int id);
}
