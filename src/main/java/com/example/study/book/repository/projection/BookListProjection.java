package com.example.study.book.repository.projection;

import java.util.UUID;

public record BookListProjection(
		UUID id,
		UUID memberId,
		String title,
		Double avgScore,
		String coverUrl
) {
}
