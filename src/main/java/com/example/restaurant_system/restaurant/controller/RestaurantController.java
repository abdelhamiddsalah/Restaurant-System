package com.example.restaurant_system.restaurant.controller;


import com.example.restaurant_system.restaurant.dto.RestaurantRequest;
import com.example.restaurant_system.restaurant.dto.RestaurantResponse;
import com.example.restaurant_system.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(
            RestaurantService restaurantService
    ) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> createRestaurant(
            @RequestBody RestaurantRequest request
    ) {
        RestaurantResponse response =
                restaurantService.createRestaurant(request, null);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
