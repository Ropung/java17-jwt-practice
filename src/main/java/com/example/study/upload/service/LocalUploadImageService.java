package com.example.study.upload.service;

import com.example.study.common.properties.upload.UploadImageProperties;
import com.example.study.util.upload.DailyFileNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static com.example.study.upload.api.dto.UploadImageDto.UploadImageResponseDto;

@Service
@RequiredArgsConstructor
@Log4j2
public class LocalUploadImageService implements UploadImageService{
	
	private final UploadImageProperties uploadImageProperties;
	private final DailyFileNameGenerator dailyFileNameGenerator;
	@Override
	public UploadImageResponseDto upload(MultipartFile file) {
		String url = uploadImageProperties.uploadUrl();
		
		url = url.endsWith("/") ? url : url + "/";
		
		if (!file.isEmpty()) {
			url += dailyFileNameGenerator.generateWithFullPath(file.getOriginalFilename());
			log.info("파일 저장 fullPath={}", url);
			
			File folder = new File(url);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			
			try {
				file.transferTo(new File(url));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return UploadImageResponseDto.builder()
				.url(url)
				.build();
	}
}
