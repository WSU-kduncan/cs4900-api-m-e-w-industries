package com.mew.demo.mapper;

import com.mew.demo.dto.GenreDto;
import com.mew.demo.model.Genre;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreDtoMapper {

  GenreDto toDto(Genre genre);

  Genre toEntity(GenreDto dto);
}
