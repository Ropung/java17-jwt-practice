package com.example.study.book.api;

import com.example.study.book.api.dto.BookAddDto.BookAddRequestDto;
import com.example.study.book.api.dto.BookAddDto.BookAddResponseDto;
import com.example.study.book.service.BookCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public final class BookCommandApi {
	
	private final BookCommandService bookCommandService;
	
	@PostMapping
	public BookAddResponseDto bookAddResponseDto(
			@RequestBody @Valid BookAddRequestDto body,
			HttpServletRequest request){
		
		return new BookAddResponseDto(bookCommandService.add(body, request));
	}
}
