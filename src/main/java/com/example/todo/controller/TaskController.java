package com.example.todo.controller;

import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.TaskDto;
import com.example.todo.service.TaskService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<TaskDto>> createTask(@RequestBody TaskDto dto) throws MessagingException, IOException {
        HttpApiResponse<TaskDto> response = taskService.createTask(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpApiResponse<TaskDto>> getTaskById(@RequestParam("id") Integer id){
        HttpApiResponse<TaskDto> response = taskService.getTaskById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-all-by-useId")
    public ResponseEntity<HttpApiResponse<List<TaskDto>>> getAllTaskByUserId(@RequestParam("userId") Integer userId){
        HttpApiResponse<List<TaskDto>> response = taskService.getAllTasksByUserId(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpApiResponse<TaskDto>> updateTaskById(
            @RequestParam("id") Integer id,
            @RequestBody TaskDto dto
    ){
        HttpApiResponse<TaskDto> response = taskService.updateTaskById(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/complete-task")
    public ResponseEntity<HttpApiResponse<TaskDto>> completeTaskById(
            @RequestParam("id") Integer id
    ) throws MessagingException, IOException {
        HttpApiResponse<TaskDto> response = taskService.completeTaskById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpApiResponse<TaskDto>> deleteTaskById(@RequestParam("id") Integer id){
        HttpApiResponse<TaskDto> response = taskService.deleteTaskById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
