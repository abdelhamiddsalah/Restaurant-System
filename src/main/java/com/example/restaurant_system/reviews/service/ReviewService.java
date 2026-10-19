
package com.example.restaurant_system.reviews.service;

import com.example.restaurant_system.common.exception.ResourceNotFoundException;
import com.example.restaurant_system.restaurant.document.Restaurant;
import com.example.restaurant_system.restaurant.document.Review;
import com.example.restaurant_system.restaurant.repository.RestaurantRepository;
import com.example.restaurant_system.reviews.dto.ReviewRequest;
import com.example.restaurant_system.reviews.dto.ReviewResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    private final RestaurantRepository restaurantRepository;

    public ReviewService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public ReviewResponse addReview(
            String restaurantId,
            ReviewRequest request,
            String writtenBy
    ) {
        // 1. Find restaurant
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found: " + restaurantId
                        )
                );

        // 2. Create review
        Review review = new Review();

        review.setId(UUID.randomUUID().toString());
        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setDatePosted(Instant.now());
        review.setLastEdited(null);

        review.setPhotos(
                request.getPhotos() != null
                        ? new ArrayList<>(request.getPhotos())
                        : new ArrayList<>()
        );

        review.setWrittenBy(writtenBy);

        // 3. Add review to restaurant
        List<Review> reviews = restaurant.getReviews();

        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        reviews.add(review);
        restaurant.setReviews(reviews);

        // 4. Recalculate average rating
        double averageRating = reviews.stream()
                .filter(r -> r.getRating() != null)
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);

        restaurant.setAverageRating(averageRating);

        // 5. Save updated restaurant
        restaurantRepository.save(restaurant);

        // 6. Convert Review to ReviewResponse
        ReviewResponse response = new ReviewResponse();

        response.setId(review.getId());
        response.setContent(review.getContent());
        response.setRating(review.getRating());
        response.setDatePosted(review.getDatePosted());
        response.setLastEdited(review.getLastEdited());
        response.setPhotos(review.getPhotos());
        response.setWrittenBy(review.getWrittenBy());

        return response;
    }

    public List<ReviewResponse> getReviewsByRestaurantId(
            String restaurantId
    ) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Restaurant not found: " + restaurantId
                        )
                );

        List<Review> reviews = restaurant.getReviews();

        if (reviews == null) {
            return new ArrayList<>();
        }

        return reviews.stream()
                .map(review -> {
                    ReviewResponse response = new ReviewResponse();

                    response.setId(review.getId());
                    response.setContent(review.getContent());
                    response.setRating(review.getRating());
                    response.setDatePosted(review.getDatePosted());
                    response.setLastEdited(review.getLastEdited());
                    response.setPhotos(review.getPhotos());
                    response.setWrittenBy(review.getWrittenBy());

                    return response;
                })
                .toList();
    }
}
