package com.example.study.sample.api;

import com.example.study.sample.properties.SampleImageProperties;
import com.example.study.sample.service.SampleImageUploadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.study.sample.dto.SampleImageUploadDto.SampleImageUploadRequestDto;
import static com.example.study.sample.dto.SampleImageUploadDto.SampleImageUploadResponseDto;

@RestController
@RequestMapping("/sample")
@Log4j2
public class SampleImageUploadApi {
	private final SampleImageUploadService imageUploadService;
	
	public SampleImageUploadApi(
			SampleImageProperties sampleImageProperties,
			SampleImageUploadService s3SampleImageUploadService,
			SampleImageUploadService localSampleImageUploadService) {
		this.imageUploadService = switch (sampleImageProperties.target()) {
			case S3 -> s3SampleImageUploadService;
			case LOCAL -> localSampleImageUploadService;
		};
	}
	
	@PostMapping("/image")
	public SampleImageUploadResponseDto uploadImage(SampleImageUploadRequestDto params) {
		return imageUploadService.upload(params.file());
	}
}
