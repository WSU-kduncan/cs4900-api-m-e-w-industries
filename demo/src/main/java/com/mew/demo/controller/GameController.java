package com.mew.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mew.demo.dto.GameDto;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.model.Game;
import com.mew.demo.service.GameService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping( path = "/games",
                 produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    
    private final GameService gameService;
    //private final GameDtoMapper gameDtoMapper;
    
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() throws EntityNotFoundException {
        
        List<GameDto> dtos = gameService.getAllGames().stream()
                                    .map(GameDto::fromEntity)
                                    .toList();
        
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{gameId}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("gameId") Integer gameId) throws EntityNotFoundException {

        Game game = gameService.getGameById(gameId);
        return ResponseEntity.ok(GameDto.fromEntity(game));
    }

    @GetMapping("/title/{gameTitle}")
    public ResponseEntity<GameDto> getGameByTitle(@PathVariable("gameTitle") String gameTitle) throws EntityNotFoundException {

        Game game = gameService.getGameByTitle(gameTitle);
        return ResponseEntity.ok(GameDto.fromEntity(game));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) throws EntityNotFoundException {
        GameDto createdGame = gameService.createGame(gameDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
    }

    // theses next two methods were used for debugging purposes

    // @GetMapping("/debug")
    // public ResponseEntity<String> debug() {
    //     return ResponseEntity.ok("GameController reachable");
    // }

    // @GetMapping("/ping")
    // public ResponseEntity<String> ping() {
    //     return ResponseEntity.ok("GameController is alive!");
    // }
}
