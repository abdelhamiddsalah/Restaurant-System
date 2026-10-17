package com.example.restaurant_system.restaurant.dto;


import com.example.restaurant_system.restaurant.document.Review;
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
public class RestaurantResponse {

    private String id;
    private String name;
    private String cuisineType;
    private ContactInformation contactInformation;
    private Double averageRating;
    private GeoPoint geoLocation;
    private Address address;
    private OperatingHours operatingHours;
    private List<Photo> photos;
    private List<Review> reviews;
    private String createdBy;

    public RestaurantResponse() {
    }

    public RestaurantResponse(
            String id,
            String name,
            String cuisineType,
            ContactInformation contactInformation,
            Double averageRating,
            GeoPoint geoLocation,
            Address address,
            OperatingHours operatingHours,
            List<Photo> photos,
            List<Review> reviews,
            String createdBy
    ) {
        this.id = id;
        this.name = name;
        this.cuisineType = cuisineType;
        this.contactInformation = contactInformation;
        this.averageRating = averageRating;
        this.geoLocation = geoLocation;
        this.address = address;
        this.operatingHours = operatingHours;
        this.photos = photos;
        this.reviews = reviews;
        this.createdBy = createdBy;
    }

}
