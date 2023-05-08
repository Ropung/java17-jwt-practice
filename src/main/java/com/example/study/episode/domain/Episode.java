package com.example.study.episode.domain;

import com.example.study.episode.domain.type.EpisodeStatus;
import com.example.study.support.MySchemaConstants;
import com.example.study.support.UuidBaseEntity;
import lombok.*;

import javax.persistence.*;
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
		name = MySchemaConstants.TB_EPI,
		schema = MySchemaConstants.SCHEMA
		//		catalog = MySchemaConstants.SCHEMA
)
public class Episode extends UuidBaseEntity {
	@Column
	private UUID bookId;
	@Column
	private UUID memberId;
	@Column
	private String nickname;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String review;
	@Column
	private String coverUrl;
	@Column
	private Integer viewCount;
	@Column
	@Enumerated(EnumType.STRING)
	private EpisodeStatus status;
	@Column
	@Builder.Default
	private OffsetDateTime createdAt = OffsetDateTime.now(DEFAULT_TIMEZONE_ID);
	@Column
	private OffsetDateTime updatedAt;
	@Column
	private OffsetDateTime deletedAt;
}
