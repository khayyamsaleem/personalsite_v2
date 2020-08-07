package com.tasteofindia.todo.pojos;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
   private UUID id;
   private String title;
   private Instant createdTime; 
}