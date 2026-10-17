package com.example.restaurant_system.restaurant.document;

import com.example.restaurant_system.restaurant.model.Photo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class Review {

    private String id;
    private String content;
    private Integer rating;
    private LocalDateTime datePosted;
    private LocalDateTime lastEdited;
    private List<Photo> photos;
    private String writtenBy;

    public Review() {
    }

    public Review(
            String id,
            String content,
            Integer rating,
            LocalDateTime datePosted,
            LocalDateTime lastEdited,
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
