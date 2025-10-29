package com.mew.demo.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mew.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT DISTINCT u FROM User u " +
            "LEFT JOIN FETCH u.games g " +
            "LEFT JOIN FETCH u.preferredConsole")
    List<User> findAllWithGamesAndConsole();

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.games g " +
            "LEFT JOIN FETCH u.preferredConsole " +
            "WHERE u.userId = :userId")
    User findByIdWithGamesAndConsole(Integer userId);

    @Query("SELECT u FROM User u " +
            "LEFT JOIN FETCH u.games g " +
            "LEFT JOIN FETCH u.preferredConsole " +
            "WHERE u.firstName = :firstName")
    User findByFirstNameWithGamesAndConsole(String firstName);

    Optional<User> findByFirstName(String firstName);

    Optional<User> findByFirstNameIgnoreCase(String firstName);

    boolean existsByEmail(String email);
    
    boolean existsByGamertag(String gamertag);
}