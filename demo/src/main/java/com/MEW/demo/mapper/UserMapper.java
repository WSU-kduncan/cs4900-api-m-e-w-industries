package com.MEW.demo.mapper;

import com.MEW.demo.dto.UserDto;
import com.MEW.demo.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDob(user.getDob());
        dto.setEmail(user.getEmail());
        dto.setGamertag(user.getGamertag());
        dto.setAboutUser(user.getAboutUser());
        return dto;
    }
}
