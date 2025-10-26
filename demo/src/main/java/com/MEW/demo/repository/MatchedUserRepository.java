package com.MEW.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.MEW.demo.dto.MatchInfoDto;
import com.MEW.demo.model.MatchedUser;
import com.MEW.demo.model.MatchedUserId;
import jakarta.transaction.Transactional;

@Repository
public interface MatchedUserRepository extends JpaRepository<MatchedUser, MatchedUserId> {

    @Query(value = "SELECT * FROM Matched_User mu " + 
                    "WHERE (mu.`User` = :userId OR mu.Liked_User = :userId) AND mu.Is_Matched = 1", nativeQuery = true)
    List<MatchedUser> findAllMatchesForUser(@Param("userId") Integer userId);

    @Query(value = """
        SELECT u.First_Name AS firstName, u.Gamertag AS gamertag, u.About_User AS aboutUser
        FROM User u
        INNER JOIN Matched_User mu
            ON u.User_Id = mu.Liked_User
        WHERE mu.`User` = :userId
        AND u.User_Id = :matchId
        AND mu.Is_Matched = 1
        """, nativeQuery = true)
    MatchInfoDto findMatchInfo(@Param("userId") Integer userId, @Param("matchId") Integer matchId);

    @Query(value = """
        SELECT * 
        FROM Matched_User mu
        WHERE mu.`User` = :userId AND mu.Liked_User = :matchId
        """, nativeQuery = true)
    MatchedUser findMatch(@Param("userId") Integer userId, @Param("matchId") Integer matchId);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value = """
        UPDATE Matched_User 
        SET Is_Matched = :isMatched 
        WHERE (`User` = :userId AND Liked_User = :matchId)
        OR (`User` = :matchId AND Liked_User = :userId)
        """, nativeQuery = true)
    void updateIsMatched(@Param("userId") Integer userId, @Param("matchId") Integer matchId, @Param("isMatched") boolean isMatched);

    @Modifying
    @Query("DELETE FROM MatchedUser mu WHERE mu.`User`.userId = :userId1 OR mu.Liked_User_Id.userId = :userId2")
    void deleteByUser1OrUser2(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);
}
