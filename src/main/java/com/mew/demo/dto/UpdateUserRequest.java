package com.mew.demo.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

/** All fields optional; only non-null values will replace existing ones. */
@Data
public class UpdateUserRequest {
  private String firstName;
  private String lastName;
  private LocalDate dob;
  @Email private String email;
  private String gamertag;
  private String about;

  private Integer consoleId;            // if present, switch console
  private List<Integer> gameIds;        // if present, REPLACE full list
  private List<Integer> likedUserIds;   // if present, REPLACE full list
}
