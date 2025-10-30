package com.mew.demo.service;

import com.mew.demo.dto.GameDto;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.model.Game;
import com.mew.demo.repository.GameRepository;
import com.mew.demo.repository.GenreRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GenreRepository genreRepository;
  // private final GameDtoMapper gameDtoMapper;

  public List<Game> convertDtosToGames(List<GameDto> gameDtos) throws EntityNotFoundException {

    return new ArrayList<>(gameDtos)
        .stream()
            .map(dto -> {
              try {
                return dto.toEntity(genreRepository);
              } catch (EntityNotFoundException e) {
                e.getLocalizedMessage();
                e.printStackTrace();
              }
              return null;
            })
            .toList();
  }

  public List<Game> getAllGames() throws EntityNotFoundException {

    return gameRepository.findAll();
  }

  public Game getGameById(Integer gameId) throws EntityNotFoundException {

    return gameRepository
        .findById(gameId)
        .orElseThrow(() -> new EntityNotFoundException("Game not found with ID " + gameId));
  }

  public Game getGameByTitle(String gameTitle) throws EntityNotFoundException {

    return gameRepository
        .findByGameTitle(gameTitle)
        .orElseThrow(
            () -> new EntityNotFoundException("Game not found with title '" + gameTitle + "'"));
  }

  @Transactional
  public GameDto createGame(GameDto dto) throws EntityNotFoundException {

    if (dto == null) {
      throw new IllegalArgumentException("Request body is required");
    }
    if (dto.getGameTitle() == null || dto.getGameTitle().trim().isEmpty()) {
      throw new IllegalArgumentException("gameTitle is required");
    }
    if (dto.getPrimaryGenreId() == null || dto.getPrimaryGenreId() == null) {
      throw new IllegalArgumentException("primaryGenre.genreId is required");
    }

    Game game = dto.toEntity(genreRepository);

    Game savedGame = gameRepository.save(game);

    return GameDto.fromEntity(savedGame);
  }
}
