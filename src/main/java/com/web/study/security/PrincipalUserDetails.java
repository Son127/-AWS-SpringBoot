package com.web.study.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PrincipalUserDetails implements UserDetails{

	private static final long serialVersionUID = -1913596722176708482L;
	
	private int userId;
	private String username;
	private String password;
	private List<String> roles; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		roles.forEach(role ->{
			authority.add(new SimpleGrantedAuthority(role));
		});
		
		return authority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	// 사용기간 만료
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//계정을 잠굼
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호 n회 오류시 계정잠굼
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 비활성 상태(이메일 인증을 완료해야하건나, 전화번호 인증을 하지 않았을때)
	@Override
	public boolean isEnabled() {
		return true;
	}

}
