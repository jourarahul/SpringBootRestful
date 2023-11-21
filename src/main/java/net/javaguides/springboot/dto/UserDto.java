package net.javaguides.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @NotEmpty(message = "user firstname should not be empty or null")
    private String firstName;
    @NotEmpty(message = "user lastname should not be empty or null")
    private String lastName;

    @NotEmpty(message = "user email should not be empty or null")
    @Email(message = "email address should be valid")
    private String email;
}
