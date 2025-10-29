package com.MEW.demo.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MEW.demo.dto.MatchInfoDto;
import com.MEW.demo.dto.MatchedUserDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.model.MatchedUser;
import com.MEW.demo.model.MatchedUserId;
import com.MEW.demo.model.User;
import com.MEW.demo.repository.MatchedUserRepository;
import com.MEW.demo.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchService {

    @Autowired
    private final MatchedUserRepository matchedUserRepository;
    
    @Autowired
    private final UserRepository userRepository;

    public List<MatchedUserDto> getAllMatchesForUser(Integer userId) throws EntityNotFoundException {
        
        List<MatchedUser> matches = matchedUserRepository.findAllMatchesForUser(userId);

        if (matches.isEmpty()) {
            throw new EntityNotFoundException("No matches found for user ID: " + userId);
        }

        return matches.stream()
                .map(MatchedUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public MatchInfoDto getMatchInfo(Integer userId, Integer matchId) {
        
        return matchedUserRepository.findMatchInfo(userId, matchId);
    }

    @Transactional
    public void updateMatchStatus(Integer userId, Integer matchId) {
    
        // Check if a reciprocal like exists
        Optional<MatchedUser> reciprocal = Optional.ofNullable(matchedUserRepository.findMatch(matchId, userId));

        if (reciprocal.isPresent()) {
            matchedUserRepository.updateIsMatched(userId, matchId, true);
            matchedUserRepository.updateIsMatched(matchId, userId, true);
        } else {

            MatchedUser newLike = new MatchedUser();
            Optional<User> user1 = userRepository.findById(userId);
            Optional<User> user2 = userRepository.findById(matchId);

            newLike.setId(new MatchedUserId(userId, matchId));
            newLike.setUser1(user1);
            newLike.setUser2(user2);
            newLike.setIsMatched(false);
            matchedUserRepository.save(newLike);
        }
    }
}
