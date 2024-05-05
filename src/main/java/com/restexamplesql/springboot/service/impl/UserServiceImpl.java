package com.restexamplesql.springboot.service.impl;

import com.restexamplesql.springboot.dto.UserDto;
import com.restexamplesql.springboot.entity.User;
import com.restexamplesql.springboot.exception.EmailAlreadyExistException;
import com.restexamplesql.springboot.exception.ResourceNotFoundException;
import com.restexamplesql.springboot.mapper.UserMapper;
import com.restexamplesql.springboot.repository.UserRepository;
import com.restexamplesql.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.mapToUser(userDto);
        Optional<User> byEmail = userRepository.findByEmail(userDto.getEmail());
        if(byEmail.isPresent()) {
            throw new EmailAlreadyExistException("User with mail Id already exists");
        }
        User user = modelMapper.map(userDto,User.class);
        User savedUser = userRepository.save(user);
//        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser,UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",id));
//        UserDto userDto = UserMapper.mapToUserDto(user.get());
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
//        List<UserDto> userDtoList = users
//                .stream()
//                .map(UserMapper::mapToUserDto)
//                .toList();
        List<UserDto> userDtoList = users
                .stream()
                .map((user) -> modelMapper.map(user,UserDto.class))
                .toList();
        return userDtoList;
    }

    @Override
    public UserDto updaateUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto,User.class);
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userDto.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
//        UserDto savedUser = UserMapper.mapToUserDto(userRepository.save(existingUser));
        UserDto savedUser = modelMapper.map(userRepository.save(existingUser),UserDto.class);
        return savedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        System.out.println("Deleting the user");
        userRepository.deleteById(userId);
    }
}
