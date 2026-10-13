package com.todo_api.dto;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {

    private int todoId;
    private String title;
    private Date createdAt;
    private Date completedAt;
    private boolean completed;
    private List<TodoListDTO> todoLists;

}
