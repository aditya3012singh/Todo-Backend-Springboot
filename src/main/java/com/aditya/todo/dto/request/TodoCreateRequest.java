package com.aditya.todo.dto.request;

import com.aditya.todo.entity.TodoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TodoCreateRequest {
    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "status is required")
    private TodoStatus status;

}
