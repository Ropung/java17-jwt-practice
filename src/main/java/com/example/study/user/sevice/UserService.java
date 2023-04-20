package com.example.study.user.sevice;

import static com.example.study.user.api.dto.UserRegisterDto.UserSignUpRequestDto;

;

public interface UserService {
	// 파라미터는
	//  DTO 받아도 되고 Entity 받아도 됨(DTO -> Entity 변환 시점: Controller or Service)
	boolean signUp(UserSignUpRequestDto dto);
	
	String login(String email, String password);
	
	
	
	

//	boolean signUpUsingMapStruct(MemberSignUpRequestDto dto);
}
