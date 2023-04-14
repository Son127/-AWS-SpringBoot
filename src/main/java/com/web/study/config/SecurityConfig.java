package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.security.jwt.JwtAuthenticationEntryPoint;
import com.web.study.security.jwt.JwtAuthenticationFilter;
import com.web.study.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{ 
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.httpBasic().disable(); //웹 기본 인증방식 .disable 안쓰겠다.
		http.formLogin().disable(); // 폼태그를 통한 로그인 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 세션 비활성화(무상태성). jwt는 세션을 안쓰기위해 사용
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()															//고정 ↓ (사용자 인증필터)↓
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint); // filter
	}
}