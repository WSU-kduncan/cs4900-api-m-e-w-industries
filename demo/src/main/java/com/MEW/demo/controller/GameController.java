package com.MEW.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MEW.demo.dto.GameDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.mapper.GameDtoMapper;
import com.MEW.demo.model.Game;
import com.MEW.demo.service.GameService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping( path = "/games",
                 produces = MediaType.APPLICATION_JSON_VALUE)
public class GameController {
    
    private final GameService gameService;
    private final GameDtoMapper gameDtoMapper;
    
    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() throws EntityNotFoundException {
        
        List<GameDto> dtos = gameDtoMapper.toDtoList(gameService.getAllGames());
        
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{gameId}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("gameId") Integer gameId) throws EntityNotFoundException {

        Game game = gameService.getGameById(gameId);
        return ResponseEntity.ok(gameDtoMapper.toDto(game));
    }

    @GetMapping("/title/{gameTitle}")
    public ResponseEntity<GameDto> getGameByTitle(@PathVariable("gameTitle") String gameTitle) throws EntityNotFoundException {

        Game game = gameService.getGameByTitle(gameTitle);
        return ResponseEntity.ok(gameDtoMapper.toDto(game));
    }

    @GetMapping("/debug")
    public ResponseEntity<String> debug() {
        return ResponseEntity.ok("GameController reachable");
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("GameController is alive!");
    }
}
