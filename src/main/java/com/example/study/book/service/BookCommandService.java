package com.example.study.book.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.example.study.book.api.dto.BookCommandDto.BookAddRequestDto;

public interface BookCommandService {
	boolean add(BookAddRequestDto dto, MultipartFile file, HttpServletRequest request);
}
