package com.bezkoder.springjwt.publicAllowance.publicURLMappings;

import org.springframework.stereotype.Component;

@Component
public class UserUrlMappingPublic {

    public static final String GET_USER_ADDRESS_PUBLIC="getUserAddress_public/{userName}";

    public static final String UPDATE_USER_ADDRESS_PUBLIC="updateUserAddress_public";
    public static final String GET_CURRENT_USER_ADDRESS="getCurrentUserAddress/{userName}";
    public static final String GET_CURRENT_USER="getCurrentUser/{userName}";
    public static final String UPDATE_CURRENT_USER_PROFILE="updateCurrentUserProfile";
    public static final String CHANGE_PASSWORD_CURRENT_USER="changePasswordCurrentUser";

}
