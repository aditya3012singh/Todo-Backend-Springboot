package com.aditya.todo.controllers;

import com.aditya.todo.dto.request.TodoCreateRequest;
import com.aditya.todo.dto.request.TodoUpdateRequest;
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

        return ResponseEntity.status(HttpStatus.FOUND).body(todos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> completeTodo(@PathVariable Long id) {
        todoService.completeTodo(id);
//        return ResponseEntity.noContent().build();
        return ResponseEntity.ok("Completed todo");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo Deleted Successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoCreateResponse> updateTodo(
            @PathVariable Long id,
            @RequestBody TodoUpdateRequest request) {

        TodoCreateResponse updatedTodo= todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);// 204
    }

}
