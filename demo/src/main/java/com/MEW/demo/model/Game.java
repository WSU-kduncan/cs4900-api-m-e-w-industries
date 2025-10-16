import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Game_Id")
    private integer id;

    @Column(name = "Game_Name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "Primary_Genre", nullable = false)
    private Genre genre;

    @ManyToMany(mappedBy = "games")
    private List<User> users;
}