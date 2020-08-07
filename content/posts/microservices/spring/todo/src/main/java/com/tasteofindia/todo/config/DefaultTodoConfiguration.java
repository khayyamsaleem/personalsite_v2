package com.tasteofindia.todo.config;

import java.util.List;

import com.tasteofindia.todo.pojos.Todo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "todo")
public class DefaultTodoConfiguration {
    private List<Todo> defaultTodos;
}