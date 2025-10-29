package com.mew.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mew.demo.model.Console;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {}