package com.example.todo.repository;

import com.example.todo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    boolean existsByIdAndDeletedAtIsNull(Integer id);

    Users findByIdAndDeletedAtIsNull(Integer userId);
}
