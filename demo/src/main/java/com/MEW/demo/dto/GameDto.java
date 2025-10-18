package com.MEW.demo.dto;
import lombok.Value;

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
    private Integer primaryGenreId;

    public static GameDto fromEntity(Game game) {
        
        return new GameDto(
            game.getGameId(),
            game.getGameTitle(),
            game.isSinglePlayer(),
            game.isMultiPlayer(),
            game.getPrimaryGenre().getGenreId()
            );
    }

    public Game toEntity(GenreRepository genreRepository) throws EntityNotFoundException{

        Genre genre = null;
        
        if(this.primaryGenreId != null) {
            genre = genreRepository.findById(this.primaryGenreId).orElseThrow(
                    () -> new IllegalArgumentException("Genre with ID " + this.primaryGenreId + " not found.")
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
