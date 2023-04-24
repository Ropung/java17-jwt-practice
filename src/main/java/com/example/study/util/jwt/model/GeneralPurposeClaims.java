package com.example.study.util.jwt.model;

// TODO 나중에 이런 식으로 필요한 것들 매핑해서 반환 가능.
public record GeneralPurposeClaims(
		String subject,
		String[] authorities
) {
}
