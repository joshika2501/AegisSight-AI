package com.aegissight.common.infrastructure.dto;

import java.util.List;

public record PageResponse<T>(
    List<T> items,
    PageDetails page
) {
    public record PageDetails(
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext
    ) {}
}
