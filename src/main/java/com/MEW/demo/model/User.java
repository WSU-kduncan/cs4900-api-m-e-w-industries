package com.MEW.demo.model;
import java.time.LocalDate;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "User")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
    @EqualsAndHashCode.Include
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
    @ToString.Exclude
    private Console preferredConsole;

    @Column(name = "About_User", nullable = true, length = 500)
    private String aboutUser;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "UserGames",
        joinColumns = @JoinColumn(name = "User_Id"),
        inverseJoinColumns = @JoinColumn(name = "Game_Id")
    )
    @Builder.Default
    @ToString.Exclude
    @JsonIgnoreProperties({"users"})
    private java.util.Set<Game> games = new java.util.HashSet<>();

    public static User fromOptional(Optional<User> optionalUser, String identifier) {
        
        return optionalUser.orElseThrow(() -> new IllegalArgumentException(
            "User with identifier " + identifier + " not found."));
    }
}
