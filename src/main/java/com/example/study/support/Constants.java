package com.example.study.support;

import java.time.ZoneId;

public final class Constants {
	public static final String BASE_PACKAGE = "com.example.study";
	public static final String DEFAULT_TIMEZONE_NAME = "Asia/Seoul";
	public static final ZoneId DEFAULT_TIMEZONE_ID = ZoneId.of(DEFAULT_TIMEZONE_NAME);
	
	private Constants() {}
}