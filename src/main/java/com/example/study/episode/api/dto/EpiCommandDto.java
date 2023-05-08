package com.example.study.episode.api.dto;


import lombok.Builder;

import javax.validation.constraints.Size;
import java.util.UUID;

public record EpiCommandDto() {

	public record EpiAddRequestDto(
			UUID bookId,
			@Size(max = 30,min = 3, message = "회차 제목은 최소 3~30자리 입니다.")
			String title,
			String content,
			@Size(max = 100,min = 3, message = "회차 제목은 최소 3~100자리 입니다.")
			String review
	) {}
	@Builder
	public record EpiAddResponsetDto(
			boolean success
	) {}
}
