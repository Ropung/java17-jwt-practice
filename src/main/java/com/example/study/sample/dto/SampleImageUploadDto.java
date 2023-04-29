package com.example.study.sample.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

public record SampleImageUploadDto() {
	public record SampleImageUploadRequestDto(
			// One vs Many -> One
			MultipartFile file
	) {}
	
	@Builder
	public record SampleImageUploadResponseDto(
			String url
	) {}
}
