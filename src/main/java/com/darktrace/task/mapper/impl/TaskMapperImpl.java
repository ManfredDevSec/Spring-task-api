package com.darktrace.task.mapper.impl;

import com.darktrace.task.domain.CreateTaskRequest;
import com.darktrace.task.domain.UpdateTaskRequest;
import com.darktrace.task.domain.dto.CreateTaskRequestDto;
import com.darktrace.task.domain.dto.TaskDto;
import com.darktrace.task.domain.dto.UpdateTaskRequestDto;
import com.darktrace.task.domain.entity.Task;
import com.darktrace.task.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.status(),
                dto.priority()
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
