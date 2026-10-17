package com.example.restaurant_system.restaurant.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Photo {

    private String url;
    private String caption;
    private LocalDateTime uploadDate;

    public Photo() {
    }

    public Photo(String url, String caption, LocalDateTime uploadDate) {
        this.url = url;
        this.caption = caption;
        this.uploadDate = uploadDate;
    }

}
