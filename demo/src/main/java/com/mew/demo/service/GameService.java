package com.mew.demo.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mew.demo.dto.GameDto;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.model.Game;
import com.mew.demo.model.Genre;
import com.mew.demo.repository.GameRepository;
import com.mew.demo.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {
    
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    //private final GameDtoMapper gameDtoMapper;

    public List<Game> convertDtosToGames(List<GameDto> gameDtos) throws EntityNotFoundException {
        
        return new ArrayList<>(gameDtos).stream()
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
        
        return gameRepository.findById(gameId)
            .orElseThrow(() -> new EntityNotFoundException("Game not found with ID " + gameId));
    }

    public Game getGameByTitle(String gameTitle) throws EntityNotFoundException {
        
         return gameRepository.findByGameTitle(gameTitle)
            .orElseThrow(() -> new EntityNotFoundException("Game not found with title '" + gameTitle + "'"));
    }

    @Transactional
    public GameDto createGame(GameDto dto) throws EntityNotFoundException {
        
        Game game = new Game();

        if (dto.getPrimaryGenre() != null && dto.getPrimaryGenre().getGenreId() != null) {
            Genre genre = genreRepository.findById(dto.getPrimaryGenre().getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Genre not found"));
            game.setPrimaryGenre(genre);
        }

        Game savedGame = gameRepository.save(game);
        
        return GameDto.fromEntity(savedGame);
    }
}
