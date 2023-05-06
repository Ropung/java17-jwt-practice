package com.example.study.member.api.dto;

import com.example.study.member.domain.type.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mapstruct.EnumMapping;

import javax.validation.constraints.Pattern;

public record MemberRegisterDto() {
	public record MemberSignUpRequestDto(
		String email,
		@JsonProperty("password")
		@Pattern(
				regexp =  "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
				message = "비밀번호 양식을 확인해주세요."
		)
		String rawPassword,
		String name,
		String nickname,
		String phone,
		String birth,
		@EnumMapping()
		Gender gender
		){
		// 초깃값이나 예외 등을 줄 수 있음(소괄호 안 쓰는 생성자)
//		        public MemberSignUpRequestDto {
//		            if (gender == null) gender = "남자";
//		            // ...
//		        }
		}
		public record MemberSignUpResponseDto(
				boolean success
		){}
	
	public record MemberLoginRequestDto(
			String email,
			@JsonProperty("password")
			@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
			String rawPassword
	){}
	public record MemberLoginResponseDto(
			String token
	){}
	
	
	
}
