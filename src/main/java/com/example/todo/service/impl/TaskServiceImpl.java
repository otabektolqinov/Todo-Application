package com.example.todo.service.impl;

import com.example.todo.dto.ErrorDto;
import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.TaskDto;
import com.example.todo.enums.TaskState;
import com.example.todo.models.Task;
import com.example.todo.repository.TaskRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.EmailService;
import com.example.todo.service.TaskService;
import com.example.todo.service.mapper.TaskMapper;
import com.example.todo.service.validation.TaskValidation;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskValidation taskValidation;
    private final UserRepository userRepository;
    private final EmailService emailService;
    TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

    @Override
    public HttpApiResponse<TaskDto> createTask(TaskDto dto) throws MessagingException, IOException {
        Optional<ErrorDto> optionalError = taskValidation.validateTask(dto);
        if (optionalError.isPresent()) {
            ErrorDto error = optionalError.get();
            return HttpApiResponse.<TaskDto>builder()
                    .status(HttpStatus.valueOf(error.getErrorCode()))
                    .message(error.getErrorMessage())
                    .responseCode(error.getErrorCode())
                    .build();
        }

        Task task = taskMapper.toEntity(dto);
        task.setTaskState(TaskState.UNCOMPLETED);
        if (dto.getUserId() != null) {
            task.setUsers(userRepository.findByIdAndDeletedAtIsNull(dto.getUserId()));
        }

        var saved = taskRepository.save(task);

        emailService.sendHtmlMail("", "xullas ukam gazini bos bundan buyogiga");
        return HttpApiResponse.<TaskDto>builder()
                .success(true)
                .content(taskMapper.toDto(saved))
                .message("Successfully saved task")
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public HttpApiResponse<TaskDto> getTaskById(Integer id) {
        Task task = taskRepository
                .findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Task with id %s not found", id)));

        return HttpApiResponse.<TaskDto>builder()
                .success(true)
                .content(taskMapper.toDto(task))
                .message("OK")
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<TaskDto> updateTaskById(Integer id, TaskDto dto) {
        Task task = taskRepository
                .findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Task with id %s not found", id)));

        Task updatedTask = taskMapper.updateAllFields(task, dto);
        Task saved = taskRepository.save(updatedTask);
        return HttpApiResponse.<TaskDto>builder()
                .success(true)
                .content(taskMapper.toDto(saved))
                .message("Successfully saved task")
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public HttpApiResponse<TaskDto> deleteTaskById(Integer id) {
        Task task = taskRepository
                .findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Task with id %s not found", id)));
        taskRepository.delete(task);
        return HttpApiResponse.<TaskDto>builder()
                .status(HttpStatus.OK)
                .message("Successfully deleted task")
                .success(true)
                .build();
    }

    @Override
    public HttpApiResponse<List<TaskDto>> getAllTasksByUserId(Integer userId) {
        if (!userRepository.existsByIdAndDeletedAtIsNull(userId)) {
            throw new EntityNotFoundException(String.format("User with id %s not found", userId));
        }
        List<Task> tasks = taskRepository.findAllByUsers_IdAndDeletedAtIsNull(userId);

        return HttpApiResponse.<List<TaskDto>>builder()
                .success(true)
                .message("OK")
                .status(HttpStatus.OK)
                .content(taskMapper.toDtoList(tasks))
                .build();
    }

    @Override
    public HttpApiResponse<TaskDto> completeTaskById(Integer id) throws MessagingException, IOException {
        Task task = taskRepository
                .findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Task with id %s not found", id)));

        if (task.getDeadline().isBefore(LocalDateTime.now())) {
            task.setTaskState(TaskState.LATE_COMPLETION);
            emailService.sendHtmlMail("", "Ehh ukam taskni tugatalmading vaqtida");
        } else {
            task.setTaskState(TaskState.COMPLETED);
            emailService.sendHtmlMail("", "Qoyil taskni qoyilmaqom bajarding");
        }
        task.setCompletedAt(LocalDateTime.now());
        Task saved = taskRepository.save(task);

        return HttpApiResponse.<TaskDto>builder()
                .success(true)
                .content(taskMapper.toDto(saved))
                .message("Task has been completed")
                .status(HttpStatus.OK)
                .build();
    }
}
