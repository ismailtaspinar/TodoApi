package com.ismailtaspinar.todoapi.dto;

import com.ismailtaspinar.todoapi.entity.Todo;

import java.time.LocalDateTime;

public class TodoResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean done;
    private LocalDateTime createdAt;

    public static TodoResponse fromEntity(Todo todo) {
        TodoResponse r = new TodoResponse();
        r.setId(todo.getId());
        r.setTitle(todo.getTitle());
        r.setDescription(todo.getDescription());
        r.setDone(todo.getDone());
        r.setCreatedAt(todo.getCreatedAt());
        return r;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getDone() {
        return done;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}