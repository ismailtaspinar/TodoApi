package com.ismailtaspinar.todoapi.common.response;

import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public class PagedResponse<T> {
    private List<T> data;
    private PageMeta meta;
    private String message;
    private LocalDateTime timestamp;

    public PagedResponse() {
    }

    public PagedResponse(List<T> data, PageMeta meta, String message, LocalDateTime timestamp) {
        this.data = data;
        this.meta = meta;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static <T> PagedResponse<T> success(List<T> data, Page<?> page, String message) {
        PageMeta meta = new PageMeta(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
        return new PagedResponse<>(data, meta, message, LocalDateTime.now());
    }

    public List<T> getData() {
        return data;
    }

    public PageMeta getMeta() {
        return meta;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setMeta(PageMeta meta) {
        this.meta = meta;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
