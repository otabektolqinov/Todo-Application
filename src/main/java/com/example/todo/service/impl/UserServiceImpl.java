package com.example.todo.service.impl;

import com.example.todo.dto.HttpApiResponse;
import com.example.todo.dto.UserDto;
import com.example.todo.models.Users;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.UserService;
import com.example.todo.service.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Override
    public HttpApiResponse<UserDto> createUser(UserDto dto) {
        Users user = userMapper.toEntity(dto);
        user.setPassword(encoder.encode(user.getPassword()));
        Users saved = userRepository.save(user);
        return HttpApiResponse.<UserDto>builder()
                .content(userMapper.toDto(saved))
                .message("User successfully registered")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public HttpApiResponse<UserDto> getUserById(Integer id) {
        Users users = userRepository.findById(id).
                orElseThrow(() ->
                        new EntityNotFoundException(String.format("User with id %d not found", id)));
        return HttpApiResponse.<UserDto>builder()
                .content(userMapper.toDto(users))
                .message("OK")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<UserDto> updateUserById(Integer id, UserDto dto) {
        Users users = userRepository.findById(id).
                orElseThrow(() ->
                        new EntityNotFoundException(String.format("User with id %d not found", id)));
        Users updatedUser = userMapper.updateAllFields(users, dto);
        Users saved = userRepository.save(updatedUser);

        return HttpApiResponse.<UserDto>builder()
                .content(userMapper.toDto(saved))
                .message("User successfully updated")
                .success(true)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public HttpApiResponse<UserDto> deleteUserById(Integer id) {
        Users users = userRepository.findById(id).
                orElseThrow(() ->
                        new EntityNotFoundException(String.format("User with id %d not found", id)));
        userRepository.delete(users);

        return HttpApiResponse.<UserDto>builder()
                .status(HttpStatus.OK)
                .message("User successfully deleted")
                .success(true)
                .build();
    }


}
