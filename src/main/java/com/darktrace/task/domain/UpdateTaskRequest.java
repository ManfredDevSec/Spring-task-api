package com.darktrace.task.domain;

import com.darktrace.task.domain.entity.TaskPriority;
import com.darktrace.task.domain.entity.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest (
        String title,
        String description,
        LocalDate dueDate,
        TaskStatus status,
        TaskPriority priority
){

}
