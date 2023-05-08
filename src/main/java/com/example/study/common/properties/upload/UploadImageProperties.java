package com.example.study.common.properties.upload;

import com.example.study.common.properties.upload.type.ImageStorageTargetType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;


@ConfigurationProperties(prefix = "app.upload.files")
@ConfigurationPropertiesBinding
public record UploadImageProperties(
		ImageStorageTargetType target,
		String uploadUrl
) {
	public UploadImageProperties {
		if (target == null) {
			target = ImageStorageTargetType.LOCAL;
		}
		
		if (uploadUrl == null || "".equals(uploadUrl)) {
			uploadUrl = "../upload";
		}
	}
}
