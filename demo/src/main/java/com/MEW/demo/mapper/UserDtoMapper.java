package com.MEW.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.model.Game;
import com.MEW.demo.model.User;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.service.UserService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    uses = {UserService.class})
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    User toEntity(UserDto userDto) throws EntityNotFoundException;

    @Mapping(source = "preferredConsole.consoleId", target = "consoleId")
    @Mapping(source = "games", target = "gameIds")
    UserDto toDto(User user) throws EntityNotFoundException;

    List<UserDto> toDtoList(List<User> userList) throws EntityNotFoundException;

    default Set<Integer> mapGamesToIds(Set<Game> games) {
        if (games == null) return Set.of();
        return games.stream().map(Game::getGameId).collect(Collectors.toSet());
    }
}