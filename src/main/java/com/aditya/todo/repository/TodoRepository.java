package com.aditya.todo.repository;

import com.aditya.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByOwner(Long ownerId);
    Optional<Todo> findByIdAndOwner_Id(Long todoId, Long ownerId);
}
