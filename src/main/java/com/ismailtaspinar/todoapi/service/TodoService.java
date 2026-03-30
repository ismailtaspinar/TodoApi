package com.ismailtaspinar.todoapi.service;

import com.ismailtaspinar.todoapi.dto.TodoRequest;
import com.ismailtaspinar.todoapi.entity.Todo;
import com.ismailtaspinar.todoapi.exception.ResourceNotFoundException;
import com.ismailtaspinar.todoapi.repository.TodoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setDone(request.getDone() != null ? request.getDone() : false);
        return todoRepository.save(todo);
    }

    public Page<Todo> getAll(Boolean done, Pageable pageable) {
        if (done == null) {
            return todoRepository.findAll(pageable);
        }
        return todoRepository.findByDone(done,pageable);
    }

    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo bulunamadi: " + id));
    }

    public Todo update(Long id, TodoRequest request) {
        Todo todo = getById(id);
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setDone(request.getDone() != null ? request.getDone() : todo.getDone());
        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        Todo todo = getById(id);
        todoRepository.delete(todo);
    }

    public Page<Todo> search(String keyword, Pageable pageable) {
        String k = keyword == null ? "" : keyword.trim();
        return todoRepository
                .findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(k, k, pageable);
    }
}