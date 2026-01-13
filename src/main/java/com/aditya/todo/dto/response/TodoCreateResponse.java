package com.aditya.todo.dto.response;

import com.aditya.todo.entity.Todo;
import com.aditya.todo.entity.TodoStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TodoCreateResponse {
    private Long id;
    private String title;
    private String description;
    private TodoStatus status;
    private LocalDateTime createdAt;
    public static TodoCreateResponse from(Todo todo){
        return TodoCreateResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .status(todo.getStatus())
                .createdAt(todo.getCreatedAt())
                .build();
    }
}
