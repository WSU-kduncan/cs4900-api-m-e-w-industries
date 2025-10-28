package com.mew.demo.repository;

import com.mew.demo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {}
