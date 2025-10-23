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

    @Query(value = """
        SELECT *
        FROM Matched_User mu
        WHERE (mu.User_Id = :userId OR mu.Liked_User_Id = :userId)
          AND mu.Is_Matched = 1
        """, nativeQuery = true)
    List<MatchedUser> findAllMatchesForUser(@Param("userId") Integer userId);
}
