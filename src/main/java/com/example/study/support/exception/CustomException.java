package com.example.study.support.exception;

public class CustomException extends RuntimeException {
	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public CustomException(ErrorCode errorCode) {
		super(errorCode.defaultMessage());
	}
	
	public CustomException(ErrorCode errorCode, Throwable cause) {
		super(errorCode.defaultMessage(), cause);
	}
}
