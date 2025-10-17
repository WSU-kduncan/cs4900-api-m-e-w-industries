package com.MEW.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import lombok.RequiredArgsConstructor;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.mapper.UserDtoMapper;
import com.MEW.demo.service.UserService;
import java.util.List;
import com.MEW.demo.exception.EntityNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@RestController
@RequestMapping( path = "/users",
                 produces = MediaType.APPLICATION_JSON_VALUE,
                 consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserDtoMapper userDtoMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() throws EntityNotFoundException {
        return new ResponseEntity<>(
            userDtoMapper.toDtoList(userService.getAllUsers()), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) throws EntityNotFoundException {
        
        return new ResponseEntity<>(
            userDtoMapper.toDto(userService.getUserById(userId)), HttpStatus.OK);
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<UserDto> getUserByFirstName(@PathVariable String firstName) throws EntityNotFoundException {
        
        return new ResponseEntity<>(
            userDtoMapper.toDto(userService.getUserByFirstName(firstName)), HttpStatus.OK);
    }
}