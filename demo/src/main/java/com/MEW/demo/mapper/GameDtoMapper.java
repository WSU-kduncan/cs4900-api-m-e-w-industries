package com.MEW.demo.mapper;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.MEW.demo.dto.GameDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.Game;
import com.MEW.demo.service.GameService;

@Mapper(componentModel = "spring",
uses = {GameService.class, GenreDtoMapper.class})
public interface GameDtoMapper {
    
    @Mapping(target = "primaryGenre", ignore = true)
    @Mapping(target = "users", ignore = true)
    Game toEntity(GameDto gameDto) throws EntityNotFoundException;

    @Mapping(target = "primaryGenreId", source = "primaryGenre.genreId")
    @Mapping(target = "userIds", expression = "java(game.getUsers().stream().map(User::getUserId).collect(java.util.stream.Collectors.toSet()))")
    GameDto toDto(Game game) throws EntityNotFoundException;

    List<GameDto> toDtoList(List<Game> gameList) throws EntityNotFoundException;
}
