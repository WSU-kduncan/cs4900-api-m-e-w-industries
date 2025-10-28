// src/main/java/com/mew/demo/dto/UserResponse.java
package com.mew.demo.dto;   // <-- change to dto

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponse {
  private Integer id;
  private String firstName;
  private String lastName;
  private LocalDate dob;
  private String email;
  private String gamertag;
  private String about;
  private Integer consoleId;
  private String consoleName;
  private List<Integer> gameIds;
  private List<Integer> likedUserIds;
  private List<Integer> likedByIds;
}
