package com.example.restaurant_system.photos.repository;

import com.example.restaurant_system.photos.entity.RestaurantPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantPhotoRepository
        extends JpaRepository<RestaurantPhoto, UUID> {

    List<RestaurantPhoto> findAllByRestaurantIdOrderByCreatedAtDesc(
            String restaurantId
    );

    Optional<RestaurantPhoto> findByIdAndRestaurantId(
            UUID id,
            String restaurantId
    );

    boolean existsByRestaurantIdAndPublicId(
            String restaurantId,
            String publicId
    );
}
