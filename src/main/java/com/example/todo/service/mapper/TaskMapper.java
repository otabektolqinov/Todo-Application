package com.example.todo.service.mapper;

import com.example.todo.dto.TaskDto;
import com.example.todo.models.Task;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Named("toDto")
    @Mapping(target = "userId", expression = "java(task.getUsers().getId())")
    TaskDto toDto(Task task);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Task toEntity(TaskDto taskDto);

    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task updateAllFields(@MappingTarget Task task, TaskDto dto);

    @IterableMapping(qualifiedByName = "toDto")
    List<TaskDto> toDtoList(List<Task> tasks);
}
