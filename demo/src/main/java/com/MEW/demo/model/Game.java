package com.MEW.demo.model;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Game")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"users", "primaryGenre"})
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

    @ManyToMany(mappedBy = "games")
    @Builder.Default
    @JsonIgnore 
    private Set<User> users = new HashSet<>();
}
