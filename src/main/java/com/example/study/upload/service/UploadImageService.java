package com.example.study.upload.service;

import org.springframework.web.multipart.MultipartFile;

import static com.example.study.upload.api.dto.UploadImageDto.UploadImageResponseDto;

public interface UploadImageService {
	UploadImageResponseDto upload(MultipartFile file);
}
