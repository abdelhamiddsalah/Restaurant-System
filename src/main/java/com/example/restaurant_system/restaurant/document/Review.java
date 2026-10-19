
package com.example.restaurant_system.restaurant.document;

import com.example.restaurant_system.restaurant.model.Photo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class Review {

    private String id;
    private String content;
    private Integer rating;

    @Field(type = FieldType.Date, format = {}, pattern = "epoch_millis")
    private Instant datePosted;

    @Field(type = FieldType.Date, format = {}, pattern = "epoch_millis")
    private Instant lastEdited;

    private List<Photo> photos;
    private String writtenBy;

    public Review() {
    }

    public Review(
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
