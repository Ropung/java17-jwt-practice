package com.example.study.episode.repository.projection;

import com.example.study.episode.domain.type.EpisodeStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EpiListProjection(
		UUID id,
		UUID memberId,
		String nickname,
		String title,
		Double avgScore,
		String coverUrl,
		Integer viewCount,
		EpisodeStatus status,
		OffsetDateTime createdAt
) {}





