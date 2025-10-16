import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UserGames")
public class UserGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private integer id;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Game_Id", nullable = false)
    private Game game;
}