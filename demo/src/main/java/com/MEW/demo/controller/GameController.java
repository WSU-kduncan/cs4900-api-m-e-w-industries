package com.MEW.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MEW.demo.dto.GameDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.mapper.GameDtoMapper;
import com.MEW.demo.service.GameService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping( path = "/games",
                 produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    
    private final GameService gameService;
    private final GameDtoMapper gameDtoMapper;
    
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() throws EntityNotFoundException {
        
        return new ResponseEntity<>(
            gameDtoMapper.toDtoList(gameService.convertDtosToGames(gameService.getAllGames())), HttpStatus.OK);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Integer gameId) throws EntityNotFoundException {

        return new ResponseEntity<>(
            gameDtoMapper.toDto(gameService.getGameById(gameId)), HttpStatus.OK);
    }
}
