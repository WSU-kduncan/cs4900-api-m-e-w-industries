package com.MEW.demo.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class UserDto {
    
    private int userId;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String gamertag;
    private int consoleId;
    private String aboutUser;
    private java.util.Set<Integer> gameIds;
}