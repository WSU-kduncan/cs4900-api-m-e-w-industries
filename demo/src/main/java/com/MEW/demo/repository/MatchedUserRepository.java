package com.MEW.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.MatchedUser;
import com.MEW.demo.model.MatchedUserId;

@Repository
public interface MatchedUserRepository extends JpaRepository<MatchedUser, MatchedUserId> {

    @Query("SELECT m FROM MatchedUser m " +
           "WHERE (m.user1.userId = :userId OR m.user2.userId = :userId) " +
           "AND m.isMatched = true")
    List<MatchedUser> findAllMatchesForUser(@Param("userId") Integer userId);
}
