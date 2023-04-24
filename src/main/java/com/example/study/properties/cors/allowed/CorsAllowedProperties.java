package com.example.study.properties.cors.allowed;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties
@ConfigurationPropertiesBinding
public record CorsAllowedProperties(
		String[] headers,
		String[] methods,
		String[] origins,
		Boolean credentials
) {
	public CorsAllowedProperties {
		if (headers == null || headers.length == 0) {
			headers = new String[] {"*"};
		}
		if (methods == null || methods.length == 0) {
			methods = new String[] {"*"};
		}
		if (origins == null) {
			origins = new String[] {};
		}
		if (credentials == null) credentials = false;
	}
}
