package com.todo_api.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "todo")
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Data 
public class Todo {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;
    private String title;
    private Date createdAt;
    private Date completedAt;
    private boolean completed;
    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodoList> todoLists;


}
