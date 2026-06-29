package com.darktrace.task.controller;

import com.darktrace.task.domain.CreateTaskRequest;
import com.darktrace.task.domain.UpdateTaskRequest;
import com.darktrace.task.domain.dto.CreateTaskRequestDto;
import com.darktrace.task.domain.dto.TaskDto;
import com.darktrace.task.domain.dto.UpdateTaskRequestDto;
import com.darktrace.task.domain.entity.Task;
import com.darktrace.task.mapper.TaskMapper;
import com.darktrace.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    @Operation(
            summary = "Create a new task",
            description = "Create a new with status and priority"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request from client")
    })
    public ResponseEntity<TaskDto> createTask(
            @Valid @RequestBody CreateTaskRequestDto createTaskRequestDto
    ) {
        CreateTaskRequest createTaskRequest = taskMapper.fromDto(createTaskRequestDto);
        Task task = taskService.createTask(createTaskRequest);
        TaskDto createdTaskDto = taskMapper.toDto(task);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all tasks",
            description = "Retrieve all tasks created by the user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All tasks retrieved"),
            @ApiResponse(responseCode = "400", description = "Bad request from client")
    })
    public ResponseEntity<List<TaskDto>> listTasks() {
        List<Task> tasks = taskService.listTasks();
        List<TaskDto> taskDtos = tasks.stream().map(taskMapper::toDto).toList();
        return ResponseEntity.ok(taskDtos);
    }

    @PutMapping(path = "/{taskId}")
    @Operation(
            summary = "Get task by Id",
            description = "Retrieve details of a specific task by ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task details retrieved"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable UUID taskId,
            @Valid @RequestBody UpdateTaskRequestDto updateTaskRequestDto
    ) {
        UpdateTaskRequest updateTaskRequest = taskMapper.fromDto(updateTaskRequestDto);
        Task task = taskService.updateTask(taskId, updateTaskRequest);
        TaskDto taskDto = taskMapper.toDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @Operation(
            summary = "Delete a task"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad request from client")
    })
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<Void> deleteTask(
            @PathVariable UUID taskId
    ) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
