package com.example.study.book.api.dto;

import com.example.study.book.repository.projection.BookListProjection;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

public record BookQueryDto() {
	
	public record GetBooksRequestDto(
			String keyword
	){
		public GetBooksRequestDto {
			if (keyword != null && "".equals(keyword.trim())) keyword = null;
		}
	}
	
	@Builder
	public record GetBooksResponseDto(
			List<BookListProjection> books,
			Long lastPage
	){}
	public record GetMemberBooksReqDto(
			UUID MemberId
	){
		public GetMemberBooksReqDto {
			if (MemberId != null && "".equals(MemberId)) MemberId = null;
		}
	}
	
	@Builder
	public record GetMemberBooksResDto(
			List<BookListProjection> books,
			Long lastPage
	){}
}
