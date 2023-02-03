package com.librarycommon.controller;

import com.librarycommon.dao.BookRepository;
import com.librarycommon.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getAllBooks()");
        }
        List<Book> allBooks = null;
        try {
            allBooks = bookRepository.findAll();
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception inside getAllBooks()");
            }
        }
        return allBooks;
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable(name = "bookId") Long bookId) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getBook()");
        }
        Book book = null;
        try {
            Optional<Book> bookPresent = bookRepository.findById(bookId);
            if (bookPresent.isPresent()) {
                book = bookPresent.get();
            }
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception inside getBook()");
            }
        }
        return book;
    }
}
