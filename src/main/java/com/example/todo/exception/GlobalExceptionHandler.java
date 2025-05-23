package com.example.todo.exception;

import com.example.todo.dto.HttpApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author: Hasanboy Xalilov
 * Created: 08/05/25
 * Project: workroom-project
 */

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    /*private final ObjectMapper objectMapper;*/

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<HttpApiResponse<Void>> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        return ResponseEntity.status (HttpStatus.NOT_FOUND)
                .body (HttpApiResponse.<Void>builder ()
                        .responseCode (HttpStatus.NOT_FOUND.value ())
                        .message (e.getMessage ())
                        .status (HttpStatus.NOT_FOUND)
                        .build ());
    }

    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpApiResponse<Void>> methodArgumentNotValidException(
            HttpServletRequest request, MethodArgumentNotValidException ex, Locale locale
    ) throws JsonProcessingException {
        BindingResult bindingResult = ex.getBindingResult ();
        FieldError fieldError = bindingResult.getFieldError ();
        assert fieldError != null;
        String requestURI = request.getRequestURI ();
        String field = fieldError.getField ();
        Object rejectedValue = fieldError.getRejectedValue ();
        ValidationMessageDto validationMessageDto = ValidationMessageDto.builder ()
                .fieldName (field)
                .message ("Validation failed")
                .rejectedValue (rejectedValue)
                .build ();
        return ResponseEntity.badRequest ()
                .body (
                        HttpApiResponse.<Void>builder ()
                                .responseCode (HttpStatus.BAD_REQUEST.value ())
                                .message ("Validation failed")
                                .status (HttpStatus.BAD_REQUEST)
                                .errors (
                                        new ErrorDto(
                                                requestURI,
                                                this.objectMapper.writeValueAsString (validationMessageDto),
                                                HttpStatus.BAD_REQUEST.value ()
                                        )
                                )
                                .build ()
                );
    }*/

}
