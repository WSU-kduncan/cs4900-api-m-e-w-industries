package com.mew.demo.dto;
import com.mew.demo.model.Genre;

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

    public static GenreDto fromEntity(Genre genre) {
       
        if(genre == null) return null;
        
        return GenreDto.builder()
            .genreId(genre.getGenreId())
            .genreName(genre.getGenreName())
            .genreDescription(genre.getGenreDescription())
            .build();
    }

    public Genre toEntity() {
        
        return Genre.builder()
            .genreId(this.genreId)
            .genreName(this.genreName)
            .genreDescription(this.genreDescription)
            .build();
    }
}
