package com.librarycommon.service;

import com.librarycommon.dao.BookRepository;
import com.librarycommon.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks(String page, String size) {
        List<Book> book = null;
        try {
            if (page!= null && size != null) {
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                Page<Book> pagedResult = bookRepository.findAll(paging);
                if(pagedResult.hasContent()) {
                    book = pagedResult.getContent();
                } else {
                    book = new ArrayList<Book>();
                }
            } else {
                book = bookRepository.findAll();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book findBookById(Long bookId) {
        Book book = null;
        try {
            Optional<Book> bookPresent = bookRepository.findById(bookId);
            if (bookPresent.isPresent()) {
                book = bookPresent.get();
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception inside findBookById()");
            }
        }
        return book;
    }
}
