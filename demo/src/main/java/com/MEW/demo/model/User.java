package com.MEW.demo.model;
import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "User")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Preferred_Console", referencedColumnName = "Console_Id", nullable = false)
    private Console preferredConsole;

    @Column(name = "About_User", nullable = true, length = 500)
    private String aboutUser;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "UserGames",
        joinColumns = @JoinColumn(name = "User"),
        inverseJoinColumns = @JoinColumn(name = "Game")
    )
    @Builder.Default
    private java.util.Set<Game> games = new java.util.HashSet<>();
}
