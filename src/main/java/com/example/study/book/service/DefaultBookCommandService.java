package com.example.study.book.service;

import com.example.study.book.api.dto.BookAddDto.BookAddRequestDto;
import com.example.study.book.domain.Book;
import com.example.study.book.repository.BookRepository;
import com.example.study.member.repository.MemberRepository;
import com.example.study.util.jwt.JwtPayloadParser;
import com.example.study.util.jwt.JwtPayloadParserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public final class DefaultBookCommandService implements BookCommandService {
	
	private final BookRepository bookRepository;
	private final MemberRepository memberRepository;
	private final JwtPayloadParserBuilder jwtPayloadParserBuilder;
	
	@Override
	public boolean add(BookAddRequestDto dto, HttpServletRequest request) {
		JwtPayloadParser payloadParser = jwtPayloadParserBuilder.buildWith(request);
		String email = payloadParser.subject();
		
		// 유저 아이디
		UUID memberId = memberRepository.findIdByEmail(email)
				.orElseThrow(IllegalStateException::new)
				.id();
		
		Book book = Book.builder()
				.memberId(memberId)
//				.genreId()
				.bookTitle(dto.bookTitle())
				.bookDescription(dto.bookDescription())
				// TODO 파일 넣는법 공부해서 적용하기(파일경로로할껀지)
				.bookCover(null)
				.isbn(dto.bookIsbn())
				.cip(dto.bookCip())
				.build();
		
		bookRepository.save(book);
		return true;
	}
}
