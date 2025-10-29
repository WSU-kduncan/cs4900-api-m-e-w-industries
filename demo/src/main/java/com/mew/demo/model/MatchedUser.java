package com.mew.demo.model;
import java.util.Optional;
import jakarta.persistence.CascadeType;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("userId1")
    @JoinColumn(name = "User_Id", nullable = false)
    private User user1;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("userId2")
    @JoinColumn(name = "Liked_User_Id", nullable = false)
    private User user2;

    @Column(name = "Is_Matched", nullable = true, columnDefinition = "TINYINT(1)")
    private Boolean isMatched;

    public void setUser1(Optional<User> optionalUser) throws IllegalStateException {
        
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("User not found"));
        this.user1 = user;
    }

    public void setUser2(Optional<User> optionalUser) throws IllegalStateException {
        
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("User not found"));
        this.user2 = user;
    }
}