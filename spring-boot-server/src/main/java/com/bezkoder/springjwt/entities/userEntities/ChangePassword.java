package com.bezkoder.springjwt.entities.userEntities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePassword {

    @NotBlank
    @Size(max = 120)
    private String currentPassword;
    @NotBlank
    @Size(max = 120)
    private String newPassword;
    @NotBlank
    @Size(max = 120)
    private String conformPassword;
    @NotBlank
    @Size(max = 120)
    private String currentUser;

}
