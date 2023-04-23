package com.example.study.member.sevice;

import com.example.study.config.jwt.TokenProvider;
import com.example.study.member.domain.member.Member;
import com.example.study.member.domain.type.AccountStatus;
import com.example.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

import static com.example.study.member.api.dto.MemberRegisterDto.*;
@Service
@RequiredArgsConstructor
public final class DefaultMemberService implements MemberService {
	
	private final MemberRepository memberRepository;
	
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;
	
	//	private final MemberMapper memberMapper;
	
	public boolean checkEmailExists(String email) {
		return memberRepository.existsMemberByEmail(email);
	}
	
	@Override
	public boolean signUp(MemberSignUpRequestDto dto) {
		
		boolean check = checkEmailExists(dto.email());
		
		if (check) {
			throw new IllegalArgumentException("이미 존재하는 유저입니다.");
		}
		
		String rawPassword = dto.rawPassword();
		String digest = passwordEncoder.encode(rawPassword);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		OffsetDateTime birth = OffsetDateTime.parse(dto.birth() + "T00:00:00+09:00");
		
		Member member = Member.builder()
				.email(dto.email())
				.password(digest)
				.name(dto.name())
				.nickname(dto.nickname())
				.phone(dto.phone())
				.gender(dto.gender())
				.birth(birth)
				.status(AccountStatus.ACTIVE)
				.build();
		memberRepository.save(member);
		return true;
	}
	
	@Override
	public MemberLoginResponseDto login(MemberLoginRequestDto body) {
		//인증에 필요한 정보를 담고, 인증 결과에 따라 인증된 사용자 정보와 권한 정보를 포함하는 Authentication 객체를 반환
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(body.email(),body.rawPassword());
		
		// 사용자 인증 및 권한 검사를 수행
		Authentication authentication =
				//인증이 성공한 경우 Authentication 객체는 인증된 사용자 정보와 권한 정보를 포함
				authenticationManager.authenticate(authenticationToken);
		
		//모든 과정이 문제가 없으면 authentication정보를 담은 토큰을 만들어줌
		return new MemberLoginResponseDto(tokenProvider.generateToken(authentication));
	}
}
