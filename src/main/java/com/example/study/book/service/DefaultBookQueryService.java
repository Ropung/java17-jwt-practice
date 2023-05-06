package com.example.study.book.service;

import com.example.study.book.repository.BookGenreRepository;
import com.example.study.book.repository.BookRepository;
import com.example.study.book.repository.projection.BookListProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
@Log4j2
public class DefaultBookQueryService implements BookQueryService{
	
	private final BookRepository bookRepository;
	private final BookGenreRepository bookGenreRepository;
	
	@Override
	public List<BookListProjection> findAllByGenre(
			String genreEnglishName, Pageable pageable){
//		List<BookGenre> some = bookGenreRepository.findIdByEng(genreEnglishName);
//		log.debug("findAllByGenre result: {}", some.get(0).getId());
//		return null;
		UUID genreId = bookGenreRepository.findIdByEng(genreEnglishName);
		log.debug("findAllByGenre genreId: {}", genreId);
		return bookRepository.findAllByGenreId(genreId, pageable);
	}
	
	@Override
	public Long countByGenre(String genreEnglishName) {
//		List some = bookGenreRepository.findIdByEng(genreEnglishName);
//		log.debug("count result: {}", some);
//		return null;
		UUID genreId = bookGenreRepository.findIdByEng(genreEnglishName);
		log.debug("count genreId: {}", genreId);
		return bookRepository.countByGenreId(genreId);
	}
}
