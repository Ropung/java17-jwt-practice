package com.example.study.config;

import com.example.study.config.jwt.AuthenticationFilter;
import com.example.study.config.jwt.TokenProvider;
import com.example.study.properties.jwt.JwtProperties;
import com.example.study.util.jwt.JwtPayloadParserBuilder;
import com.example.study.util.security.EncoderFactory;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
import java.security.Key;

@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final DataSource dataSource;	// 임포트 패키지 주의
	private final EncoderFactory encoderFactory;
	private final TokenProvider tokenProvider;
	private final JwtProperties jwtProperties;
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.httpBasic().disable()
				.formLogin().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
				
				.and()
				.authorizeRequests()
				.antMatchers("/login","/signup","/books/add").permitAll()
				.anyRequest().authenticated()
//				.anyRequest().permitAll()
				
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
	
	@Bean
	public JwtParser jwtParser() {
		// Parsing -> g
		byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.password());
		Key secretKey = Keys.hmacShaKeyFor(keyBytes);

		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build();
	}
	
	@Bean
	public JwtPayloadParserBuilder jwtPayloadParserBuilder(JwtParser jwtParser) {
	    return JwtPayloadParserBuilder.withJwtParser(jwtParser);
	}
	
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
