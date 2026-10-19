
package com.example.restaurant_system.photos.service;

import com.example.restaurant_system.photos.entity.RestaurantPhoto;
import com.example.restaurant_system.photos.repository.RestaurantPhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PhotoService {

    private final RestaurantPhotoRepository photoRepository;
    private final CloudinaryPhotoService cloudinaryPhotoService;

    public PhotoService(
            RestaurantPhotoRepository photoRepository,
            CloudinaryPhotoService cloudinaryPhotoService
    ) {
        this.photoRepository = photoRepository;
        this.cloudinaryPhotoService = cloudinaryPhotoService;
    }

    public List<RestaurantPhoto> uploadPhotos(
            String restaurantId,
            MultipartFile[] files
    ) throws IOException {

        if (restaurantId == null || restaurantId.isBlank()) {
            throw new IllegalArgumentException(
                    "Restaurant ID is required"
            );
        }

        if (files == null || files.length == 0) {
            throw new IllegalArgumentException(
                    "At least one photo is required"
            );
        }

        List<RestaurantPhoto> uploadedPhotos = new ArrayList<>();

        for (MultipartFile file : files) {

            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException(
                        "Photo must not be empty"
                );
            }

            Map<?, ?> result = cloudinaryPhotoService.upload(
                    file,
                    "restaurants/" + restaurantId
            );

            String imageUrl = (String) result.get("secure_url");
            String publicId = (String) result.get("public_id");

            if (imageUrl == null || publicId == null) {
                throw new IllegalStateException(
                        "Cloudinary did not return photo details"
                );
            }

            RestaurantPhoto photo = new RestaurantPhoto();
            photo.setRestaurantId(restaurantId);
            photo.setImageUrl(imageUrl);
            photo.setPublicId(publicId);

            uploadedPhotos.add(photoRepository.save(photo));
        }

        return uploadedPhotos;
    }

    public List<RestaurantPhoto> getRestaurantPhotos(
            String restaurantId
    ) {
        return photoRepository
                .findAllByRestaurantIdOrderByCreatedAtDesc(restaurantId);
    }
}
