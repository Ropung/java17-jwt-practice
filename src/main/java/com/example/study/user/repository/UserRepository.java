package com.example.study.user.repository;

import com.example.study.user.domain.User;
import com.example.study.user.projection.UserEmailNicknameProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);
	
	
	boolean existsUsersByEmail(String email);
	
	// 컬럼 이름으로 여러 회원 조회
	// List<Member> findAllBy컬럼이름(String 컬럼이름);
	
	// 여러 컬럼으로 AND 조건으로 회원들 조회
	// List<...> findAllByColumn1AndColumn2(String column1, String column2);
	
	// @Query( ... ) <-
	
	// Update 할 때 애노테이션들
	
	// ...
	
	// deleteBy...
	
	// ===
	
	// Use Projection
	UserEmailNicknameProjection findEmailAndNicknameByEmail(String email); // TODO test
}
