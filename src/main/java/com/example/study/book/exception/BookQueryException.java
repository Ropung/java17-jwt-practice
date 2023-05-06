package com.example.study.book.exception;

import com.example.study.support.exception.CustomException;
import com.example.study.support.exception.ErrorCode;

public class BookQueryException extends CustomException {
	public BookQueryException() {
		super(BookQueryErrorCode.DEFAULT);
	}
	
	public BookQueryException(String message) {
		super(message);
	}
	
	public BookQueryException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BookQueryException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public BookQueryException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
