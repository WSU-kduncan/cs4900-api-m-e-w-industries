package com.mew.demo.mapper;

import com.mew.demo.dto.GameSummaryDto;
import com.mew.demo.dto.UserDto;
import com.mew.demo.model.Game;
import com.mew.demo.model.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper {

  public UserDto toDto(User u) {
    if (u == null) return null;

    var consoleId = (u.getConsole() != null) ? u.getConsole().getId() : null;
    var consoleName = (u.getConsole() != null) ? u.getConsole().getName() : null;

    List<GameSummaryDto> games = safe(u.getGames()).stream()
        .filter(Objects::nonNull)
        .map(this::toGameSummary)
        .collect(Collectors.toList());

    List<Integer> likedUserIds = safe(u.getLikedUsers()).stream()
        .map(User::getId)
        .filter(Objects::nonNull)
        .toList();

    List<Integer> likedByIds =
        safe(u.getLikedBy()).stream().map(User::getId).filter(Objects::nonNull).toList();

    String fullName = u.getFirstName()
        + (u.getLastName() != null && !u.getLastName().isBlank() ? " " + u.getLastName() : "");

    return new UserDto(
        u.getId(),
        u.getFirstName(),
        u.getLastName(),
        fullName,
        u.getDob(),
        u.getEmail(),
        u.getGamertag(),
        consoleId,
        consoleName,
        games,
        likedUserIds,
        likedByIds,
        u.getAbout());
  }

  public List<UserDto> toDtoList(List<User> users) {
    return safe(users).stream().map(this::toDto).toList();
  }

  private GameSummaryDto toGameSummary(Game g) {
    // matches entity: Game.title (SQL: Game_Title)
    return new GameSummaryDto(g.getId(), g.getTitle());
  }

  private static <T> List<T> safe(List<T> list) {
    return list == null ? List.of() : list;
  }
}
