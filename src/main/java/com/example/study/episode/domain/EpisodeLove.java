package com.example.study.episode.domain;

import com.example.study.support.MySchemaConstants;
import com.example.study.support.UuidBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(
		name = MySchemaConstants.TB_EPI_LOVE,
		schema = MySchemaConstants.SCHEMA
		//		catalog = MySchemaConstants.SCHEMA
)
public class EpisodeLove extends UuidBaseEntity {
	@Column
	private UUID memberId;
	@Column
	private UUID epiId;
	@Column
	private Boolean isEpi;
}
