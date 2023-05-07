package com.example.study.book.service;

import com.example.study.book.repository.BookGenreRepository;
import com.example.study.book.repository.BookRepository;
import com.example.study.book.repository.projection.BookListProjection;
import com.example.study.common.type.SearchType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
@Log4j2
public class DefaultBookQueryService implements BookQueryService{
	
	private final BookRepository bookRepository;
	private final BookGenreRepository bookGenreRepository;
	
	@Override
	public Page<BookListProjection> searchWithGenreBy(String genreEnglishName, SearchType searchType, String keyword, Pageable pageable) {
		UUID genreId = bookGenreRepository.findIdByEng(genreEnglishName);
		log.debug("findAllByGenre genreId: {}", genreId);
		return switch (searchType) {
			case TITLE ->
					bookRepository.findAllByGenreIdAndTitleContainsIgnoreCase(genreId, keyword, pageable);
			case CONTENT ->
					bookRepository.findAllByGenreIdAndDescriptionContainsIgnoreCase(genreId, keyword, pageable);
			case MEMBER_NAME ->
					bookRepository.findAllByGenreIdAndMemberNicknameContainsIgnoreCase(genreId, keyword, pageable);
			case NONE -> bookRepository.findAllByGenreId(genreId, pageable);
		};
	}
}
