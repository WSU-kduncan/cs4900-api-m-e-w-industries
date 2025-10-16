package com.MEW.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Game")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game_Id", nullable = false)
    private int gameId;

    @Column(name = "Game_Title", nullable = false, length = 50)
    private String gameTitle;

    @Column(name = "Single_Player", nullable = false)
    private boolean singlePlayer;

    @Column(name = "Multi_Player", nullable = false)
    private boolean multiPlayer;

    @JoinColumn(name = "Primary_Genre", nullable = false)
    @ManyToOne
    private Genre primaryGenre;

    @ManyToMany
    @JoinTable(
        name = "UserGames",
        joinColumns = @JoinColumn(name = "Game"),
        inverseJoinColumns = @JoinColumn(name = "User")
    )
    private java.util.Set<User> users = new java.util.HashSet<>();
}
