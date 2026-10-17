package com.example.restaurant_system.restaurant.dto;


import com.example.restaurant_system.restaurant.model.Address;
import com.example.restaurant_system.restaurant.model.ContactInformation;
import com.example.restaurant_system.restaurant.model.OperatingHours;
import com.example.restaurant_system.restaurant.model.Photo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.util.List;

@Setter
@Getter
public class RestaurantRequest {

    private String name;
    private String cuisineType;
    private ContactInformation contactInformation;
    private GeoPoint geoLocation;
    private Address address;
    private OperatingHours operatingHours;
    private List<Photo> photos;

    public RestaurantRequest() {
    }

    public RestaurantRequest(
            String name,
            String cuisineType,
            ContactInformation contactInformation,
            GeoPoint geoLocation,
            Address address,
            OperatingHours operatingHours,
            List<Photo> photos
    ) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.contactInformation = contactInformation;
        this.geoLocation = geoLocation;
        this.address = address;
        this.operatingHours = operatingHours;
        this.photos = photos;
    }

}
