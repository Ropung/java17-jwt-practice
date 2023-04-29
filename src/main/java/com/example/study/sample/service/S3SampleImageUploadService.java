package com.example.study.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.example.study.sample.dto.SampleImageUploadDto.SampleImageUploadResponseDto;

@Service
public final class S3SampleImageUploadService implements SampleImageUploadService {
	@Override
	public SampleImageUploadResponseDto upload(MultipartFile file) {
		throw new Error("안 만들었고 안 만들 거임. 예시로 넣어 놓음.");
	}
}
