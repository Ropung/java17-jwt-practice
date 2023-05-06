package com.example.study.book.api;

import com.example.study.book.api.dto.BookCommandDto.BookAddRequestDto;
import com.example.study.book.api.dto.BookCommandDto.BookAddResponseDto;
import com.example.study.book.service.BookCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public final class BookCommandApi {
	
	private final BookCommandService bookCommandService;
	
	@PostMapping("/add")
	public BookAddResponseDto bookAddResponseDto(
			@RequestPart(value = "coverImage", required = false) MultipartFile file,
			@ModelAttribute @Valid BookAddRequestDto body,
			HttpServletRequest request){
		return new BookAddResponseDto(bookCommandService.add(body, file, request));
	}
}
