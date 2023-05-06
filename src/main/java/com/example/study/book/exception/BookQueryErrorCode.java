package com.example.study.book.exception;

import com.example.study.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BookQueryErrorCode implements ErrorCode {
	PAGE_OUT_OF_RANGE("페이지 번호가 유효하지 않음.",HttpStatus.BAD_REQUEST),
	DEFAULT("책 조회 오류", HttpStatus.INTERNAL_SERVER_ERROR);
	
	public final String MESSAGE;
	public final HttpStatus STATUS;
	
	@Override
	public String getName() {
		return name();
	}
	
	@Override
	public HttpStatus defaultHttpStatus() {
		return STATUS;
	}
	
	@Override
	public String defaultMessage() {
		return MESSAGE;
	}
	
	@Override
	public RuntimeException defaultException() {
		return new BookQueryException(this);
	}
	
	@Override
	public RuntimeException defaultException(Throwable cause) {
		return new BookQueryException(this, cause);
	}
}
