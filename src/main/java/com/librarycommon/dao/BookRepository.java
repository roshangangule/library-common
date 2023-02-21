package com.librarycommon.dao;

import com.librarycommon.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findByTitleContaining(String title, Pageable pageable);

    Page<Book> findByCategory(String category, Pageable pageable);
}
