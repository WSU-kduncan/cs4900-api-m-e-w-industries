package com.MEW.demo.model;

import java.time.LocalDate;
import java.util.Set;

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
@Table(name = "`User`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private Integer userId;

    @Column(name = "First_Name", length = 15, nullable = false)
    private String firstName;

    @Column(name = "Last_Name", length = 20)
    private String lastName;

    @Column(name = "Dob", nullable = false)
    private LocalDate dob;

    @Column(name = "Email", length = 50, nullable = false)
    private String email;

    @Column(name = "Gamertag", length = 25, nullable = false)
    private String gamertag;

    @Column(name = "About_User", length = 500)
    private String aboutUser;

    @ManyToOne
    @JoinColumn(name = "Preferred_Console", nullable = false)
    private Console preferredConsole;

    @ManyToMany
    @JoinTable(
            name = "UserGames",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Game_Id")
    )
    private Set<Game> games;

    @ManyToMany
    @JoinTable(
            name = "Matched_User",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Liked_User_Id")
    )
    private Set<User> likedUsers;
}
