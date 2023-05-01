package com.example.study.config;

import com.example.study.member.domain.Member;
import com.example.study.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Authentication Manager가 사용.

@Service
@RequiredArgsConstructor
public class AuthenticationUserMappingService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Member> optionalUser = memberRepository.findByEmail(email);
		
		if (optionalUser.isEmpty()) throw new UsernameNotFoundException("해당하는 이메일이 없습니다.");

		Member member = optionalUser.get();
		
		// 한 줄로 축약은 가능함.
//		Member member = optionalUser
//				.orElseThrow(() -> new UsernameNotFoundException("해당하는 이메일이 없습니다."));
//		Member member = optionalUser
//				.orElseThrow(SignupFailureErrorCode.DEFAULT::defaultException);
		
		return org.springframework.security.core.userdetails.User
				.withUsername(email)
				.password(member.getPassword())
//				foo(String... params) << 가변인자
				.authorities("USER")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}
