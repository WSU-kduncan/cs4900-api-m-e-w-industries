package com.MEW.demo.dto;
import lombok.Value;

import java.util.Set;

import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.Game;
import com.MEW.demo.model.Genre;
import com.MEW.demo.repository.GenreRepository;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Value
public class GameDto {
    
    private Integer gameId;
    private String gameTitle;
    private boolean isSinglePlayer;
    private boolean isMultiPlayer;
    private GenreDto primaryGenre;
    private Set<Integer> userIds;


    public static GameDto fromEntity(Game game) {
        
        return new GameDto(
            game.getGameId(),
            game.getGameTitle(),
            game.isSinglePlayer(),
            game.isMultiPlayer(),
            game.getPrimaryGenre() != null ? GenreDto.fromEntity(game.getPrimaryGenre()) : null,
            game.getUsers() != null ? game.getUsers().stream()
                .map(user -> user.getUserId())
                .collect(java.util.stream.Collectors.toSet()) : null
            );
    }

    public Game toEntity(GenreRepository genreRepository) throws EntityNotFoundException{

        Genre genre = null;
        
        if(this.primaryGenre.getGenreId() != null) {
            genre = genreRepository.findById(this.primaryGenre.getGenreId()).orElseThrow(
                    () -> new IllegalArgumentException("Genre with ID " + this.primaryGenre.getGenreId() + " not found.")
            );
        }

        return Game.builder()
            .gameId(this.gameId)
            .gameTitle(this.gameTitle)
            .singlePlayer(this.isSinglePlayer)
            .multiPlayer(this.isMultiPlayer)
            .primaryGenre(genre)
            .build();
    }
}
