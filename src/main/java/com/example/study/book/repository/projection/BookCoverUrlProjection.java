package com.example.study.book.repository.projection;

import lombok.Builder;

@Builder
public record BookCoverUrlProjection(
		String coverUrl
) {}