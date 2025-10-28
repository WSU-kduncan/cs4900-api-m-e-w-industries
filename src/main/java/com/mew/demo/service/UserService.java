package com.mew.demo.service;

import com.mew.demo.dto.CreateUserRequest;
import com.mew.demo.dto.UpdateUserRequest;
import com.mew.demo.model.Console;
import com.mew.demo.model.Game;
import com.mew.demo.model.User;
import com.mew.demo.repository.ConsoleRepository;
import com.mew.demo.repository.GameRepository;
import com.mew.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final ConsoleRepository consoleRepository;
  private final GameRepository gameRepository;

  

  public List<User> getAll() {
    return repository.findAll();
  }

  public User getById(Integer id) {
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public List<User> searchByEmail(String q) {
    return repository.findByEmailContainingIgnoreCase(q);
  }

 

  @Transactional
  public User create(CreateUserRequest req) {
   
    List<User> matches = repository.findByEmailContainingIgnoreCase(req.getEmail());
    boolean exactExists = matches.stream()
        .anyMatch(u -> u.getEmail() != null && u.getEmail().equalsIgnoreCase(req.getEmail()));
    if (exactExists) {
      throw new IllegalArgumentException("Email already exists: " + req.getEmail());
    }

   
    Console console = consoleRepository.findById(req.getConsoleId())
        .orElseThrow(() -> new IllegalArgumentException("Console not found: " + req.getConsoleId()));

   
    List<Game> games = (req.getGameIds() == null || req.getGameIds().isEmpty())
        ? new ArrayList<>()
        : gameRepository.findAllById(req.getGameIds());
    if (req.getGameIds() != null && games.size() != new HashSet<>(req.getGameIds()).size()) {
      throw new IllegalArgumentException("One or more gameIds do not exist.");
    }

    
    List<User> likedUsers = (req.getLikedUserIds() == null || req.getLikedUserIds().isEmpty())
        ? new ArrayList<>()
        : repository.findAllById(req.getLikedUserIds());
    if (req.getLikedUserIds() != null && likedUsers.size() != new HashSet<>(req.getLikedUserIds()).size()) {
      throw new IllegalArgumentException("One or more likedUserIds do not exist.");
    }

    
    User u = new User();
    u.setFirstName(req.getFirstName());
    u.setLastName(req.getLastName());
    u.setDob(req.getDob());
    u.setEmail(req.getEmail());
    u.setGamertag(req.getGamertag());
    u.setAbout(req.getAbout());
    u.setConsole(console);
    u.setGames(games);
    u.setLikedUsers(likedUsers);

    return repository.save(u);
  }



  @Transactional
  public User update(Integer id, UpdateUserRequest req) {
    User u = repository.findById(id).orElseThrow(EntityNotFoundException::new);

    
    if (req.getEmail() != null && !req.getEmail().equalsIgnoreCase(u.getEmail())) {
      List<User> matches = repository.findByEmailContainingIgnoreCase(req.getEmail());
      boolean takenByAnother = matches.stream().anyMatch(other ->
          other.getEmail() != null
              && other.getEmail().equalsIgnoreCase(req.getEmail())
              && !Objects.equals(other.getId(), id));
      if (takenByAnother) {
        throw new IllegalArgumentException("Email already in use: " + req.getEmail());
      }
      u.setEmail(req.getEmail());
    }

    if (req.getFirstName() != null) u.setFirstName(req.getFirstName());
    if (req.getLastName()  != null) u.setLastName(req.getLastName());
    if (req.getDob()       != null) u.setDob(req.getDob());
    if (req.getGamertag()  != null) u.setGamertag(req.getGamertag());
    if (req.getAbout()     != null) u.setAbout(req.getAbout());

    
    if (req.getConsoleId() != null) {
      Console console = consoleRepository.findById(req.getConsoleId())
          .orElseThrow(() -> new IllegalArgumentException("Console not found: " + req.getConsoleId()));
      u.setConsole(console);
    }

   
    if (req.getGameIds() != null) {
      List<Game> games = gameRepository.findAllById(req.getGameIds());
      if (games.size() != new HashSet<>(req.getGameIds()).size()) {
        throw new IllegalArgumentException("One or more gameIds do not exist.");
      }
      u.setGames(games);
    }

    if (req.getLikedUserIds() != null) {
      List<User> liked = repository.findAllById(req.getLikedUserIds());
      if (liked.size() != new HashSet<>(req.getLikedUserIds()).size()) {
        throw new IllegalArgumentException("One or more likedUserIds do not exist.");
      }
      u.setLikedUsers(liked);
    }

    return repository.save(u);
  }
}
