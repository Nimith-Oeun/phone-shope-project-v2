package com.unimal.phone_shope_demo.service.utill;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/*
    * pageable = pagination
 */
public interface Pagination {
    int DEFAULT_PAGE_LIMIT = 2;
    int DEFAULT_PAGE_NUMBER = 1;
    String PAGE_LIMIT = "_limit"; // key for page size
    String PAGE_NUMBER = "_page"; // key for page number

    static Pageable getPageable(int pageNumber, int pageSize) {
        if (pageNumber < DEFAULT_PAGE_NUMBER) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_LIMIT;
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return pageable;
    }
}
