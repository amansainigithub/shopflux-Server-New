package com.bezkoder.springjwt.entities.addressEntities;

import lombok.Data;

@Data
public class UpdateAddressForm {

        private String    addressLine1;
        private  String  addressLine2;
        private  String  city;
        private  String  state;
        private  String  zipCode;
        private  String  country;
        private  String  mobile;

}
