package com.mew.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.RequestBody;
import com.mew.demo.dto.UserDto;
import com.mew.demo.exception.EntityNotFoundException;
import com.mew.demo.model.User;
import com.mew.demo.service.UserService;

@Mapper(
    componentModel = "spring",
    uses = {UserService.class})
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto toDto(User user) throws EntityNotFoundException;

    UserDto updateUser(RequestBody RequestBody, User user) throws EntityNotFoundException;
}