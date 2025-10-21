package com.MEW.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.Console;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {}