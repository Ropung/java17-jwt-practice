package com.example.study.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "jwt")
@ConfigurationPropertiesScan
@ConfigurationPropertiesBinding
public record Properties(
		//1. 토큰 Properties에서 jwt 비밀번호를 가져온다.
		String password
) {
	@Configuration
	@EnableConfigurationProperties(Properties.class)
	public static class ScanningConfig {}

	public Properties {
		if (password == null) password = "77d1b474a0920ab13e44a05170117cf0e809bad5c554d19020a95b45e9e2fb95893b8b149382e294d78fdb8e5aa2ae266b5797d985f5dc127366d2a50ec3938e";
	}
}