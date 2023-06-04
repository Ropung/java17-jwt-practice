package com.example.study.member.repository;

import com.example.study.member.domain.Member;
import com.example.study.member.repository.projection.MemberEmailNicknameProjection;
import com.example.study.member.repository.projection.MemberQueryProjection.MemberIdProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
	Optional<Member> findByEmail(String email);
	boolean existsMemberByEmail(String email);
	
	// 컬럼 이름으로 모든 회원 조회
	// List<Member> findAllBy컬럼이름(String 컬럼이름);
	
	// 여러 컬럼으로 AND 조건으로 회원들 조회
	// List<...> findAllByColumn1AndColumn2(String column1, String column2);
	
	// @Query( ... ) <-
	
	// Update 할 때 애노테이션들
	
	// ...
	
	// deleteBy...
	
	// ===
	
	// Use Projection
	MemberEmailNicknameProjection findEmailAndNicknameByEmail(String email); // TODO test
	
	Optional<MemberIdProjection> findIdByEmail(String email);
}
