
package com.example.restaurant_system.reviews.dto;

import com.example.restaurant_system.restaurant.model.Photo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReviewRequest {

    @NotBlank(message = "Review content is required")
    private String content;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not exceed 5")
    private Integer rating;

    private List<Photo> photos;

    public ReviewRequest() {
    }

    public ReviewRequest(
            String content,
            Integer rating,
            List<Photo> photos
    ) {
        this.content = content;
        this.rating = rating;
        this.photos = photos;
    }
}
