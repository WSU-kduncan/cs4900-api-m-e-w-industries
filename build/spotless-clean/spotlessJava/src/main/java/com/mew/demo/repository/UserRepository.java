package com.mew.demo.repository;

import com.mew.demo.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByEmailContainingIgnoreCase(String email);
}
