package com.MEW.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Genre_Id")
    private Byte genreId;

    @Column(name = "Genre_Name", length = 30, nullable = false)
    private String genreName;

    @Column(name = "Genre_Description", length = 254)
    private String genreDescription;
}
