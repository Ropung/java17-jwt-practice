package com.example.study.book.api;

import com.example.study.book.exception.BookQueryErrorCode;
import com.example.study.book.repository.projection.BookListProjection;
import com.example.study.book.service.BookQueryService;
import com.example.study.common.type.SearchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.study.book.api.dto.BookQueryDto.BookReadResponseDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Log4j2
public class BookQueryApi {
	private final BookQueryService bookQueryService;
	private final Integer PAGE_SIZE = 12;
	
	// Restful(over) -> initial CRUD
	// vs just Rest한 API
	@GetMapping(path = "/genre/{genreEng}")
	public BookReadResponseDto findAllByGenre(
			@PathVariable String genreEng,
			// 0~
			@PageableDefault(size=12, sort="createAt", direction = Sort.Direction.DESC)
			Pageable pageable,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) SearchType searchType){
		if (pageable.getPageNumber() != 0) pageable = pageable.previousOrFirst();
		Long lastPageNumber =
				bookQueryService.countByGenre(genreEng) / PAGE_SIZE
						+ (bookQueryService.countByGenre(genreEng) % PAGE_SIZE != 0? 1 : 0);
		
		if (pageable.getPageNumber() >= lastPageNumber) {
			throw BookQueryErrorCode.PAGE_OUT_OF_RANGE.defaultException();
		}
		
		// TODO Search by ...
		List<BookListProjection> books =
				bookQueryService.findAllByGenre(genreEng, pageable);
		
		return BookReadResponseDto.builder()
				.books(books)
				.lastPage(lastPageNumber)
				.build();
	}
}
// GET /genre/1/books
// GET /genre/1/books/1
// POST /genre/1/books
// PUT /genre/1/books/Q9ADFDAF
// DELETE /genre/Q9DF7A/books/1
// "/genre/{genreId}/books/{bookId}"

// private ID public CODE(ID)

// GET /books/genre/