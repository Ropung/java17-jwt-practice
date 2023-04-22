package com.example.study.config;

import com.example.study.config.jwt.AuthenticationFilter;
import com.example.study.config.jwt.TokenProvider;
import com.example.study.member.util.security.EncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final DataSource dataSource;	// 임포트 패키지 주의
	private final EncoderFactory encoderFactory;
	private final TokenProvider tokenProvider;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// csrf 비활성화
				.csrf().disable()
				// 세션을 무상태(stateless)로 관리하겠다는 의미
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				//fromLogin 비활성화
				.and()
				.formLogin().disable()
				// HTTP 기본 인증 기능을 비활성화
				.httpBasic().disable()
				
				.authorizeRequests()
				// /api/hello에 대한 요청은 인증이 필요 없다
				.antMatchers("/login","/signup").permitAll()
				// 나머지 요청은 인증이 필요하다
				.anyRequest().authenticated()
				
				.and()
				//인증때 토큰과, 유저가 맞는지 확인하는 설정
				.addFilterBefore(new AuthenticationFilter(tokenProvider),UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// return encoderFactory.createEncoder(EncoderType.BCRYPT);
		return encoderFactory.defaultEncoder();
	}
	
//	@Bean
//	public JwtParser jwtParser() {
//		// Parsing -> g
//		byte[] keyBytes = Decoders.BASE64.decode(secretKeyAsString);
//		Key secretKey = Keys.hmacShaKeyFor(keyBytes);
//
//		return Jwts.parserBuilder()
//				.setSigningKey(secretKey)
//				.build();
//	}
	
	// 1) jwt token -> create(: provider)
	// 2) jwt parser
	// 3) authentication manager bean
	// TODO below codes
	// UsernamePasswordAuthenticationToken authenticationToken =
	// new UsernamePasswordAuthenticationToken(email, password);
		    // Auth -> throws AuthenticationException
	//	    authenticationManager.authenticate(authenticationToken);
	// String token = jwtProvider.createToken(...);
	// Cookie, Header, ... -> check. (HTTPS: SSL/TLS Certification)
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
