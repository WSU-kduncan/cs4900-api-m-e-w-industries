package com.mew.demo.mapper;
import org.mapstruct.Mapper;
import com.mew.demo.dto.GenreDto;
import com.mew.demo.model.Genre;

@Mapper(componentModel = "spring")
public interface GenreDtoMapper {
    
    GenreDto toDto(Genre genre);
    Genre toEntity(GenreDto dto);
}
