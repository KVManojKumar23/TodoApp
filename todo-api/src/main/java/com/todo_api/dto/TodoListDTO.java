package com.todo_api.dto;

import java.util.Date;

import com.todo_api.model.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoListDTO {

    private int todoListId;
    private String name;
    private String description;
    private Status status;
    private Date createdAt;
    private Date completedAt;
    private int todoId;
    private boolean completed;

}
