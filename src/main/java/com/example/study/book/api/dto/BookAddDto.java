package com.example.study.book.api.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public record BookAddDto() {
	
	// TODO 장르 아이디 받아 올 것
	public record BookAddRequestDto(
			@Size(max = 30, min = 3) String title,
			@Size(max = 255) String description,
			MultipartFile coverFile,
			String isbn,
			String cip
	){}
	
	public record BookAddResponseDto(
			boolean success
	){}
}
