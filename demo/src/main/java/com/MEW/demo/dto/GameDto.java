package com.MEW.demo.dto;
import lombok.Value;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Value
public class GameDto {
    
    private Integer gameId;
    private String gameTitle;
    private boolean isSinglePlayer;
    private boolean isMultiPlayer;
    private Integer primaryGenreId;
}
