package com.restexamplesql.springboot.mapper;

import com.restexamplesql.springboot.dto.UserDto;
import com.restexamplesql.springboot.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return  userDto;
    }

    public static User mapToUser(UserDto userDto) {
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
