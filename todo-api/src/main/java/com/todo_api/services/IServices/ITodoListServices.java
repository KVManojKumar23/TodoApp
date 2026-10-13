package com.todo_api.services.IServices;

import java.util.List;

import com.todo_api.dto.TodoListDTO;
import com.todo_api.model.enums.Status;

public interface ITodoListServices {

    public void createTodoList(TodoListDTO todoListDTO);
    public void updateTodoList(TodoListDTO todoListDTO);
    public void deleteTodoList(int id);
    public void updateTodoListStatus(int id, Status status);
    public TodoListDTO getTodoListById(int id);
    public List<TodoListDTO> getAllTodoLists();

}
