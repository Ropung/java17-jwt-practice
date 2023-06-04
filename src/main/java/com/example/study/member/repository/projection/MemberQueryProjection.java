package com.example.study.member.repository.projection;

import java.util.UUID;

public record MemberQueryProjection() {
	public record MemberIdProjection(
			UUID id
	) {}
}
