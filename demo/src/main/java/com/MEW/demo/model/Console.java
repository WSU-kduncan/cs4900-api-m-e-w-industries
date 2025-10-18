package com.MEW.demo.model;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Console")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Console_Id", nullable = false)
    private int consoleId;

    @Column(name = "Console_Name", nullable = true, length = 12)
    private String consoleName;

    @OneToMany(mappedBy = "preferredConsole")
    private List<User> users = new java.util.ArrayList<>();  
}