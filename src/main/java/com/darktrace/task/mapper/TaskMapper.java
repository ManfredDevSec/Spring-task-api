package com.darktrace.task.mapper;

import com.darktrace.task.domain.CreateTaskRequest;
import com.darktrace.task.domain.UpdateTaskRequest;
import com.darktrace.task.domain.dto.CreateTaskRequestDto;
import com.darktrace.task.domain.dto.TaskDto;
import com.darktrace.task.domain.dto.UpdateTaskRequestDto;
import com.darktrace.task.domain.entity.Task;

public interface TaskMapper {

    CreateTaskRequest fromDto(CreateTaskRequestDto dto);

    UpdateTaskRequest fromDto(UpdateTaskRequestDto dto);

    TaskDto toDto(Task task);

}
