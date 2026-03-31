package com.ismailtaspinar.todoapi.service;

import com.ismailtaspinar.todoapi.dto.TodoRequest;
import com.ismailtaspinar.todoapi.entity.Todo;
import com.ismailtaspinar.todoapi.exception.ResourceNotFoundException;
import com.ismailtaspinar.todoapi.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;
    @InjectMocks
    private TodoService todoService;

    @Test
    @DisplayName("getById -> found")
    void getById_found() {
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Test");
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        Todo result = todoService.getById(1L);
        assertEquals(1L, result.getId());
        assertEquals("Test", result.getTitle());
    }

    @Test
    @DisplayName("getById -> not found throws")
    void getById_notFound() {
        when(todoRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> todoService.getById(99L));
    }

    @Test
    @DisplayName("create -> done null ise false")
    void create_doneNullDefaultsFalse() {
        TodoRequest req = new TodoRequest();
        req.setTitle("Yeni");
        req.setDescription("Aciklama");
        req.setDone(null);
        when(todoRepository.save(any(Todo.class))).thenAnswer(inv -> inv.getArgument(0));
        Todo saved = todoService.create(req);
        assertFalse(saved.getDone());
        assertEquals("Yeni", saved.getTitle());
    }

    @Test
    @DisplayName("delete -> repository delete cagrilir")
    void delete_success() {
        Todo todo = new Todo();
        todo.setId(1L);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));
        todoService.delete(1L);
        verify(todoRepository, times(1)).delete(todo);
    }
}
