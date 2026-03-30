package com.ismailtaspinar.todoapi.common.response;


import java.time.LocalDateTime;

public class ApiResponse<T> {
    private T data;
    private String message;
    private LocalDateTime timestamp;

    public ApiResponse() {
    }

    public ApiResponse(T data, String message, LocalDateTime timestamp) {
        this.data = data;
        this.message = message;
        this.timestamp = timestamp;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, LocalDateTime.now());
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
