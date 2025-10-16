package com.MEW.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Console")
public class Console {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Console_Id")
    private Byte consoleId;

    @Column(name = "Console_Name", length = 10, nullable = false)
    private String consoleName;
}
