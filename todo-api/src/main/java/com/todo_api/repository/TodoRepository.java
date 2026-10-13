package com.todo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo_api.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
