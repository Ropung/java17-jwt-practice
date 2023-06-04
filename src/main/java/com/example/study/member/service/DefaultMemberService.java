package com.example.study.member.service;

import com.example.study.config.jwt.TokenProvider;
import com.example.study.member.domain.Member;
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
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(body.email(),body.rawPassword());
		Authentication authentication =
				authenticationManager.authenticate(authenticationToken);
		String nickname = memberRepository.findEmailAndNicknameByEmail(body.email()).nickname();
		
		return new MemberLoginResponseDto(tokenProvider.generateToken(authentication, nickname));
	}
}
