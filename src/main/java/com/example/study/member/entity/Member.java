package com.example.study.member.entity;

import com.example.study.member.entity.type.AccountStatus;
import com.example.study.member.entity.type.Gender;
import com.example.study.support.MySchemaConstants;
import com.example.study.support.UuidBaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static com.example.study.support.Constants.DEFAULT_TIMEZONE_ID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = MySchemaConstants.TB_MEMBER,
		schema = MySchemaConstants.SCHEMA
//		catalog = MySchemaConstants.SCHEMA
)
public class Member extends UuidBaseEntity {
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String nickname;
	@Column
	@Enumerated(EnumType.STRING)
	private AccountStatus status = AccountStatus.ACTIVE;
	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column
	@Builder.Default
	private OffsetDateTime createdAt = OffsetDateTime.now(DEFAULT_TIMEZONE_ID);
	
}

