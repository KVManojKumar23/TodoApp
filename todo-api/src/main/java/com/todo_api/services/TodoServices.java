package com.todo_api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.todo_api.dto.TodoDTO;
import com.todo_api.dto.TodoListDTO;
import com.todo_api.model.Todo;
import com.todo_api.model.TodoList;
import com.todo_api.repository.TodoRepository;
import com.todo_api.services.IServices.ITodoService;

@Service
public class TodoServices implements ITodoService {

    private final TodoRepository todoRepository;

    public TodoServices(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // entity to DTO
    private TodoDTO convertToDTO(Todo todo) {
        return TodoDTO.builder()
                .todoId(todo.getTodoId())
                .title(todo.getTitle())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .completedAt(todo.getCompletedAt())
                .todoLists(
                        todo.getTodoLists().stream()
                                .sorted(Comparator.comparingInt(TodoList::getTodoListId))
                                .map(this::convertToDTO)
                                .toList())
                .build();

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
    public void createTodo(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todo.setCompletedAt(new Date());
        this.todoRepository.save(todo);
    }

    @Override
    public void updateTodo(int id, String title, boolean completed) {
        Todo todo = this.todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
        todo.setTitle(title);
        if (completed && !todo.isCompleted()) {
            todo.setCompleted(true);
            todo.setCompletedAt(new Date());
        } else if (!completed && todo.isCompleted()) {
            todo.setCompleted(false);
            todo.setCompletedAt(null);
        }
        this.todoRepository.save(todo);
    }

    @Override
    public void deleteTodo(int id) {
        Todo todo = this.todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
        this.todoRepository.delete(todo);

    }

    @Override
    public TodoDTO getTodoById(int id) {
        Todo todo = this.todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo Not Found"));
        return convertToDTO(todo);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        return todos.stream().map(this::convertToDTO).toList();
    }

}
