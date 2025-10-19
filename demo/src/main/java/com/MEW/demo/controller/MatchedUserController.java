package com.MEW.demo.controller;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.MEW.demo.dto.MatchedUserDto;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.service.MatchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/matches", produces = MediaType.APPLICATION_JSON_VALUE)
public class MatchedUserController {

    private final MatchService matchService;

    @GetMapping("/user/id/{userId}")
    public ResponseEntity<List<MatchedUserDto>> getMatchesForUser(@PathVariable Integer userId)
            throws EntityNotFoundException {
        List<MatchedUserDto> matches = matchService.getAllMatchesForUser(userId);
        return ResponseEntity.ok(matches);
    }
}
