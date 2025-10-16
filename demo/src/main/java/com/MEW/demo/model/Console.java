import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Console")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Console_Id")
    private integer id;

    @Column(name = "Console_Name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "console", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
}