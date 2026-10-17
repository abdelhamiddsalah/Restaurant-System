package com.example.restaurant_system.restaurant.service;


import com.example.restaurant_system.restaurant.document.Restaurant;
import com.example.restaurant_system.restaurant.dto.RestaurantRequest;
import com.example.restaurant_system.restaurant.dto.RestaurantResponse;
import com.example.restaurant_system.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public RestaurantResponse createRestaurant(
            RestaurantRequest request,
            String createdBy
    ) {
        // 1. Convert Request DTO to Entity
        Restaurant restaurant = new Restaurant();

        restaurant.setId(UUID.randomUUID().toString());
        restaurant.setName(request.getName());
        restaurant.setCuisineType(request.getCuisineType());
        restaurant.setContactInformation(
                request.getContactInformation()
        );
        restaurant.setGeoLocation(request.getGeoLocation());
        restaurant.setAddress(request.getAddress());
        restaurant.setOperatingHours(request.getOperatingHours());
        restaurant.setPhotos(request.getPhotos());

        // 2. Set system-managed fields
        restaurant.setAverageRating(0.0);
        restaurant.setReviews(new ArrayList<>());
        restaurant.setCreatedBy(createdBy);

        // 3. Save Restaurant in Elasticsearch
        Restaurant savedRestaurant =
                restaurantRepository.save(restaurant);

        // 4. Convert Entity to Response DTO
        RestaurantResponse response = new RestaurantResponse();

        response.setId(savedRestaurant.getId());
        response.setName(savedRestaurant.getName());
        response.setCuisineType(savedRestaurant.getCuisineType());
        response.setContactInformation(
                savedRestaurant.getContactInformation()
        );
        response.setAverageRating(savedRestaurant.getAverageRating());
        response.setGeoLocation(savedRestaurant.getGeoLocation());
        response.setAddress(savedRestaurant.getAddress());
        response.setOperatingHours(savedRestaurant.getOperatingHours());
        response.setPhotos(savedRestaurant.getPhotos());
        response.setReviews(savedRestaurant.getReviews());
        response.setCreatedBy(savedRestaurant.getCreatedBy());

        return response;
    }
}
