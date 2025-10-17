package com.MEW.demo.service;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.ConsoleRepository;
import com.MEW.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.exception.EntityNotFoundException;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final ConsoleRepository consoleRepository;

    public List<User> convertDtosToUsers(List<UserDto> userDtos) throws EntityNotFoundException {
        return userDtos.stream()
            .map(dto -> dto.toEntity(consoleRepository))
            .toList();
        }

    public List<UserDto> getAllUsers() throws EntityNotFoundException {

        List<UserDto> userDto = userRepository.findAll()
            .stream()
            .map(UserDto::fromEntity)
            .toList();

        List<UserDto> modifiableList = new java.util.ArrayList<>(userDto);

        return modifiableList;
    }
    
    public User getUserById(Integer userId) throws EntityNotFoundException {
        Optional<User> result = userRepository.findById(userId);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("User with ID " + userId + " not found.");
        }
        return result.get();
    }

    public User getUserByFirstName(String firstName) throws EntityNotFoundException {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(firstName)) {
                return user;
            }
        }
        throw new EntityNotFoundException("User with first name " + firstName + " not found.");
    }
}