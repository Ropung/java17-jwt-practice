package com.example.study.sample.service;

import com.example.study.sample.properties.SampleImageProperties;
import com.example.study.util.upload.DailyFileNameGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.example.study.sample.dto.SampleImageUploadDto.SampleImageUploadResponseDto;

@Service
@RequiredArgsConstructor
@Log4j2
public class LocalSampleImageUploadService implements SampleImageUploadService {
	
	private final SampleImageProperties sampleImageProperties;
	private final DailyFileNameGenerator dailyFileNameGenerator;
	
	@Override
	public SampleImageUploadResponseDto upload(MultipartFile file) {
		String url = sampleImageProperties.uploadUrl();
		
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
		
		return SampleImageUploadResponseDto.builder()
				.url(url)
				.build();
	}
}
