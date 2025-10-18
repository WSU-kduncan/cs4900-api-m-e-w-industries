package com.MEW.demo.dto;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import com.MEW.demo.model.Console;
import com.MEW.demo.model.Game;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.ConsoleRepository;
import com.MEW.demo.repository.GameRepository;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import jakarta.validation.constraints.*;

@Builder
@Data
@Value
public class UserDto {
    
    private Integer userId;

    @NotBlank(message = "First name is required")
    private String firstName;

    private String lastName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Gamertag is required")
    private String gamertag;

    @NotNull(message = "Preferred console must be specified")
    private Integer consoleId;

    private String aboutUser;
    
    private Set<Integer> gameIds;

    public User toEntity(ConsoleRepository consoleRepository, GameRepository gameRepository) {

        User.UserBuilder userBuilder = User.builder()
            .userId(this.userId != null ? this.userId : 0)
            .firstName(this.firstName)
            .lastName(this.lastName)
            .dob(this.dob)
            .email(this.email)
            .gamertag(this.gamertag)
            .aboutUser(this.aboutUser);

            if(this.consoleId != null) {
                
                Console console = consoleRepository.findById(this.consoleId)
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Console with ID " + this.consoleId + " not found."));

                userBuilder.preferredConsole(console);
            }

            if(this.gameIds != null && !this.gameIds.isEmpty()) {
                
                Set<Game> games = this.gameIds.stream()
                    .map(gameId -> gameRepository.findById(gameId)
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Game with ID " + gameId + " not found.")))
                    .collect(Collectors.toSet());
            userBuilder.games(games);
        }

        return userBuilder.build();
    }

    public static UserDto fromEntity(User user) {
        
        if (user == null) return null;

        return UserDto.builder()
            .userId(user.getUserId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .dob(user.getDob())
            .email(user.getEmail())
            .gamertag(user.getGamertag())
            .consoleId(
                user.getPreferredConsole() != null ? user.getPreferredConsole().getConsoleId() : null
            )
            .aboutUser(user.getAboutUser())
            .gameIds(
                user.getGames() != null
                    ? user.getGames().stream()
                        .map(Game::getGameId)
                        .collect(Collectors.toSet())
                    : null
            )
            .build();
    }
}