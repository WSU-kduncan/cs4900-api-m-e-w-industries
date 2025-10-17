package com.MEW.demo.mapper;
import org.mapstruct.Mapper;
import com.MEW.demo.dto.UserDto;
import com.MEW.demo.model.User;
import com.MEW.demo.exception.EntityNotFoundException;
import com.MEW.demo.service.UserService;
import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {UserService.class})
public interface UserDtoMapper {

    User toEntity(UserDto userDto) throws EntityNotFoundException;

    UserDto toDto(User user) throws EntityNotFoundException;

    List<UserDto> toDtoList(List<User> userList) throws EntityNotFoundException;
}