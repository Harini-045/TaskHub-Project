package com.taskhub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {
    private int taskId;
    private String taskTitle;
    private String taskDescription;
    private LocalDate startDate;
    private LocalDate dueDate;
    private int userId;
    private String priority;
    private int labelId;
}
