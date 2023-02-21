package com.librarycommon.service;

import com.librarycommon.dao.ReviewRepository;
import com.librarycommon.entity.Book;
import com.librarycommon.entity.Review;
import com.librarycommon.exception.LibraryCommonException;
import com.librarycommon.utility.LibraryCommonUtility;
import com.librarycommon.vo.ResponseVo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    public ResponseVo getReviewByBookId(long id) {
        ResponseVo responseVo = null;
        List<Review> reviews = null;
        Map<String,Object> dataMap = new HashMap<>();
        Map<String,String> pageMap = new HashMap<>();
        Page<Review> pagedResult = null;
        try {
            Pageable paging = null;
            pagedResult = reviewRepository.findByBookId(id,paging);
            if( pagedResult != null && !pagedResult.isEmpty()) {
                reviews = pagedResult.getContent();
            }
            dataMap.put("reviews", reviews);
            dataMap.put("page",pageMap);
            responseVo = new ResponseVo();
            responseVo.setData(dataMap);
            LibraryCommonUtility.createSuccessResponse(responseVo);

        } catch (LibraryCommonException e) {

        }
        return responseVo;
    }
}
