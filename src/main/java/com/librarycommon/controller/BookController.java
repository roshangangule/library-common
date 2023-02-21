package com.librarycommon.controller;

import com.librarycommon.service.BookService;
import com.librarycommon.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<ResponseVo> getAllBooks(@RequestParam(required = false) String page, @RequestParam(required = false) String size) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getAllBooks()");
        }
        ResponseVo responseVo = bookService.getBooks(page,size);
        if (logger.isDebugEnabled()) {
            logger.info("Exit from getAllBooks()");
        }
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<ResponseVo> getBook(@PathVariable(name = "bookId") Long bookId) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookById(bookId);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
    @GetMapping("/books/search/title")
    public ResponseEntity<ResponseVo> getBookByTitle(
            @RequestParam(name = "title") String title,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size
    ) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookByTitle(title,page,size);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
    @GetMapping("/books/search/category")
    public ResponseEntity<ResponseVo> getBookByCategory(
            @RequestParam(name = "category") String category,
            @RequestParam(required = false) String page,
            @RequestParam(required = false) String size
    ) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside getBook()");
        }
        ResponseVo responseVo = bookService.getBookByCategory(category,page,size);
        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }
}
