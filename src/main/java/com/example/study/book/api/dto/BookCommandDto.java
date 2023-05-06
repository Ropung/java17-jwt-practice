package com.example.study.book.api.dto;

import javax.validation.constraints.Size;
import java.util.UUID;

public record BookCommandDto() {
	
	public record BookAddRequestDto(
			UUID genreId,
			@Size(max = 30, min = 3 , message = "책 제목은 3~30 자리입니다.") String title,
			@Size(max = 255, message = "책 소개는 최대 255 자리입니다.") String description,
			String isbn,
			String cip
	){}
	public record BookAddResponseDto(
			boolean success
	){}
}
