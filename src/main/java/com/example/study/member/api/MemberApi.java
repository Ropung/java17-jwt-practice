package com.example.study.member.api;

import com.example.study.member.api.dto.MemberRegisterDto;
import com.example.study.member.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.study.member.api.dto.MemberRegisterDto.*;



@RestController
@RequiredArgsConstructor
public class MemberApi {

	private final MemberService memberService;
	
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@PostMapping("/signup")
	public MemberSignUpResponseDto signUp(
			@RequestBody @Valid MemberRegisterDto.MemberSignUpRequestDto body,
			HttpServletRequest request
			)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) ip = request.getRemoteAddr();
		
		return new MemberSignUpResponseDto( memberService.signUp(body) );
	}
	
	@PostMapping("/login")
	public MemberLoginResponseDto login(
			@RequestBody MemberLoginRequestDto body,
			HttpServletRequest request
	)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) ip = request.getRemoteAddr();
		
		return memberService.login(body);
	}
	
}
