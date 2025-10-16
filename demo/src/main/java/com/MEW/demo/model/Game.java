package com.MEW.demo.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game_Id")
    private Integer gameId;

    @Column(name = "Game_Title", length = 50, nullable = false)
    private String gameTitle;

    @Column(name = "Single_Player", nullable = false)
    private boolean singlePlayer;

    @Column(name = "Multi_Player", nullable = false)
    private boolean multiPlayer;

    @ManyToOne
    @JoinColumn(name = "Primary_Genre", nullable = false)
    private Genre primaryGenre;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;
}
