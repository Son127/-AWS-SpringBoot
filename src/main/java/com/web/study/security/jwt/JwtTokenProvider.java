package com.web.study.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.dto.response.auth.JwtTokenRespDto;
import com.web.study.exception.CustomException;
import com.web.study.security.PrincipalUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenProvider {
	
	private final Key key;
	
	public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	
	public JwtTokenRespDto createToken(Authentication authentication) { // 토큰 생성 메서드
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});
		
		authoritiesBuilder.delete(authoritiesBuilder.length() -1, authoritiesBuilder.length());
		
		String authorities = authoritiesBuilder.toString();
		
		long now = (new Date()).getTime();
		// 1000 == 1초
		Date tokenExpiresDate = new Date(now + (1000 * 60 * 30));  // 토큰 만료 시간 (현재시간 + ~
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal();
		
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpiresDate)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build();
	}

	public boolean validateToken(String token) { //토큰 검증
		try {
			Jwts.parserBuilder() //jwt json을 자바에서 사용 가능하게 변환
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
		
			return true;
			
		}catch (SecurityException | MalformedJwtException e) {
			//Security 라이브러리에 오류가 있거나, malformedJwt - josn의 포맷이 잘못된 형식의 jwt가 들어왔을 때 예외
			//SignatureException이 포함되어 있음 
			log.info("Invalid JWT Token", e);
		}catch (ExpiredJwtException e) {
			// 토큰의 유효기간이 만료된 경우 예외
			log.info("Expired JWT Token", e);
		}catch (UnsupportedJwtException e) {
			// jwt의 형식을 지키지 않은 경우 (Header.payload.Signature) 
			log.info("Unsupported JWT Token",e);
		}catch(IllegalArgumentException e) {
			// JWT가 없을때 발생하는 오류
			log.info("IllegalArgument JWT Token", e);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
	public Authentication getAuthentication(String accessToken) {
		Claims claims = parClaims(accessToken);
		Object roles = claims.get("auth").toString();
		
		if(claims.get("auth") == null) {
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		String[] rolesArray = roles.toString().split(","); 
		
		Arrays.asList(rolesArray).forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
								//스프링에서 지원해주는 기본 user
		UserDetails userDetails = new User(claims.getSubject(),"",authorities);
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}
	
	private Claims parClaims(String accessToken) { 
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
