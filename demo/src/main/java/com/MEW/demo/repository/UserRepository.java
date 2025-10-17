package com.MEW.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MEW.demo.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {}