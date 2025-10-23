package com.MEW.demo.mapper;
import org.mapstruct.Mapper;
import com.MEW.demo.dto.GenreDto;
import com.MEW.demo.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreDtoMapper {
    
    GenreDto toDto(Genre genre);
    Genre toEntity(GenreDto dto);
}
