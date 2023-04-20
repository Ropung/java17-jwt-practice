package com.example.study.user.config;

import com.example.study.user.config.jwt.AuthenticationFilter;
import com.example.study.user.config.jwt.TokenProvider;
import com.example.study.user.util.security.EncoderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
				.and()
				.formLogin().disable()
				.httpBasic().disable()
				
				.authorizeRequests()
				.antMatchers ("/api/**", "/login/**","/signup/**").permitAll ()
				
				.and()
				.addFilterBefore(new AuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);
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
	//        UsernamePasswordAuthenticationToken authenticationToken =
	//	    new UsernamePasswordAuthenticationToken(email, password);
		    // Auth -> throws AuthenticationException
	//	    authenticationManager.authenticate(authenticationToken);
	// String token = jwtProvider.createToken(...);
	// Cookie, Header, ... -> check. (HTTPS: SSL/TLS Certification)
	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
}
