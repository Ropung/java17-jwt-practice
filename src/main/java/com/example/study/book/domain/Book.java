package com.example.study.book.domain;

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
	@Column private UUID memberId;
	@Column private String memberNickname;
	@Column private UUID genreId;
	@Column private String title;
	@Column private String description;
	@Column private String coverUrl;
	@Column private String isbn;
	@Column private String cip;
	@Column @Builder.Default
	private Double avgScore = 0.;
	@Column @Builder.Default
	private OffsetDateTime createdAt = OffsetDateTime.now(DEFAULT_TIMEZONE_ID);
	@Column private OffsetDateTime updatedAt;
	@Column private OffsetDateTime deletedAt;
}

