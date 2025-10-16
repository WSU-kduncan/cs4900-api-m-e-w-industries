import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Genre_Id")
    private interger id;

    @Column(name = "Genre_Name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;
}