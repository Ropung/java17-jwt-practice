package com.example.study.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static com.example.study.upload.api.dto.UploadImageDto.UploadImageResponseDto;

@Service
public final class S3UploadImageService implements UploadImageService {
	
	@Override
	public UploadImageResponseDto upload(MultipartFile file) {
		//TODO S3적용시 참고
		throw new Error("S3UploadImageService: 추후에 필요하면 넣을 예정");
	}
}
