package com.example.study.book.repository;

import com.example.study.book.domain.Book;
import com.example.study.book.repository.projection.BookListProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
	List<BookListProjection> findAllByGenreId(UUID genreId, Pageable pageable);
	Long countByGenreId(UUID genreId);
	
	//	List<BookListProjection> findAllProjectedBy(Pageable pageable);
	
	// 컬럼 이름으로 여러 회원 조회
	// List<Member> findAllBy컬럼이름(String 컬럼이름);

}
