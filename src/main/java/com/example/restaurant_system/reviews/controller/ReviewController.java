
package com.example.restaurant_system.reviews.controller;

import com.example.restaurant_system.restaurant.service.RestaurantService;
import com.example.restaurant_system.reviews.dto.ReviewRequest;
import com.example.restaurant_system.reviews.dto.ReviewResponse;

import com.example.restaurant_system.reviews.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController( ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{restaurantId}/reviews")
    public ResponseEntity<ReviewResponse> addReview(
            @PathVariable String restaurantId,
            @Valid @RequestBody ReviewRequest request
    ) {
        ReviewResponse response = reviewService.addReview(
                restaurantId,
                request,
                null
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @GetMapping("/{restaurantId}/reviews")
    public ResponseEntity<List<ReviewResponse>> getReviews(
            @PathVariable String restaurantId
    ) {
        List<ReviewResponse> reviews =
                reviewService.getReviewsByRestaurantId(restaurantId);

        return ResponseEntity.ok(reviews);
    }


}
