package com.example.study.book.domain;

import com.example.study.support.MySchemaConstants;
import com.example.study.support.UuidBaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(
		name = MySchemaConstants.TB_BOOK_GENRE,
		schema = MySchemaConstants.SCHEMA
//		catalog = MySchemaConstants.SCHEMA
)
public class BookGenre extends UuidBaseEntity {
	@Column
	private String kor;
	@Column
	private String eng;
}