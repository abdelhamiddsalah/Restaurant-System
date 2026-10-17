package com.example.restaurant_system.restaurant.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContactInformation {

    private String phoneNumber;
    private String email;
    private String website;

    public ContactInformation() {
    }

    public ContactInformation(
            String phoneNumber,
            String email,
            String website
    ) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.website = website;
    }

}
