package com.MEW.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Console")
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Console_Id")
    private Byte id;

    @Column(name = "Console_Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "console")
    private List<User> users;
}
