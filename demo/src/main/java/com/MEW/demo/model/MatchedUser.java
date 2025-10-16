import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Matched_User")
public class MatchedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // optional synthetic key
    private integer id;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Liked_User_Id", nullable = false)
    private User likedUser;

    @Column(name = "Is_Matched", nullable = false)
    private Boolean isMatched = false;
}