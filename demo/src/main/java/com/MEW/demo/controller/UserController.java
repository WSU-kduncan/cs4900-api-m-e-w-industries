package com.MEW.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import com.MEW.demo.dto.UserDto;
//import com.MEW.demo.mapper.UserDtoMapper;
import com.MEW.demo.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import com.MEW.demo.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.MEW.demo.model.User;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users",
                produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    //private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() throws EntityNotFoundException {
        
        List<User> users = userService.getAllUsers();
        List<UserDto> dtos = users.stream()
                                    .map(UserDto::fromEntity)
                                    .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) throws EntityNotFoundException {
        
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(UserDto.fromEntity(user));
    }

    @GetMapping("/name/{firstName}")
    public ResponseEntity<UserDto> getUserByFirstName(@PathVariable String firstName) throws EntityNotFoundException {
        
        User user = userService.getUserByFirstName(firstName);
        return ResponseEntity.ok(UserDto.fromEntity(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws EntityNotFoundException {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}