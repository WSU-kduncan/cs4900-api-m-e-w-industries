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
}