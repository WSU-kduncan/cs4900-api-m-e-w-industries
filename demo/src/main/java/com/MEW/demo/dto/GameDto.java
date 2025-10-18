package com.MEW.demo.dto;
import java.util.HashSet;
import java.util.Set;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.Game;
import com.MEW.demo.model.Genre;
import com.MEW.demo.repository.GenreRepository;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameDto {
    
    private Integer gameId;
    private String gameTitle;
    private boolean singlePlayer;
    private boolean multiPlayer;
    private GenreDto primaryGenre;
    @Builder.Default
    private Set<Integer> userIds = new HashSet<>();

    GameDto(
        Integer gameId,
        String gameTitle,
        boolean singlePlayer,
        boolean multiPlayer,
        GenreDto primaryGenre,
        Set<Integer> userIds
    ) {
        this.gameId = gameId;
        this.gameTitle = gameTitle;
        this.singlePlayer = singlePlayer;
        this.multiPlayer = multiPlayer;
        this.primaryGenre = primaryGenre;
        this.userIds = userIds != null ? userIds : new HashSet<>();
    }

    public GameDto(
        Integer gameId,
        String gameTitle,
        boolean singlePlayer,
        boolean multiPlayer,
        GenreDto primaryGenre
    ) {
        this(gameId, gameTitle, singlePlayer, multiPlayer, primaryGenre, null);
    }

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
            .singlePlayer(this.singlePlayer)
            .multiPlayer(this.multiPlayer)
            .primaryGenre(genre)
            .build();
    }

    public void setUserIds(Set<Integer> userIds) {
        
        if (userIds == null) {
            this.userIds = new HashSet<>();
        } else {
            this.userIds = userIds;
        }
    }
}
