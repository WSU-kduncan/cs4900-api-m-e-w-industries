package com.MEW.demo.dto;

import java.time.LocalDate;
import java.util.List;

public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        String fullName,
        LocalDate dob,
        String email,
        String gamertag,
        Byte consoleId,
        String name,
        List<GameSummaryDto> games,
        List<Integer> likedUserIds,
        List<Integer> likedByIds,
        String about
) {}
