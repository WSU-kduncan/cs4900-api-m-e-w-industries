package com.MEW.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MEW.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstNameContainingIgnoreCase(String firstName);
}