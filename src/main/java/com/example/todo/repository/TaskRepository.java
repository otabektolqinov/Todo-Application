package com.example.todo.repository;

import com.example.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByIdAndDeletedAtIsNull(Integer id);

    List<Task> findAllByUsers_IdAndDeletedAtIsNull(Integer userId);
}
