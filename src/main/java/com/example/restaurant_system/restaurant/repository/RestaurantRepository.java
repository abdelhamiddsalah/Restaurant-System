package com.example.restaurant_system.restaurant.repository;

import com.example.restaurant_system.restaurant.document.Restaurant;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RestaurantRepository
        extends ElasticsearchRepository<Restaurant, String> {

    List<Restaurant> findByNameContaining(String name);

    @Query("""
        {
          "bool": {
            "filter": [
              {
                "geo_distance": {
                  "distance": "?2",
                  "geoLocation": {
                    "lat": ?0,
                    "lon": ?1
                  }
                }
              }
            ]
          }
        }
        """)
    List<Restaurant> findNearbyRestaurants(
            double latitude,
            double longitude,
            String distance
    );
}