package com.MEW.demo.service;

import com.MEW.demo.model.User;
import com.MEW.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<User> searchByEmail(String q) {
        return repository.findByEmailContainingIgnoreCase(q);
    }
}
