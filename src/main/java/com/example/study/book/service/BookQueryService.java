package com.example.study.book.service;

import com.example.study.book.repository.projection.BookListProjection;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookQueryService {
	List<BookListProjection> findAllByGenre(String genreEnglishName, Pageable pageable);
	
	Long countByGenre(String genreEnglishName);
}
