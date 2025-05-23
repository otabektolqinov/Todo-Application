package com.example.todo.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpApiResponse<T> {

    private boolean success;
    private String message;
    private int responseCode;
    private HttpStatus status;
    private T content;
    private ErrorDto errors;

}
