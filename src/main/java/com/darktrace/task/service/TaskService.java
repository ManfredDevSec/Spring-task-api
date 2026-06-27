package com.darktrace.task.service;

import com.darktrace.task.domain.CreateTaskRequest;
import com.darktrace.task.domain.UpdateTaskRequest;
import com.darktrace.task.domain.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    Task createTask(CreateTaskRequest request);

    List<Task> listTasks();

    Task updateTask(UUID taskId, UpdateTaskRequest request);

    void deleteTask(UUID taskId);

}
