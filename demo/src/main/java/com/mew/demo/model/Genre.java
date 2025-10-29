package com.mew.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Genre")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "Genre_Id", nullable = false)
    private int genreId;

    @Column(name = "Genre_Name", nullable = false, length = 30)
    private String genreName;

    @Column(name = "Genre_Description", nullable = true, length = 254)
    private String genreDescription;
}
