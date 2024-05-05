package com.restexamplesql.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "User Details"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;

    @Schema(description = "User FirstName")
    //User firstName should not be empty or null
    @NotEmpty(message = "User firstName should not be empty or null")
    String firstName;

    @Schema(description = "User Last Name")
    //User latName should not be empty or null
    @NotEmpty(message = "User lasName should not be empty or null")
    String lastName;

    @Schema(description = "User Email Id")
    //User email should not be empty or null
    //email is not valid
    @NotEmpty(message = "User email should not be empty or null")
    @Email(message = "email is not valid")
    String email;
}
