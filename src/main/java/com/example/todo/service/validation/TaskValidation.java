package com.example.todo.service.validation;

import com.example.todo.dto.ErrorDto;
import com.example.todo.dto.TaskDto;
import com.example.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskValidation {

    private final UserRepository userRepository;

    public Optional<ErrorDto> validateTask(TaskDto dto) {
        boolean userExists = userRepository.existsByIdAndDeletedAtIsNull(dto.getUserId());
        if (!userExists) {
            return Optional.of(
                    new ErrorDto(
                            "/address",
                            "Company not found",
                            HttpStatus.BAD_REQUEST.value()
                    ));
        }
        return Optional.empty();

    }

}
