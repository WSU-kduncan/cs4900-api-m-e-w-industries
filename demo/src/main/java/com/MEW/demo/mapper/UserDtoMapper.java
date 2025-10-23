package com.MEW.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.RequestBody;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.model.User;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.service.UserService;

@Mapper(
    componentModel = "spring",
    uses = {UserService.class})
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

    UserDto toDto(User user) throws EntityNotFoundException;

    void updateUser(RequestBody RequestBody, User user) throws EntityNotFoundException;
}