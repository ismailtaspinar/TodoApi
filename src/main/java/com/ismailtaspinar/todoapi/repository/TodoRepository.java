package com.ismailtaspinar.todoapi.repository;

import com.ismailtaspinar.todoapi.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByDone(Boolean done);
    Page<Todo> findByDone(Boolean done, Pageable pageable);
    Page<Todo> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
            String titleKeyword,
            String descriptionKeyword,
            Pageable pageable
    );
}
