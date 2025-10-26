package com.MEW.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.UserGames;
import com.MEW.demo.model.UserGamesId;
import jakarta.transaction.Transactional;

@Repository
public interface UserGamesRepository extends JpaRepository<UserGames, UserGamesId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM UserGames ug WHERE ug.user.userId = :userId")
    void deleteByUserId(@Param("userId") Integer userId);
}
