package com.MEW.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game_Id")
    private Integer id;

    @Column(name = "Game_Title", nullable = false)
    private String title;

    @Column(name = "Single_Player", nullable = false)
    private boolean singlePlayer;

    @Column(name = "Multi_Player", nullable = false)
    private boolean multiPlayer;

    @ManyToOne
    @JoinColumn(name = "Primary_Genre", nullable = false)
    private Genre genre;

    @ManyToMany(mappedBy = "games")
    private List<User> users;
}
