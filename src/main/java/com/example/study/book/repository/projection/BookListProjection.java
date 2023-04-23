package com.example.study.book.repository.projection;

public record BookListProjection(
		String bookTitle,
		Integer avgScore
) {
}
