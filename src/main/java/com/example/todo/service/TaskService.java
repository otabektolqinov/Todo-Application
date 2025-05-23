package com.example.todo.service;

import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.TaskDto;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface TaskService {

    HttpApiResponse<TaskDto> createTask(TaskDto dto) throws MessagingException, IOException;

    HttpApiResponse<TaskDto> getTaskById(Integer id);

    HttpApiResponse<TaskDto> updateTaskById(Integer id, TaskDto dto);

    HttpApiResponse<TaskDto> deleteTaskById(Integer id);

    HttpApiResponse<List<TaskDto>> getAllTasksByUserId(Integer userId);

    HttpApiResponse<TaskDto> completeTaskById(Integer id) throws MessagingException, IOException;
}
