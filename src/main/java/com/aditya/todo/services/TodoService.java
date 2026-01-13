package com.aditya.todo.services;

import com.aditya.todo.dto.request.TodoCreateRequest;
import com.aditya.todo.dto.response.TodoCreateResponse;
import com.aditya.todo.entity.Todo;
import com.aditya.todo.entity.TodoStatus;
import com.aditya.todo.entity.User;
import com.aditya.todo.exception.ResourceNotFoundException;
import com.aditya.todo.repository.TodoRepository;
import com.aditya.todo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(TodoRepository todoRepository, UserRepository userRepository){
        this.todoRepository=todoRepository;
        this.userRepository=userRepository;
    }

    public TodoCreateResponse createTodo(TodoCreateRequest request){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));

        Todo todo= Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(TodoStatus.PENDING)
                .owner(user)
                .build();

        Todo savedTodo = todoRepository.save(todo);

        return TodoCreateResponse.builder()
                .id(savedTodo.getId())
                .title(savedTodo.getTitle())
                .description(savedTodo.getDescription())
                .build();
    }

    public List<TodoCreateResponse> getMyTodos(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();

        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));

        return todoRepository.findByOwner(user.getId())
                .stream()
                .map(todo -> TodoCreateResponse.builder()
                        .id(todo.getId())
                        .title(todo.getTitle())
                        .description(todo.getDescription())
                        .build())
                .toList();
    }

    public void completeTodo(Long todoId){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found"));

        Todo todo= todoRepository.findByIdAndOwner_Id(todoId, user.getId())
                .orElseThrow(()->new ResourceNotFoundException("Todo not found"));

        if(todo.getStatus() == TodoStatus.COMPLETED){
            throw new IllegalStateException("Todo already completed");
        }

        todo.setStatus(TodoStatus.COMPLETED);
        todoRepository.save(todo);
    }
}
