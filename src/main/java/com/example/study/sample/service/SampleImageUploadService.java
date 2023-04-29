package com.example.study.sample.service;

import org.springframework.web.multipart.MultipartFile;

import static com.example.study.sample.dto.SampleImageUploadDto.SampleImageUploadResponseDto;

public interface SampleImageUploadService {
	SampleImageUploadResponseDto upload(MultipartFile file);
}
