package com.example.study.book.service;

import com.example.study.book.api.dto.BookAddDto;
import com.example.study.book.domain.Book;
import com.example.study.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public final class DefaultBookCommandService implements BookCommandService {
	
	private final BookRepository bookRepository;
	
	@Override
	public boolean add(BookAddDto.BookAddRequestDto dto) {
		Book book = Book.builder()
				.bookTitle(dto.title())
				//TODO 파일 넣는법 공부해서 적용하기(파일경로로할껀지)
				.bookCover(null)
				.bookDescription(dto.description())
				.cip(dto.cip())
				.isbn(dto.isbn())
				.build();
		
		bookRepository.save(book);
		return true;
	}
}
