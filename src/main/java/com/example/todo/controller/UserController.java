package com.example.todo.controller;

import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.UserDto;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpApiResponse<UserDto>> createUser(@RequestBody UserDto dto){
        HttpApiResponse<UserDto> response = userService.createUser(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<HttpApiResponse<UserDto>> getUserById(@RequestParam("id") Integer id){
        HttpApiResponse<UserDto> response = userService.getUserById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping
    public ResponseEntity<HttpApiResponse<UserDto>> updateUserById(
            @RequestParam("id") Integer id,
            @RequestBody UserDto dto
    ){
        HttpApiResponse<UserDto> response = userService.updateUserById(id, dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping
    public ResponseEntity<HttpApiResponse<UserDto>> deleteUserById(@RequestParam("id") Integer id){
        HttpApiResponse<UserDto> response = userService.deleteUserById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
