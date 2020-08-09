package com.tasteofindia.todo.controllers;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.tasteofindia.todo.config.DefaultTodoConfiguration;
import com.tasteofindia.todo.pojos.Todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

@Getter
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private List<Todo> todos;

    public TodoController(DefaultTodoConfiguration defaultTodoConfiguration) {
        this.todos = defaultTodoConfiguration.getDefaultTodos();
    }

    @GetMapping("/")
    public List<Todo> getAllTodos() {
        return todos;
    }

    @PostMapping("/")
    public Todo createNewTodo(@RequestBody String title) {
        Todo newTodo = new Todo(UUID.randomUUID(), title, Instant.now());
        this.todos.add(newTodo);
        return newTodo;
    }

    @GetMapping("/clear")
    public List<Todo> clearAllTodos() {
        this.todos.clear();
        return this.todos;
    }

}