package com.example.todo.config;

import com.example.todo.models.Users;
import com.example.todo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws EntityNotFoundException {
        System.out.println(email);
        Optional<Users> optional = userRepository.findByEmailAndDeletedAtIsNull(email);
        System.out.println("oooooooooooooooo");
        /*Users users = optional.get();*/
//        System.out.println(users);
        if (optional.isEmpty()){
            throw new EntityNotFoundException("USER 404");
        }
        System.out.println(optional.get());
        return new UserPrincipal(optional.get());
    }
}
