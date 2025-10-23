package com.MEW.demo.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.MEW.demo.dto.MatchedUserDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.MatchedUser;
import com.MEW.demo.repository.MatchedUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchedUserRepository matchedUserRepository;

    public List<MatchedUserDto> getAllMatchesForUser(Integer userId) throws EntityNotFoundException {
        List<MatchedUser> matches = matchedUserRepository.findAllMatchesForUser(userId);

        if (matches.isEmpty()) {
            throw new EntityNotFoundException("No matches found for user ID: " + userId);
        }

        return matches.stream()
                .map(MatchedUserDto::fromEntity)
                .collect(Collectors.toList());
    }
}
