package com.librarycommon.controller;

import com.librarycommon.service.ReviewService;
import com.librarycommon.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ResponseEntity<ResponseVo> getReviewByBookId(@RequestParam("book_id") long id) {
        ResponseVo responseVo = reviewService.getReviewByBookId(id);

        return new ResponseEntity<>(responseVo, HttpStatus.OK);
    }

}
