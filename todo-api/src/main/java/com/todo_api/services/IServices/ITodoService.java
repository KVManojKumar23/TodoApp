package com.todo_api.services.IServices;

import java.util.List;

import com.todo_api.dto.TodoDTO;

public interface ITodoService {

    public void createTodo(String title);
    public void updateTodo(int id, String title, boolean completed);

    public void deleteTodo(int id);
    public TodoDTO getTodoById(int id);
    public List<TodoDTO> getAllTodos();

}
