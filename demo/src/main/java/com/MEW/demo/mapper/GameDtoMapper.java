package com.MEW.demo.mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
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

    @Mapping(target = "primaryGenre", source = "primaryGenre")
    @Mapping(target = "userIds", 
             expression = "java(game.getUsers() != null ? game.getUsers().stream().map(User::getUserId).collect(java.util.stream.Collectors.toSet())" + 
                         ": java.util.Collections.emptySet())")
    GameDto toDto(Game game) throws EntityNotFoundException;

    List<GameDto> toDtoList(List<Game> gameList) throws EntityNotFoundException;

    @AfterMapping
    default void mapUserIds(Game game, @MappingTarget GameDto dto) {

        if (game.getUsers() != null) {
            dto.getUserIds().addAll(
                game.getUsers()
                    .stream()
                    .map(u -> u.getUserId())
                    .collect(Collectors.toSet())
            );
        }
    }
}
