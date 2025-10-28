package com.MEW.demo.mapper;

import com.MEW.demo.dto.GenreDto;
import com.MEW.demo.model.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-23T21:58:31-0400",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251001-1143, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class GenreDtoMapperImpl implements GenreDtoMapper {

    @Override
    public GenreDto toDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto.GenreDtoBuilder genreDto = GenreDto.builder();

        genreDto.genreDescription( genre.getGenreDescription() );
        genreDto.genreId( genre.getGenreId() );
        genreDto.genreName( genre.getGenreName() );

        return genreDto.build();
    }

    @Override
    public Genre toEntity(GenreDto dto) {
        if ( dto == null ) {
            return null;
        }

        Genre.GenreBuilder genre = Genre.builder();

        genre.genreDescription( dto.getGenreDescription() );
        if ( dto.getGenreId() != null ) {
            genre.genreId( dto.getGenreId() );
        }
        genre.genreName( dto.getGenreName() );

        return genre.build();
    }
}
