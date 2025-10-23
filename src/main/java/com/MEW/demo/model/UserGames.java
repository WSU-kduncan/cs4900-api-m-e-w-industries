package com.MEW.demo.model;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class UserGames {
    
    @EmbeddedId
    private UserGamesId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "User_Id")
    private User user;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "Game_Id")
    private Game game;
}
