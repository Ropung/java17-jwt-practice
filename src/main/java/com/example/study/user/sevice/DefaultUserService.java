package com.example.study.user.sevice;

import com.example.study.config.jwt.TokenProvider;
import com.example.study.user.domain.User;
import com.example.study.user.domain.type.AccountStatus;
import com.example.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.study.user.api.dto.UserRegisterDto.*;
@Service
@RequiredArgsConstructor
public final class DefaultUserService implements UserService {
	
	
	private final UserRepository userRepository;
//	private final MemberMapper memberMapper;
	private final AuthenticationManager authenticationManager;
	
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	
	
	public boolean checkEmailExists(String email) {
		return userRepository.existsUsersByEmail(email);
	}
	
	@Override
	public boolean signUp(UserSignUpRequestDto dto) {
		
		boolean check = checkEmailExists(dto.email());
		
		if (check) {
			throw new IllegalArgumentException("이미 존재하는 유저입니다.");
		}
		
		String rawPassword = dto.rawPassword();
		String digest = passwordEncoder.encode(rawPassword);
		
		User user = User.builder()
				.email(dto.email())
				.password(digest)
				.nickname(dto.nickname())
				.status(AccountStatus.ACTIVE)
				.gender(dto.gender())
				.build();
		userRepository.save(user);
		return true;
	}
	
	@Override
	public UserLoginResponseDto login(UserLoginRequestDto body) {
		//인증에 필요한 정보를 담고, 인증 결과에 따라 인증된 사용자 정보와 권한 정보를 포함하는 Authentication 객체를 반환
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(body.email(),body.rawPassword());
		
		// 사용자 인증 및 권한 검사를 수행
		Authentication authentication =
				//인증이 성공한 경우 Authentication 객체는 인증된 사용자 정보와 권한 정보를 포함
				authenticationManager.authenticate(authenticationToken);
		
		//모든 과정이 문제가 없으면 authentication정보를 담은 토큰을 만들어줌
		return new UserLoginResponseDto(tokenProvider.generateToken(authentication));
	}
}
