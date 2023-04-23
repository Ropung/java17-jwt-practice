package com.example.study.book.service;

import javax.servlet.http.HttpServletRequest;

import static com.example.study.book.api.dto.BookAddDto.BookAddRequestDto;

public interface BookCommandService {
	boolean add(BookAddRequestDto dto, HttpServletRequest request);
}
