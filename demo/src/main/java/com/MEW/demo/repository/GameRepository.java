package com.MEW.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {}