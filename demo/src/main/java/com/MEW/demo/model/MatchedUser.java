package com.MEW.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Matched_User")
public class MatchedUser {

    @EmbeddedId
    private MatchedUserId id;

    @ManyToOne
    @MapsId("userId1")
    @JoinColumn(name = "User", nullable = false)
    private User user1;

    @ManyToOne
    @MapsId("userId2")
    @JoinColumn(name = "Liked_User", nullable = false)
    private User user2;

    @Column(name = "Is_Matched", nullable = true, columnDefinition = "TINYINT(1)")
    private Boolean isMatched;
}