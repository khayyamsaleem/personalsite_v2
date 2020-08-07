package com.tasteofindia.todo.controllers;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

import com.tasteofindia.todo.config.DefaultTodoConfiguration;
import com.tasteofindia.todo.pojos.Todo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;;

@ExtendWith(MockitoExtension.class)
public class TodoControllerTest {

    MockMvc mockMvc;

    @Mock
    DefaultTodoConfiguration defaultTodoConfiguration;

    TodoController todoController;

    @BeforeEach
    public void setUp() {
        when(defaultTodoConfiguration.getDefaultTodos()).thenReturn(new ArrayList<Todo>() {
            private static final long serialVersionUID = 1L;
            {
			    add(new Todo(UUID.randomUUID(), "Learn Junit 5", Instant.now()));
			    add(new Todo(UUID.randomUUID(), "Learn Mockito", Instant.now()));
            }
        });
        todoController = new TodoController(defaultTodoConfiguration);
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void givenDefaultConfiguration_whenGetAllTodos_thenAllTodosReturned() throws Exception {
        mockMvc.perform(
            get("/api/v1/todos/").accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(2)));
    }
}