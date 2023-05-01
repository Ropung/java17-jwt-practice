package com.example.study.member.exception.signup;

import com.example.study.support.exception.CustomException;

public class SignUpFailureException extends CustomException {
	public final SignupFailureErrorCode ERROR_CODE;
	
	public SignUpFailureException() {
		super(SignupFailureErrorCode.DEFAULT.MESSAGE);
		ERROR_CODE = SignupFailureErrorCode.DEFAULT;
	}
	
	public SignUpFailureException(String message) {
		super(message);
		ERROR_CODE = SignupFailureErrorCode.DEFAULT;
	}
	
	public SignUpFailureException(String message, Throwable cause) {
		super(message, cause);
		ERROR_CODE = SignupFailureErrorCode.DEFAULT;
	}
	
	public SignUpFailureException(SignupFailureErrorCode errorCode) {
		super(errorCode);
		ERROR_CODE = errorCode;
	}
	
	public SignUpFailureException(SignupFailureErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
		ERROR_CODE = errorCode;
	}
}