package com.mew.demo.dto;

import com.mew.demo.model.MatchedUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchedUserDto {

  private Integer userId1;
  private Integer userId2;
  private boolean isMatched;
  private UserDto user1;
  private UserDto user2;

  public static MatchedUserDto fromEntity(MatchedUser match) {
    if (match == null) return null;

    return MatchedUserDto.builder()
        .userId1(match.getUser1().getUserId())
        .userId2(match.getUser2().getUserId())
        .isMatched(match.getIsMatched())
        .user1(UserDto.fromEntity(match.getUser1()))
        .user2(UserDto.fromEntity(match.getUser2()))
        .build();
  }
}
