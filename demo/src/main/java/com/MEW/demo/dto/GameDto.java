package com.MEW.demo.dto;
import lombok.Value;
import com.MEW.demo.model.Game;
import com.MEW.demo.model.Genre;
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

    public Game toEntity() {
        
        Game.GameBuilder gameBuilder = Game.builder()
            .gameId(this.gameId)
            .gameTitle(this.gameTitle)
            .singlePlayer(this.isSinglePlayer)
            .multiPlayer(this.isMultiPlayer);
        
        if(this.primaryGenreId != null) {
            gameBuilder.primaryGenre(
                Genre.builder()
                    .genreId(this.primaryGenreId)
                    .build()
            );
        }

        return gameBuilder.build();
    }
}
