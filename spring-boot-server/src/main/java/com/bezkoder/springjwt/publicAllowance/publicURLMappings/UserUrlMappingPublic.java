package com.bezkoder.springjwt.publicAllowance.publicURLMappings;

import org.springframework.stereotype.Component;

@Component
public class UserUrlMappingPublic {

    public static final String GET_USER_ADDRESS_PUBLIC="getUserAddress_public/{userName}";

    public static final String UPDATE_USER_ADDRESS_PUBLIC="updateUserAddress_public";
}
