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
        if (logger.isDebugEnabled()) {
            logger.info("Inside findAllBooks()");
        }
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
            if (logger.isErrorEnabled()) {
               logger.error("Exception inside findAllBooks()");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findAllBooks()");
        }
        return book;
    }

    public Book findBookById(Long bookId) {
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findBookById()");
        }
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
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findBookById()");
        }
        return book;
    }
}
