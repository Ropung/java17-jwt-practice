package com.example.study.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public final class JwtPayloadParser {
	
	private final JwtParser jwtParser;
	private final HttpServletRequest request;
	
	public String subject() {
		return claims().getSubject();
	}
	
	public Claims claims() {
		String token = request.getHeader("Authorization");
		
		if (token == null || !token.startsWith("Bearer "))
			throw new AuthenticationCredentialsNotFoundException("Bearer 헤더에 토큰이 없습니다.");
		
		token = token.substring(7);
		return jwtParser.parseClaimsJws(token).getBody();
	}
}
