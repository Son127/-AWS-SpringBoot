package com.web.study.dto.response.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtTokenRespDto {
	private String grantType;
	private String accessToken;
}
