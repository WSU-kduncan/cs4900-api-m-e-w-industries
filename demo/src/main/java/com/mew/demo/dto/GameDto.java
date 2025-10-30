package com.mew.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.model.Game;
import com.mew.demo.model.Genre;
import com.mew.demo.repository.GenreRepository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GameDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Integer gameId;

  @NotBlank(message = "Game title is required")
  private String gameTitle;

  @NotNull(message = "Single player field must be specified")
  private boolean singlePlayer;

  @NotNull(message = "Multi player field must be specified")
  private boolean multiPlayer;

  @NotNull(message = "Primary genre field must be specified")
  private Integer primaryGenreId;

  @Builder.Default
  private Set<Integer> userIds = new HashSet<>();

  public GameDto(
      Integer gameId,
      String gameTitle,
      boolean singlePlayer,
      boolean multiPlayer,
      Integer primaryGenreId,
      Set<Integer> userIds) {
    this.gameId = gameId;
    this.gameTitle = gameTitle;
    this.singlePlayer = singlePlayer;
    this.multiPlayer = multiPlayer;
    this.primaryGenreId = primaryGenreId;
    this.userIds = userIds != null ? userIds : new HashSet<>();
  }

  public static GameDto fromEntity(Game game) {

    if (game == null) { return null;}

    return GameDto.builder()
        .gameId(game.getGameId())
        .gameTitle(game.getGameTitle())
        .singlePlayer(game.isSinglePlayer())
        .multiPlayer(game.isMultiPlayer())
        .primaryGenreId(
            game.getPrimaryGenre() != null ? game.getPrimaryGenre().getGenreId() : null)
        .userIds(
            game.getUsers() != null
                ? game.getUsers().stream().map(user -> user.getUserId()).collect(Collectors.toSet())
                : new HashSet<>())
        .build();
  }

  public Game toEntity(GenreRepository genreRepository) throws EntityNotFoundException {

    Game.GameBuilder gameBuilder = Game.builder()
        .gameId(this.gameId != null ? this.gameId : 0)
        .gameTitle(this.gameTitle)
        .singlePlayer(this.singlePlayer)
        .multiPlayer(this.multiPlayer);

    if (this.primaryGenreId != null) {
      Genre genre = genreRepository
          .findById(this.primaryGenreId)
          .orElseThrow(() -> new EntityNotFoundException("Genre not found"));
      gameBuilder.primaryGenre(genre);
    }

    return gameBuilder.build();
  }

  public void setUserIds(Set<Integer> userIds) {

    if (userIds == null) {
      this.userIds = new HashSet<>();
    } else {
      this.userIds = userIds;
    }
  }
}
