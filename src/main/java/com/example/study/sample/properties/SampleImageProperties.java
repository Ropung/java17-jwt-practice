package com.example.study.sample.properties;

import com.example.study.sample.properties.type.ImageStorageTargetType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "app.sample.files")
@ConfigurationPropertiesBinding
public record SampleImageProperties(
		ImageStorageTargetType target,
		String uploadUrl
) {
	public SampleImageProperties {
		if (target == null) {
			target = ImageStorageTargetType.LOCAL;
		}
		
		if (uploadUrl == null || "".equals(uploadUrl)) {
			uploadUrl = "../upload";
		}
	}
}
