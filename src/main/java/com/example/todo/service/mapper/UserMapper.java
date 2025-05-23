package com.example.todo.service.mapper;

import com.example.todo.dto.UserDto;
import com.example.todo.models.Users;
import org.mapstruct.*;

@Mapper
public interface UserMapper {

    UserDto toDto(Users user);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Users toEntity(UserDto userDto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Users updateAllFields(@MappingTarget Users users, UserDto dto);

}
