package com.example.study.book.api.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.UUID;

public record BookAddDto() {
	
	// TODO 장르 아이디 받아 올 것
	public record BookAddRequestDto(
			UUID genreId,
			@Size(max = 30, min = 3) String bookTitle,
			@Size(max = 255) String bookDescription,
			MultipartFile bookCoverFile,
			String bookIsbn,
			String bookCip
	){}
	
	public record BookAddResponseDto(
			boolean success
	){}
}
