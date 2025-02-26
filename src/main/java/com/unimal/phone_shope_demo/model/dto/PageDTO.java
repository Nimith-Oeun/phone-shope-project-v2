package com.unimal.phone_shope_demo.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/*
    * This class is used to create a DTO for the Page
    * this class use to response the data with pagination to the client
    *  * for paginationDTO must be much this verible if you want to use pagination at other project
 */
@Data
public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page){
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .build();
    }
}
