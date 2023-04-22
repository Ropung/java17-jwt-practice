package com.example.study.user.api;

import com.example.study.user.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.example.study.user.api.dto.UserRegisterDto.*;



@RestController
@RequiredArgsConstructor
public class UserApi {

	private final UserService userService;
	
	
	@PostMapping("/signup")
	public UserSignUpResponseDto signUp(
			@RequestBody @Valid UserSignUpRequestDto body,
			HttpServletRequest request
			)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) ip = request.getRemoteAddr();
		
		return new UserSignUpResponseDto( userService.signUp(body) );
	}
	
	@PostMapping("/login")
	public UserLoginResponseDto login(
			@RequestBody UserLoginRequestDto body,
			HttpServletRequest request
	)
	{
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) ip = request.getRemoteAddr();
		
		return userService.login(body);
	}
	
	@GetMapping("/hello")
	public String hello() {
		
		return "hello";
	}
	
//	@GetMapping("/signup/check/{email}/exists")
//	public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email) {
//		return ResponseEntity.ok(memberService.checkEmailExists(email));
//	}
}
