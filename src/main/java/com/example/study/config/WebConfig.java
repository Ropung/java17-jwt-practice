package com.example.study.config;

// 이게 핵심.
// 여기서 편하게 쓰려고 설정 파일 작성한 거예요.
// 그리고 원래 소스 코드와 설정 파일은 나누는 게 나아요

import com.example.study.properties.cors.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	//확실히 관리가 미쳤네요 넘 효율적이네
	
	protected final CorsProperties corsProperties;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(corsProperties.allowed().origins())
				.allowedMethods(corsProperties.allowed().methods())
				.allowedHeaders(corsProperties.allowed().headers())
				.allowCredentials(corsProperties.allowed().credentials()) // for "Access-Control-Allow-Credentials"
				.exposedHeaders(corsProperties.exposedHeaders())
				// .allowCredentials(true)
				.maxAge(3600L); // seconds
	}
}
