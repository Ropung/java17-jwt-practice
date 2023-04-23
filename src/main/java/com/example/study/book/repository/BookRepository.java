package com.example.study.book.repository;

import com.example.study.book.domain.Book;
import com.example.study.book.repository.projection.BookListProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
	List<BookListProjection> findAllProjectedBy(Pageable pageable);
}
