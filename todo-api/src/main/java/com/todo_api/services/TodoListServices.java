package com.todo_api.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo_api.dto.TodoListDTO;
import com.todo_api.model.TodoList;
import com.todo_api.model.enums.Status;
import com.todo_api.repository.TodoListRepository;
import com.todo_api.repository.TodoRepository;
import com.todo_api.services.IServices.ITodoListServices;

@Service
public class TodoListServices implements ITodoListServices {

    private final TodoRepository todoRepository;
    private final TodoListRepository todoListRepository;

    public TodoListServices(TodoRepository todoRepository,
            TodoListRepository todoListRepository) {
        this.todoRepository = todoRepository;
        this.todoListRepository = todoListRepository;
    }

    private TodoListDTO convertToDTO(TodoList todoList) {
        return TodoListDTO.builder()
                .todoListId(todoList.getTodoListId())
                .name(todoList.getName())
                .description(todoList.getDescription())
                .status(todoList.getStatus())
                .createdAt(todoList.getCreatedAt())
                .completedAt(todoList.getCompletedAt())
                .completed(todoList.isCompleted())
                .build();
    }

    @Override
    public void createTodoList(TodoListDTO todoListDTO) {
        TodoList todoList = new TodoList();
        todoList.setName(todoListDTO.getName());
        todoList.setDescription(todoListDTO.getDescription());
        todoList.setStatus(todoListDTO.getStatus());
        todoList.setCreatedAt(new Date());
        todoList.setTodo(todoRepository.findById(todoListDTO.getTodoId())
                .orElseThrow(() -> new RuntimeException("Todo Not Found")));
        this.todoListRepository.save(todoList);
    }

    @Override
    public void updateTodoList(TodoListDTO todoListDTO) {
        TodoList todoList = this.todoListRepository.findById(todoListDTO.getTodoListId())
                .orElseThrow(() -> new RuntimeException("Todo List Not Found"));
        todoList.setName(todoListDTO.getName());
        todoList.setDescription(todoListDTO.getDescription());
        todoList.setStatus(todoListDTO.getStatus());
        if (!todoListDTO.isCompleted() && todoListDTO.getStatus() != Status.FINISHED) {
            todoList.setCompleted(false);
            todoList.setCompletedAt(null);
        } else if (todoListDTO.isCompleted() && todoListDTO.getStatus() == Status.FINISHED) {
            todoList.setCompleted(true);
            todoList.setCompletedAt(new Date());
        }
        this.todoListRepository.save(todoList);
    }

    @Override
    public void deleteTodoList(int id) {
        TodoList todoList = this.todoListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo List Not Found"));
        this.todoListRepository.delete(todoList);
    }

    @Override
    public void updateTodoListStatus(int id, Status status) {
        TodoList todoList = this.todoListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo List Not Found"));
        todoList.setStatus(status);
        if (status == Status.FINISHED) {
            todoList.setCompleted(true);
            todoList.setCompletedAt(new Date());
        } else {
            todoList.setCompleted(false);
            todoList.setCompletedAt(null);
        }
        this.todoListRepository.save(todoList);
    }

    @Override
    public TodoListDTO getTodoListById(int id) {
        TodoList todoList = this.todoListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo List Not Found"));
        return convertToDTO(todoList);
    }

    @Override
    public List<TodoListDTO> getAllTodoLists() {
        List<TodoList> todoLists = this.todoListRepository.findAll();
        return todoLists.stream()
                .map(this::convertToDTO)
                .toList();
    }

}
