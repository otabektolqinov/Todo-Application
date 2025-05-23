package com.example.todo.dto;

import com.example.todo.enums.Priority;
import com.example.todo.enums.TaskState;
import com.example.todo.models.Users;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Integer id;
    private String name;
    private String description;
    private String priority;
    private String taskState;
    private LocalDateTime deadline;
    private LocalDateTime completedAt;
    private Integer userId;

}
