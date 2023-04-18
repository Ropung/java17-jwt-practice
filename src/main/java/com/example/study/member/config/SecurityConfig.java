package com.example.study.member.config;

import com.example.study.member.util.security.EncoderFactory;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.security.Key;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final DataSource dataSource;	// 임포트 패키지 주의
	private final EncoderFactory encoderFactory;
	@Value("${jwt.password}")
	private String secretKeyAsString;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity hs) throws Exception {
		hs.csrf().disable()
				.authorizeRequests()
				.antMatchers( "/signup",
						"/js/**",
						"/img/**")
				.permitAll()					// 설정한 리소스의 접근을 인증 없이 사용 허가
				.anyRequest().authenticated()	// 위의 경로 이외에는 모두 로그인
				.and()
				.formLogin()					// 일반적인 폼을 이용한 로그인 처리/실패 방법을 사용
				.loginPage("/login")	// 내가 사용할 폼은 시큐리티에서 제공하는 기본 폼이 아닌 사용자가 만든 폼 사용
				.loginProcessingUrl("/login").permitAll()	// 인증처리 URL. 로그인 폼의 action 속성 값 지정
				.usernameParameter("userId")	// 로그인 폼 아이디의 name 속성
				.passwordParameter("userPw")	// 로그인 폼 비밀번호의 name 속성
				.and()
				.logout()
				.logoutSuccessUrl("/").permitAll()	// 로그아웃 성공 시에 이동할 URL
				.and()
				.cors()
				.and()
				.httpBasic();
		
		return hs.build();
	}
	
	// 인증용 쿼리
	// @Autowired
	// public void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	// 	String userNameQueryforEnabled =
	// 			"select userId username, userPw password, enabled " +
	// 			"from member " +
	// 			"where userId = ?";
	
	// 	String userNameQueryforRole =
	// 			"select userId username, roleName role_name " +
	// 			"from member " +
	// 			"where userId = ?";
	
	// 	auth.jdbcAuthentication()
	// 	.dataSource(dataSource)
	// 	.usersByUsernameQuery(userNameQueryforEnabled)
	// 	.authoritiesByUsernameQuery(userNameQueryforRole);
	// }
	
	// 단방향 비밀번호 암호화
	@Bean
	public PasswordEncoder passwordEncoder() {
		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// return encoderFactory.createEncoder(EncoderType.BCRYPT);
		return encoderFactory.defaultEncoder();
	}
	
	@Bean
	public JwtParser jwtParser() {
		// Parsing -> g
		byte[] keyBytes = Decoders.BASE64.decode(secretKeyAsString);
		Key secretKey = Keys.hmacShaKeyFor(keyBytes);
		
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build();
	}
	
	// jwt token -> create(: provider)
	// jwt parser
	// authentication manager bean
	
	// TODO below codes
	
	/*
	*   UsernamePasswordAuthenticationToken authenticationToken =
	    new UsernamePasswordAuthenticationToken(email, password);
	
	    // Auth -> throws AuthenticationException
	    authenticationManager.authenticate(authenticationToken);
* */
	// String token = jwtProvider.createToken(...);
	// Cookie, Header, ... -> check. (HTTPS: SSL/TLS Certification)
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
