package com.todo_api.model;

import java.util.Date;

import com.todo_api.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "todo_list")
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Data 
public class TodoList {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int todoListId;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date createdAt;
    private Date completedAt;
    private boolean completed;
    @ManyToOne(targetEntity = Todo.class)
    @JoinColumn(name = "todoId")
    private Todo todo;

}
