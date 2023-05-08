package com.example.study.episode.repository.projection;

import java.util.UUID;

public record EpiListProjection(
		UUID id,
		UUID memberId,
		String title,
		Double avgScore,
		String coverUrl
) {}





