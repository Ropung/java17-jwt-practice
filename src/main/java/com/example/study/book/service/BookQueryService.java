package com.example.study.book.service;

import com.example.study.book.repository.projection.BookListProjection;
import com.example.study.common.type.SearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryService {
	
	Page<BookListProjection> searchWithGenreBy(String genreEnglishName, SearchType searchType, String keyword, Pageable pageable);
}
