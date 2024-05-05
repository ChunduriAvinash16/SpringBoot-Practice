package com.restexamplesql.springboot.controller;

import com.restexamplesql.springboot.dto.UserDto;
import com.restexamplesql.springboot.entity.User;
import com.restexamplesql.springboot.exception.ErrorDetails;
import com.restexamplesql.springboot.exception.ResourceNotFoundException;
import com.restexamplesql.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "User Controller",
        description = "CreateUser,GetUser, GetAllUSer,Update User,Delete User"
)
@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private  UserService userService;

    @Operation(
            summary = "Save the User",
            description = "Create new User"
    )
    @ApiResponse(
            responseCode = "201",
            description = "User Created"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto user) {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get User By Id",
            description = "Retrieve User By Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User Retrieved"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }


    @Operation(
            summary = "Get All Users",
            description = "Retrieve All Users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Users Retrieved"
    )
    @GetMapping
    public  ResponseEntity<List<UserDto>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }


    @Operation(
            summary = "UpdateUser By Id",
            description = "Update USer details Based on Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User Updated"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody @Valid UserDto user) {
        user.setId(userId);
        UserDto updatedUser = userService.updaateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }



    @Operation(
            summary = "Delete User By Id",
            description = "Delete USer Based on Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "User Deleted"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundExcpeiton(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest) {
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new  ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
//    }

}
