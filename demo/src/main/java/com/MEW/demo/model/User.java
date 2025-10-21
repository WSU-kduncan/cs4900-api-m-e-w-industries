package com.MEW.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Integer id;

    @Column(name = "First_Name", nullable = false)
    private String firstName;

    @Column(name = "Last_Name")
    private String lastName;

    @Column(name = "Dob", nullable = false)
    private LocalDate dob;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Gamertag", nullable = false)
    private String gamertag;

    @Column(name = "About_User")
    private String about;

    @ManyToOne
    @JoinColumn(name = "Preferred_Console", nullable = false)
    private Console console;

    @ManyToMany
    @JoinTable(
        name = "UserGames",
        joinColumns = @JoinColumn(name = "User_Id"),
        inverseJoinColumns = @JoinColumn(name = "Game_Id")
    )
    private List<Game> games;

    @ManyToMany
    @JoinTable(
        name = "Matched_User",
        joinColumns = @JoinColumn(name = "User_Id"),
        inverseJoinColumns = @JoinColumn(name = "Liked_User_Id")
    )
    private List<User> likedUsers;

    @ManyToMany(mappedBy = "likedUsers")
    private List<User> likedBy;
}
