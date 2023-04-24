package com.example.study.member.service;

import static com.example.study.member.api.dto.MemberRegisterDto.*;

;

public interface MemberService {
	//  DTO 받아도 되고 Entity 받아도 됨(DTO -> Entity 변환 시점: Controller or Service)
	boolean signUp(MemberSignUpRequestDto dto);
	
	MemberLoginResponseDto login(MemberLoginRequestDto dto);
	
//	boolean signUpUsingMapStruct(MemberSignUpRequestDto dto);
}
