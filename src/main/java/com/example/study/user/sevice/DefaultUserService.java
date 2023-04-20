package com.example.study.user.sevice;

import com.example.study.config.jwt.TokenProvider;
import com.example.study.user.domain.User;
import com.example.study.user.domain.type.AccountStatus;
import com.example.study.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.study.user.api.dto.UserRegisterDto.UserSignUpRequestDto;
@Service
@RequiredArgsConstructor
public final class DefaultUserService implements UserService {
	
	
	private final UserRepository userRepository;
//	private final MemberMapper memberMapper;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
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
	public String login(String email, String password) {
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(email, password);
		
		// 검증
		Authentication authentication =
				authenticationManager.authenticate(authenticationToken);
		
		return tokenProvider.generateToken(authentication);
	}
}
