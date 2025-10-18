package com.MEW.demo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.MEW.demo.dto.GameDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.Game;
import com.MEW.demo.repository.GameRepository;
import com.MEW.demo.repository.GenreRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GameService {
    
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

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

    public List<GameDto> getAllGames() throws EntityNotFoundException {
        
        List<GameDto> gameDto = gameRepository.findAll()
            .stream()
            .map(GameDto::fromEntity)
            .toList();

             List<GameDto> modifiableList = new java.util.ArrayList<>(gameDto);
             return modifiableList;
    }

    public Game getGameById(Integer gameId) throws EntityNotFoundException {
        
        Optional<Game> result = gameRepository.findById(gameId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + gameId + " not found.");
        }
        return result.get();
    }
}
