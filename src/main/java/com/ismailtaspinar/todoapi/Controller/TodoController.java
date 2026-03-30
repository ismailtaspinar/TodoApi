package com.ismailtaspinar.todoapi.Controller;


import com.ismailtaspinar.todoapi.common.response.ApiResponse;
import com.ismailtaspinar.todoapi.common.response.PagedResponse;
import com.ismailtaspinar.todoapi.dto.TodoRequest;
import com.ismailtaspinar.todoapi.dto.TodoResponse;
import com.ismailtaspinar.todoapi.entity.Todo;
import com.ismailtaspinar.todoapi.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TodoResponse>> create(@Valid @RequestBody TodoRequest request) {
        Todo created = todoService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(TodoResponse.fromEntity(created), "todo created"));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<TodoResponse>> getAll(
            @RequestParam(required = false) Boolean done,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Todo> page = todoService.getAll(done, pageable);
        List<TodoResponse> data = page.getContent().stream().map(TodoResponse::fromEntity).toList();
        return ResponseEntity.ok(PagedResponse.success(data, page, "todos fetched"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> getById(@PathVariable Long id) {
        Todo todo = todoService.getById(id);
        return ResponseEntity.ok(ApiResponse.success(TodoResponse.fromEntity(todo), "todo fetched"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TodoResponse>> update(@PathVariable Long id, @Valid @RequestBody TodoRequest request) {
        Todo updated = todoService.update(id, request);
        return ResponseEntity.ok(ApiResponse.success(TodoResponse.fromEntity(updated), "todo updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable Long id) {
        todoService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "todo deleted"));
    }
}
