package com.MEW.demo.service;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.MEW.demo.exception.EntityNotFoundException;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
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