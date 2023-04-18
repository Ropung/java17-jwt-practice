package com.example.study.member.sevice;

import static com.example.study.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;

;

public interface MemberService {
	// 파라미터는
	//  DTO 받아도 되고 Entity 받아도 됨(DTO -> Entity 변환 시점: Controller or Service)
	boolean signUp(MemberSignUpRequestDto dto);
	
//	boolean signUp(Member member);

//	boolean signUpUsingMapStruct(MemberSignUpRequestDto dto);
}
