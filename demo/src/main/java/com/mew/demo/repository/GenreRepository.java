package com.mew.demo.repository;
import org.springframework.stereotype.Repository;
import com.mew.demo.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {}