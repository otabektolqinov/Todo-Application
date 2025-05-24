package com.example.todo.dto;

import com.example.todo.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private List<TaskDto> tasks;

}
