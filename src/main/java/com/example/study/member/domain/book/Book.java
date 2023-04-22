package com.example.study.member.domain.book;

import com.example.study.support.MySchemaConstants;
import com.example.study.support.UuidBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.example.study.support.Constants.DEFAULT_TIMEZONE_ID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = MySchemaConstants.TB_BOOK,
		schema = MySchemaConstants.SCHEMA
		//		catalog = MySchemaConstants.SCHEMA
)
public class Book extends UuidBaseEntity {
	@Column
	private UUID memberId;
	@Column
	private UUID genreId;
	@Column
	private String ISBN_CIP;
	@Column
	private String bookTitle;
	@Column
	private String bookDescription;
	@Column
	private String bookCover;
	@Column
	private Integer avgScore;
	@Column
	@Builder.Default
	private OffsetDateTime createdAt = OffsetDateTime.now(DEFAULT_TIMEZONE_ID);
	@Column
	private OffsetDateTime updateAt;
	@Column
	private OffsetDateTime deleteAt;


}

