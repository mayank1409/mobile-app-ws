package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import com.mayankmadhav.demo.app.mobileappws.models.Address;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "firstName can't be blank")
    private String firstName;

    @NotBlank(message = "lastName can't be blank")
    private String lastName;

    @NotBlank(message = "email can't be blank")
    @Email(message = "email should be valid")
    private String email;

    private String password;

    @NotBlank(message = "mobile can't be blank")
    private String mobile;

    private UserProfile userProfile;

}
