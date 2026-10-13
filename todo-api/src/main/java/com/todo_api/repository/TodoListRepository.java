package com.todo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_api.model.TodoList;

public interface TodoListRepository extends  JpaRepository<TodoList, Integer>{

}
