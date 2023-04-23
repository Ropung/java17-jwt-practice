package com.example.study.book.service;

import static com.example.study.book.api.dto.BookAddDto.BookAddRequestDto;

public interface BookCommandService {
	boolean add(BookAddRequestDto dto);
}
