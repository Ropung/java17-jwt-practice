package com.example.study.member.exception.signup;

import com.example.study.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum SignupFailureErrorCode implements ErrorCode {
	EMAIL_ALREADY_EXISTS("이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
	// ...
	DEFAULT("회원가입이 완료되지 않았습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	
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
	public SignUpFailureException defaultException() {
		return new SignUpFailureException(this);
	}
	
	@Override
	public SignUpFailureException defaultException(Throwable cause) {
		return new SignUpFailureException(this, cause);
	}
}
