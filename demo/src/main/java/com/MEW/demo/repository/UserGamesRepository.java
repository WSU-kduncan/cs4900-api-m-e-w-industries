package com.MEW.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.UserGames;

@Repository
public interface UserGamesRepository extends JpaRepository<UserGames, Integer> {}
