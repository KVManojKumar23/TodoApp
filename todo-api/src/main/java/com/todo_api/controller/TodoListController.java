package com.todo_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo_api.dto.TodoListDTO;
import com.todo_api.model.enums.Status;
import com.todo_api.services.IServices.ITodoListServices;

@RestController 
@RequestMapping("/api/todo-lists")
public class TodoListController {

    private final ITodoListServices todoListServices;

    public TodoListController(ITodoListServices todoListServices) {
        this.todoListServices = todoListServices;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTodoList(@RequestBody TodoListDTO todoListDTO) {
        this.todoListServices.createTodoList(todoListDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateTodoList(@RequestBody TodoListDTO todoListDTO) {
        this.todoListServices.updateTodoList(todoListDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/status")
    public ResponseEntity<Void> updateTodoList(@RequestParam int id, @RequestParam Status status) {
        this.todoListServices.updateTodoListStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTodoList(@RequestParam  int id ) {
        this.todoListServices.deleteTodoList(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/getById")
    public ResponseEntity<TodoListDTO> getTodoListById(@RequestParam int id) {
        TodoListDTO todoListDTO = this.todoListServices.getTodoListById(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoListDTO);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<TodoListDTO>> getAllTodoList() {
        List<TodoListDTO> todoListDTOs = this.todoListServices.getAllTodoLists();
        return ResponseEntity.status(HttpStatus.OK).body(todoListDTOs);
    }



}
