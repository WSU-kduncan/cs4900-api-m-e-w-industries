package com.mew.demo.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mew.demo.dto.MatchInfoDto;
import com.mew.demo.dto.MatchedUserDto;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.service.MatchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchedUserController {

    @Autowired
    private final MatchService matchService;

    @GetMapping("/user/id/{userId}")
    public ResponseEntity<List<MatchedUserDto>> getMatchesForUser(@PathVariable Integer userId)
            throws EntityNotFoundException {
        List<MatchedUserDto> matches = matchService.getAllMatchesForUser(userId);
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{userId}/{matchId}")
    public ResponseEntity<MatchInfoDto> getMatchInfo(@PathVariable Integer userId, @PathVariable Integer matchId) {

        MatchInfoDto matchInfo = matchService.getMatchInfo(userId, matchId);

        if (matchInfo == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(matchInfo);
    }

    @PatchMapping("/{userId}/{matchId}")
    public ResponseEntity<Void> updateMatchStatus(@PathVariable Integer userId, @PathVariable Integer matchId) {
        
        matchService.updateMatchStatus(userId, matchId);
        return ResponseEntity.noContent().build();
    }
}
