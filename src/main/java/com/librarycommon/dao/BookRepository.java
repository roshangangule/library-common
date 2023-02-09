package com.librarycommon.dao;

import com.librarycommon.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Page<Book> findAll(Pageable pageable);
    List<Book> findAll();
}
