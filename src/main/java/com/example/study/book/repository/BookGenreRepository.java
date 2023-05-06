package com.example.study.book.repository;

import com.example.study.book.domain.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BookGenreRepository extends JpaRepository<BookGenre, UUID> {
	// Correct:
//	UUID findIdByEng(String eng);
	
	//	Using @Query
	@Query("select genre.id from BookGenre genre where eng = :eng")
	UUID findIdByEng(@Param("eng") String eng);
}
