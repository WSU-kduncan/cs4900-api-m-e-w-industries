package com.MEW.demo.controller;

import com.MEW.demo.dto.UserDto;
import com.MEW.demo.mapper.UserDtoMapper;
import com.MEW.demo.model.User;
import com.MEW.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(
    path = "user",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {

    private final UserService userService;
    private final UserDtoMapper mapper;

    // 1️⃣ Get all users
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(mapper.toDtoList(users));
    }

    // 2️⃣ Get user by ID
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getById(id);
            return ResponseEntity.ok(mapper.toDto(user));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 3️⃣ Search users by email
    // Example: GET /user/search?q=gmail.com
    @GetMapping("search")
    public ResponseEntity<List<UserDto>> searchUserByEmail(@RequestParam String q) {
        List<User> users = userService.searchByEmail(q);
        return ResponseEntity.ok(mapper.toDtoList(users));
    }
}
