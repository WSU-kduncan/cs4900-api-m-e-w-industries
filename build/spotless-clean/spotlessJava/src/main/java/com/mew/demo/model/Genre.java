package com.mew.demo.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Genre")
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Genre_Id")
  private Byte id;

  @Column(name = "Genre_Name", nullable = false)
  private String name;

  @Column(name = "Genre_Description")
  private String description;

  @OneToMany(mappedBy = "genre")
  private List<Game> games;
}
