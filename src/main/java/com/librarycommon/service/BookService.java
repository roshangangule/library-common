package com.librarycommon.service;

import com.librarycommon.exception.LibraryCommonException;
import com.librarycommon.utility.LibraryCommonUtility;
import com.librarycommon.dao.BookRepository;
import com.librarycommon.entity.Book;
import com.librarycommon.vo.ResponseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public ResponseVo getBooks(String page, String size) {
        if (logger.isDebugEnabled()) {
            logger.info("Inside findAllBooks()");
        }
        ResponseVo responseVo = new ResponseVo();
        List<Book> book = null;
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,String> pageMap = new HashMap<>();

        try {
            if (page!= null && size != null) {
                Pageable paging = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
                Page<Book> pagedResult = bookRepository.findAll(paging);
                pageMap.put("totalElements",String.valueOf(pagedResult.getTotalElements()));
                pageMap.put("totalPages",String.valueOf(LibraryCommonUtility.getTotalPages(Integer.parseInt(size), pagedResult.getTotalElements())));

                if(pagedResult.hasContent()) {
                    book = pagedResult.getContent();
                } else {
                    book = new ArrayList<>();
                }
            } else {
                book = bookRepository.findAll();
            }
            dataMap.put("books", book);
            dataMap.put("page",pageMap);
            responseVo.setData(dataMap);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
               logger.error("Exception inside findAllBooks()");
            }
            throw new LibraryCommonException("500","Something missing in request params", new ResponseVo());
        }
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findAllBooks()");
        }
        return responseVo;
    }

    public ResponseVo getBookById(Long bookId) {
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findBookById()");
        }
        Book book = null;
        ResponseVo responseVo = null;
        try {
            Optional<Book> bookPresent = bookRepository.findById(bookId);
            if (bookPresent.isPresent()) {
                book = bookPresent.get();
            }
            responseVo = new ResponseVo();
            responseVo.setData(book);
            LibraryCommonUtility.createSuccessResponse(responseVo);
        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("Exception inside findBookById()");
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("Exit from findBookById()");
        }
        return responseVo;
    }
}
