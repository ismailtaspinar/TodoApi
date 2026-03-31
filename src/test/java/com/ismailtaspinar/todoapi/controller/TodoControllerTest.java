package com.ismailtaspinar.todoapi.controller;


import tools.jackson.databind.ObjectMapper;
import com.ismailtaspinar.todoapi.Controller.TodoController;
import com.ismailtaspinar.todoapi.dto.TodoRequest;
import com.ismailtaspinar.todoapi.entity.Todo;
import com.ismailtaspinar.todoapi.exception.ResourceNotFoundException;
import com.ismailtaspinar.todoapi.service.TodoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private TodoService todoService;

    private Todo sampleTodo(Long id) {
        Todo t = new Todo();
        t.setId(id);
        t.setTitle("Spring Boot ogren");
        t.setDescription("Ilk todo");
        t.setDone(false);
        t.setCreatedAt(LocalDateTime.now());
        return t;
    }

    @Test
    @DisplayName("POST /api/v1/todos -> 201 Created")
    void createTodo_success() throws Exception {
        TodoRequest request = new TodoRequest();
        request.setTitle("Yeni todo");
        request.setDescription("Aciklama");
        request.setDone(false);
        Todo created = sampleTodo(1L);
        when(todoService.create(any(TodoRequest.class))).thenReturn(created);
        mockMvc.perform(post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
        // Wrapper kullaniyorsan:
        // .andExpect(jsonPath("$.data.id").value(1));
        // Raw entity donuyorsan:
        // .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("POST /api/v1/todos -> 400 Validation")
    void createTodo_validationFail() throws Exception {
        TodoRequest request = new TodoRequest();
        request.setTitle(""); // @NotBlank fail
        request.setDescription("Aciklama");
        mockMvc.perform(post("/api/v1/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /api/v1/todos/{id} -> 404 Not Found")
    void getById_notFound() throws Exception {
        when(todoService.getById(anyLong()))
                .thenThrow(new ResourceNotFoundException("Todo bulunamadi: 99"));
        mockMvc.perform(get("/api/v1/todos/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/v1/todos -> 200 OK")
    void getAll_success() throws Exception {
        Todo t1 = sampleTodo(1L);
        Todo t2 = sampleTodo(2L);
        Page<Todo> page = new PageImpl<>(
                List.of(t1, t2),
                PageRequest.of(0, 10, Sort.by("createdAt").descending()),
                2
        );
        when(todoService.getAll(any(), any(Pageable.class))).thenReturn(page);
        mockMvc.perform(get("/api/v1/todos?page=0&size=10"))
                .andExpect(status().isOk());
    }
}