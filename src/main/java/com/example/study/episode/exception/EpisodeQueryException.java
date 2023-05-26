package com.example.study.episode.exception;

import com.example.study.support.exception.CustomException;
import com.example.study.support.exception.ErrorCode;

public class EpisodeQueryException extends CustomException {
	public EpisodeQueryException() {
		super(EpisodeQueryErrorCode.DEFAULT);
	}
	
	public EpisodeQueryException(String message) {
		super(message);
	}
	
	public EpisodeQueryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EpisodeQueryException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public EpisodeQueryException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
