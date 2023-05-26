package com.example.study.config.jwt;

import com.example.study.common.properties.jwt.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
	
	// 키 선언
	private final Key key;
	
	// properties에서 비밀키를 넣어주면 HMAC알고리즘화된 키를 반환 그게 위에키에 대입됨
	public TokenProvider(JwtProperties jwtProperties) {
		// Proerties에서 받은 비밀키를 64바이트로 디코드
		byte[] secretByteKey = Decoders.BASE64.decode(jwtProperties.password());
		// HMAC 알고리즘에서 사용할 수 있는 비밀 키를 생성
		this.key = Keys.hmacShaKeyFor(secretByteKey);
	}
	
	// 검증된 authentication를 넣어 토큰생성 설정등을 요기서함
	public String generateToken(Authentication authentication, String nickname) {
		
		// header 설정
//		Map<String, Object> header = new HashMap<>();
//		header.put("alg", "HS256");
//		header.put("typ", "JWT");
		
		//payload 설정
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority ::getAuthority)
				.collect(Collectors.joining(","));
		String email = authentication.getName();
		
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("auth", authorities);
		claims.put("nickname", nickname);
		
		//Access Token 생성
		return Jwts.builder()
				// JWT를 소유하고 있는 사용자의 식별자 정보를 저장하는 데 사용
				.setSubject(authentication.getName())
				// payload 넣어주는곳
				.setClaims(claims)
				//토큰 기한
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 30))
				// Signature
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	
	// 로그인할때 시큐리티에서 유저인증정보와 엑세스토큰을 대조해서 시큐리티에서 작업할수있게 만들어주는애
	public Authentication getAuthentication(String accessToken) {
		
		//액세스 토큰의 페이로드를 claims객체로 파싱
		Claims claims = parseClaims(accessToken);
		
		// 페이로드에 저장된 auth를 확인
		if (claims.get("auth") == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}
		
		// JWT 토큰에서 권한 정보를 추출
		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
						.map(SimpleGrantedAuthority::new)
						.collect(Collectors.toList());
		//User 객체는 Spring Security에서 제공하는 인증 정보 객체로 만드는작업
		UserDetails principal = new User(claims.getSubject(), "", authorities);
		//인증된 사용자를 나타내는 Spring Security에서 제공하는 객체
		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}
	
	
	// JWT 토큰이 유효한지 검증하는 기능
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch (io.jsonwebtoken.security.SecurityException |
		        MalformedJwtException e) {
			log.info("Invalid JWT Token", e);
		} catch (ExpiredJwtException e) {
			log.info("Expired JWT Token", e);
		} catch (UnsupportedJwtException e) {
			log.info("Unsupported JWT Token", e);
		} catch (IllegalArgumentException e) {
			log.info("JWT claims string is empty.", e);
		}
		return false;
	}
	
	//  JWT 토큰에서 Claims 객체를 파싱하여 반환하는 역할
	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
	

}
