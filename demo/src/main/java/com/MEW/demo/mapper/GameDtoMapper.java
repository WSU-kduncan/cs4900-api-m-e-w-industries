package com.MEW.demo.mapper;
import java.util.List;
import org.mapstruct.Mapper;
import com.MEW.demo.dto.GameDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.Game;
import com.MEW.demo.service.GameService;

@Mapper(componentModel = "spring",
uses = {GameService.class})
public interface GameDtoMapper {
    
    Game toEntity(GameDto gameDto) throws EntityNotFoundException;

    GameDto toDto(Game game) throws EntityNotFoundException;

    List<GameDto> toDtoList(List<Game> gameList) throws EntityNotFoundException;
}
