package com.todo_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo_api.dto.TodoDTO;
import com.todo_api.services.IServices.ITodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final ITodoService todoService;

    public TodoController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTodo(@RequestParam String title) {
        this.todoService.createTodo(title);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTodo(
            @RequestParam int id,
            @RequestParam String title,
            @RequestParam boolean completed) {
        this.todoService.updateTodo(id, title, completed);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTodo(@RequestParam int id) {
        this.todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getById")
    public ResponseEntity<TodoDTO> getTodoById(@RequestParam int id) {
        TodoDTO todoDTO = this.todoService.getTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todoDTOs = this.todoService.getAllTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todoDTOs);
    }

}
