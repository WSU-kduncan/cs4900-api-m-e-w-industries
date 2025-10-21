package com.MEW.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MEW.demo.dto.UserDto;
import com.MEW.demo.mapper.UserMapper;
import com.MEW.demo.model.User;
import com.MEW.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(UserMapper.toUserDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<UserDto> findUsersByFirstName(@RequestParam String firstName) {
        return userService.findUsersByFirstName(firstName).stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }
}



