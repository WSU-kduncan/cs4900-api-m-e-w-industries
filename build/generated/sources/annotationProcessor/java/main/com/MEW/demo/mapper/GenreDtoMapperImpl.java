package com.MEW.demo.mapper;

import com.MEW.demo.dto.GenreDto;
import com.MEW.demo.model.Genre;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T13:12:19-0400",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.16 (Homebrew)"
)
@Component
public class GenreDtoMapperImpl implements GenreDtoMapper {

    @Override
    public GenreDto toDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreDto.GenreDtoBuilder genreDto = GenreDto.builder();

        genreDto.genreId( genre.getGenreId() );
        genreDto.genreName( genre.getGenreName() );
        genreDto.genreDescription( genre.getGenreDescription() );

        return genreDto.build();
    }

    @Override
    public Genre toEntity(GenreDto dto) {
        if ( dto == null ) {
            return null;
        }

        Genre.GenreBuilder genre = Genre.builder();

        if ( dto.getGenreId() != null ) {
            genre.genreId( dto.getGenreId() );
        }
        genre.genreName( dto.getGenreName() );
        genre.genreDescription( dto.getGenreDescription() );

        return genre.build();
    }
}
