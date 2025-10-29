package com.mew.demo.repository;

import com.mew.demo.model.Game;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

  Optional<Game> findByGameTitle(String gameTitle);
}
