package com.MEW.demo.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
@Value
public class GenreDto {
    
    private Integer genreId;
    private String genreName;
    private String genreDescription;
}
