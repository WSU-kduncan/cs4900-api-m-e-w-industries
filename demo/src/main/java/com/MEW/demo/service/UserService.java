package com.MEW.demo.service;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.ConsoleRepository;
import com.MEW.demo.repository.GameRepository;
import com.MEW.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.exception.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final ConsoleRepository consoleRepository;
    private final GameRepository gameRepository;

    public List<User> convertDtosToUsers(List<UserDto> userDtos) throws EntityNotFoundException {
        
        return userDtos.stream()
            .map(dto -> dto.toEntity(consoleRepository, gameRepository))
            .toList();
    }

    public List<User> getAllUsers() throws EntityNotFoundException {

        return userRepository.findAllWithGamesAndConsole()
            .stream()
            .collect(Collectors.toList());
    }
    
    public User getUserById(Integer userId) throws EntityNotFoundException {
        
        Optional<User> optionalUser = userRepository.findById(userId);
        return User.fromOptional(optionalUser, "ID=" + userId);
    }

    public User getUserByFirstName(String firstName) throws EntityNotFoundException {
        
        Optional<User> optionalUser = userRepository.findByFirstName(firstName);
        return User.fromOptional(optionalUser, "firstName=" + firstName);
    }
}