package com.aditya.todo.controllers;

import com.aditya.todo.dto.request.TodoCreateRequest;
import com.aditya.todo.dto.response.TodoCreateResponse;
import com.aditya.todo.services.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoCreateResponse> createTodo(@Valid @RequestBody TodoCreateRequest request){
        TodoCreateResponse response= todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TodoCreateResponse>> getMyTodos(){
        List<TodoCreateResponse> todos = todoService.getMyTodos();
        return ResponseEntity.ok(todos);
    }
}
