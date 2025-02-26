package com.unimal.phone_shope_demo.model.dto;

import lombok.Builder;
import lombok.Data;

/*
    * This class is used to create a DTO for the Pagination
    * for paginationDTO must be much this verible if you want to use pagination at other project
 */
@Builder
@Data
public class PaginationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;
}
