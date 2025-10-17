package com.MEW.demo.model;
import java.time.LocalDate;
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
@Table(name = "User")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id", nullable = false)
    private int userId;

    @Column(name = "First_Name", nullable = false, length = 15)
    private String firstName;

    @Column(name = "Last_Name", nullable = true, length = 20)
    private String lastName;

    @Column(name = "Dob", nullable = false)
    private LocalDate dob;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Gamertag", nullable = false, length = 25)
    private String gamertag;

    @JoinColumn(name = "Preferred_Console", nullable = false)
    @ManyToOne
    private Console console;

    @Column(name = "About_User", nullable = true, length = 500)
    private String aboutUser;

    @ManyToMany
    @JoinTable(
        name = "UserGames",
        joinColumns = @JoinColumn(name = "User"),
        inverseJoinColumns = @JoinColumn(name = "Game")
    )
    private java.util.Set<Game> games = new java.util.HashSet<>();
}
