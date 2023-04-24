package com.example.study.properties.cors;

import com.example.study.properties.cors.allowed.CorsAllowedProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "app.security.cors")
@ConfigurationPropertiesBinding
public record CorsProperties(
		@NestedConfigurationProperty CorsAllowedProperties allowed,
		String[] exposedHeaders
) {
	public CorsProperties {
		if (exposedHeaders == null || exposedHeaders.length == 0) {
			exposedHeaders = new String[] {"*"};
		}
	}
}
