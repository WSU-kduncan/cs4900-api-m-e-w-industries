package com.MEW.demo.service;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.ConsoleRepository;
import com.MEW.demo.repository.GameRepository;
import com.MEW.demo.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.exception.EntityNotFoundException;
//import com.MEW.demo.mapper.UserDtoMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;
import com.MEW.demo.model.Game;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final ConsoleRepository consoleRepository;
    private final GameRepository gameRepository;
    //private final UserDtoMapper userDtoMapper;

    public List<User> convertDtosToUsers(List<UserDto> userDtos) throws EntityNotFoundException {
        
        return userDtos.stream()
            .map(dto -> dto.toEntity(consoleRepository, gameRepository))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() throws EntityNotFoundException {

        return userRepository.findAllWithGamesAndConsole()
            .stream()
            .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public User getUserById(Integer userId) throws EntityNotFoundException {
        
        Optional<User> optionalUser = userRepository.findById(userId);
        return User.fromOptional(optionalUser, "ID=" + userId);
    }

    @Transactional(readOnly = true)
    public User getUserByFirstName(String firstName) throws EntityNotFoundException {
        
        Optional<User> optionalUser = userRepository.findByFirstName(firstName);
        return User.fromOptional(optionalUser, "firstName=" + firstName);
    }

    @Transactional
    public UserDto createUser(UserDto userDto) throws EntityNotFoundException {
        
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EntityExistsException("A user already exists with this email: " + userDto.getEmail());
        }
        if (userRepository.existsByGamertag(userDto.getGamertag())) {
            throw new EntityExistsException("A user already exists with this gamertag: " + userDto.getGamertag());
        }

        User user = userDto.toEntity(consoleRepository, gameRepository);
        
        return UserDto.fromEntity(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUser(UserDto userDto) throws EntityNotFoundException, IllegalArgumentException {

        if (userDto.getUserId() == null) {
            throw new IllegalArgumentException("UserId must be provided for update.");
        }
             
        User user = getUserById(userDto.getUserId());

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setGamertag(userDto.getGamertag());
        user.setPreferredConsole(consoleRepository.getReferenceById(userDto.getConsoleId()));
        user.setAboutUser(userDto.getAboutUser());
            
        Set<Integer> gameIds = userDto.getGameIds();
        Set<Game> games = gameIds.stream()
                .map(gameId -> gameRepository.findById(gameId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Game with ID " + gameId + " not found.")))
                .collect(Collectors.toSet());
            
        user.setGames(games);

        return UserDto.fromEntity(userRepository.save(user));
    }
}