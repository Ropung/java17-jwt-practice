package com.example.study.member.sevice;

import com.example.study.member.entity.Member;
import com.example.study.member.entity.type.AccountStatus;
import com.example.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.study.member.api.dto.MemberRegisterDto.MemberSignUpRequestDto;
@Service
@RequiredArgsConstructor
public final class DefaultMemberService implements MemberService {
	
	private final MemberRepository memberRepository;
//	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public boolean signUp(MemberSignUpRequestDto dto) {
		
		String rawPassword = dto.rawPassword();
		String digest = passwordEncoder.encode(rawPassword);
		
		Member member = Member.builder()
				.email(dto.email())
				.password(digest)
				.nickname(dto.nickname())
				.status(AccountStatus.ACTIVE)
				.gender(dto.gender())
				.build();
		
		memberRepository.save(member);
		return true;
	}
	
//	@Override
//	public boolean signUp(Member member) {
//		member = Member.builder()
//				.email(member.getEmail())
//				.password(member.getPassword())
//				.nickname(member.getNickname())
//				.status(AccountStatus.ACTIVE)
//				.gender(Gender.M)
//				.build();
//		memberRepository.save(member);
//		return true;
//	}
}
