
package com.example.restaurant_system.reviews.dto;

import com.example.restaurant_system.restaurant.model.Photo;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class ReviewResponse {

    private String id;
    private String content;
    private Integer rating;
    private Instant datePosted;
    private Instant lastEdited;
    private List<Photo> photos;
    private String writtenBy;

    public ReviewResponse() {
    }

    public ReviewResponse(
            String id,
            String content,
            Integer rating,
            Instant datePosted,
            Instant lastEdited,
            List<Photo> photos,
            String writtenBy
    ) {
        this.id = id;
        this.content = content;
        this.rating = rating;
        this.datePosted = datePosted;
        this.lastEdited = lastEdited;
        this.photos = photos;
        this.writtenBy = writtenBy;
    }
}
