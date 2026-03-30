package com.ismailtaspinar.todoapi.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {
    @NotBlank(message = "title bos olamaz")
    private String title;
    private String description;
    private Boolean done;
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Boolean getDone() { return done; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setDone(Boolean done) { this.done = done; }
}
