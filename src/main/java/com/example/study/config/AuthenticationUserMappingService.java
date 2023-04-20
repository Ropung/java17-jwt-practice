package com.example.study.config;

import com.example.study.user.domain.User;
import com.example.study.user.repository.UserRepository;
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
	
	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		if (optionalUser.isEmpty()) throw new UsernameNotFoundException("해당하는 이메일이 없습니다.");
		
		User user = optionalUser.get();
		
		return org.springframework.security.core.userdetails.User
				.withUsername(email)
				.password(user.getPassword())
//				foo(String... params) << 가변인자
				.authorities("USER")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
	}
}
