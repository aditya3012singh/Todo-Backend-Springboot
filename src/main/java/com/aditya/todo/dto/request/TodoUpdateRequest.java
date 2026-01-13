package com.aditya.todo.dto.request;

import lombok.Data;

@Data
public class TodoUpdateRequest {
    private String title;
    private String description;
}
