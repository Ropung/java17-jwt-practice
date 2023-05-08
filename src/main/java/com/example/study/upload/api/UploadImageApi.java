package com.example.study.upload.api;

import com.example.study.common.properties.upload.UploadImageProperties;
import com.example.study.upload.service.UploadImageService;
import com.example.study.util.upload.DailyFileNameGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.study.upload.api.dto.UploadImageDto.UploadImageRequestDto;
import static com.example.study.upload.api.dto.UploadImageDto.UploadImageResponseDto;

@RestController
@RequestMapping("/upload")
@Log4j2
public class UploadImageApi {
	
	private final UploadImageService uploadImageService;
	
	public UploadImageApi(
			UploadImageProperties uploadImageProperties,
			UploadImageService s3UploadImageService,
			UploadImageService localUploadImageService,
			DailyFileNameGenerator dailyFileNameGenerator
			) {
		this.uploadImageService = switch (uploadImageProperties.target()){
			case S3 -> s3UploadImageService;
			case LOCAL -> localUploadImageService;
			default -> throw new Error("안 만들었음.");
		};
	}
	
	@PostMapping("/image")
	public UploadImageResponseDto uploadImage(UploadImageRequestDto params){
		UploadImageResponseDto result =  uploadImageService.upload(params.file());
		return new UploadImageResponseDto(result.url());
	}
	
}
