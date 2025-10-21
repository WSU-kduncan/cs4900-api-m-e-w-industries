package com.MEW.demo.repository;
import org.springframework.stereotype.Repository;
import com.MEW.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {}