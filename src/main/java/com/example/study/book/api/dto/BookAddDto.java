package com.example.study.book.api.dto;

import javax.validation.constraints.Size;
import java.util.UUID;

public record BookAddDto() {
	
	public record BookAddRequestDto(
			UUID genreId,
			@Size(max = 30, min = 3) String title,
			@Size(max = 255) String description,
			String isbn,
			String cip
	){}
	
	public record BookAddResponseDto(
			boolean success
	){}
}
