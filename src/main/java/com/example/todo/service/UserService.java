package com.example.todo.service;

import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    HttpApiResponse<UserDto> createUser(UserDto dto);

    HttpApiResponse<UserDto> getUserById(Integer id);

    HttpApiResponse<UserDto> updateUserById(Integer id, UserDto dto);

    HttpApiResponse<UserDto> deleteUserById(Integer id);
}
