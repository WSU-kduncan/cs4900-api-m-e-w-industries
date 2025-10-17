package com.MEW.demo.dto;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.ConsoleRepository;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class UserDto {
    
    private int userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String gamertag;
    private Integer consoleId;
    private String aboutUser;
    private java.util.Set<Integer> gameIds;

    public User toEntity(ConsoleRepository consoleRepository) {

        User.UserBuilder userBuilder = User.builder()
            .userId(this.userId)
            .firstName(this.firstName)
            .lastName(this.lastName)
            .dob(java.time.LocalDate.parse(this.dob))
            .email(this.email)
            .gamertag(this.gamertag)
            .aboutUser(this.aboutUser);

            if(this.consoleId != null) {
                userBuilder.preferredConsole(
                    consoleRepository.findById(this.consoleId).orElseThrow(
                        () -> new IllegalArgumentException("Console with ID " + this.consoleId + " not found.")
                    )
                );
            }

        return userBuilder.build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
            .userId(user.getUserId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .dob(user.getDob().toString())
            .email(user.getEmail())
            .gamertag(user.getGamertag())
            .consoleId(user.getPreferredConsole().getConsoleId())
            .aboutUser(user.getAboutUser())
            .gameIds(user.getGames().stream().map(game -> game.getGameId()).collect(java.util.stream.Collectors.toSet()))
            .build();
    }
}