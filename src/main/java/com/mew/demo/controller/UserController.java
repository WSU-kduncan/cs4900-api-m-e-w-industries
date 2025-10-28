package com.mew.demo.controller;

import com.mew.demo.dto.CreateUserRequest;
import com.mew.demo.dto.UpdateUserRequest;
import com.mew.demo.dto.UserDto;
import com.mew.demo.mapper.UserDtoMapper;
import com.mew.demo.model.User;
import com.mew.demo.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  private final UserService userService;
  private final UserDtoMapper mapper;

  
  @GetMapping
  public ResponseEntity<List<UserDto>> getAllUsers() {
    List<User> users = userService.getAll();
    return ResponseEntity.ok(mapper.toDtoList(users));
  }

 
  @GetMapping("{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
    try {
      User user = userService.getById(id);
      return ResponseEntity.ok(mapper.toDto(user));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  
  // Example: GET /user/search?q=gmail.com
  @GetMapping("search")
  public ResponseEntity<List<UserDto>> searchUserByEmail(@RequestParam String q) {
    List<User> users = userService.searchByEmail(q);
    return ResponseEntity.ok(mapper.toDtoList(users));
  }

  /** POST /user : Create new user */
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserRequest req) {
    try {
      User created = userService.create(req);
      return ResponseEntity
          .created(URI.create("/user/" + created.getId()))
          .body(mapper.toDto(created));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  /** PUT /user/{id} : Update existing user */
  @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> update(@PathVariable Integer id,
                                        @Valid @RequestBody UpdateUserRequest req) {
    try {
      User updated = userService.update(id, req);
      return ResponseEntity.ok(mapper.toDto(updated));
    } catch (EntityNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }

  // your existing endpoints (getAll, getById, searchByEmail) remain as-is
}
