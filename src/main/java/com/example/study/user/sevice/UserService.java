package com.example.study.user.sevice;

import static com.example.study.user.api.dto.UserRegisterDto.*;

;

public interface UserService {
	//  DTO 받아도 되고 Entity 받아도 됨(DTO -> Entity 변환 시점: Controller or Service)
	boolean signUp(UserSignUpRequestDto dto);
	
	UserLoginResponseDto login(UserLoginRequestDto dto);
	
//	boolean signUpUsingMapStruct(MemberSignUpRequestDto dto);
}
