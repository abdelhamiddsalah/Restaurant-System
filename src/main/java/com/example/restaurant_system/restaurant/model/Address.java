package com.example.restaurant_system.restaurant.model;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Address {

    private String streetNumber;
    private String streetName;
    private String unit;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address() {
    }

    public Address(
            String streetNumber,
            String streetName,
            String unit,
            String city,
            String state,
            String postalCode,
            String country
    ) {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.unit = unit;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }
}
