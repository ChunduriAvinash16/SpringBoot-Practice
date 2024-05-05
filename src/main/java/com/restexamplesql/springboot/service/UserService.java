package com.restexamplesql.springboot.service;

import com.restexamplesql.springboot.dto.UserDto;
import com.restexamplesql.springboot.entity.User;

import java.util.List;

public interface UserService {
     UserDto createUser(UserDto user);

     UserDto getUserById(Long id);

     List<UserDto> getAllUsers();

     UserDto updaateUser(UserDto userDto);

     void deleteUser(Long userId);
}
