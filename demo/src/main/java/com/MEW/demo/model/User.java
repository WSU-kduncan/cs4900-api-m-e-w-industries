import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_Id")
    private integer id;

    @Column(name = "User_Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

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