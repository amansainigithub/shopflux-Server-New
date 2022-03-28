package com.bezkoder.springjwt.entities.userEntities;

import lombok.Data;

@Data
public class UserEntity {

    private String username;

    private String email;

    private String gender;

    private String mobile;
}
