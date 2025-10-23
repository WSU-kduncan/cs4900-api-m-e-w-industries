package com.mew.demo.service;

import com.mew.demo.model.User;
import com.mew.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;

  public List<User> getAll() {
    return repository.findAll();
  }

  public User getById(Integer id) {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public List<User> searchByEmail(String q) {
    return repository.findByEmailContainingIgnoreCase(q);
  }
}
