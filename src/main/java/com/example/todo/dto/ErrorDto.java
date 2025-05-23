package com.example.todo.dto;

import lombok.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

    private String errorPath;
    private String errorMessage;
    private int errorCode;
    private LocalDateTime timestamp;

    public ErrorDto(String errorPath, String errorMessage, int errorCode) {
        this.errorPath = errorPath;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
    }
}
