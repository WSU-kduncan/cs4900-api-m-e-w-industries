package com.mew.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class CreateUserRequest {
  @NotBlank private String firstName;
  private String lastName;
  @NotNull  private LocalDate dob;        // "2001-05-20"
  @Email @NotBlank private String email;
  @NotBlank private String gamertag;
  private String about;

  @NotNull  private Integer consoleId;    // Required by your entity
  private List<Integer> gameIds;          // optional
  private List<Integer> likedUserIds;     // optional
}
