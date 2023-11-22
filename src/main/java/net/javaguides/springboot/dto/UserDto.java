package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "User DTO Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;

    @Schema(
            description = "User First Name"
    )
    @NotEmpty(message = "user firstname should not be empty or null")
    private String firstName;
    @Schema(
            description = "User Last Name"
    )
    @NotEmpty(message = "user lastname should not be empty or null")
    private String lastName;

    @Schema(
            description = "User Email Address"
    )

    @NotEmpty(message = "user email should not be empty or null")
    @Email(message = "email address should be valid")
    private String email;
}
